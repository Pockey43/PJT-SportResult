package fr.formation.inti.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import fr.formation.inti.entity.League;
import fr.formation.inti.entity.Match;
import fr.formation.inti.entity.MatchStats;
import fr.formation.inti.entity.Team;
import fr.formation.inti.entity.TeamStat;
import fr.formation.inti.entity.Venue;

@Service
public class ApiServiceimpl implements ApiService{
	
	
	@Override
	public List<Match> findMatchByLeague(Integer leagueId, Integer season) throws UnirestException, JsonMappingException, JsonProcessingException, ParseException {
		HttpResponse<String> client = Unirest
				.get("https://api-football-v1.p.rapidapi.com/v3/fixtures?league="+leagueId+"&season="+season)
				.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
				.header("x-rapidapi-key", "51b09b1246msh0cc2257e3f23c9bp16944fjsn2f6815b57387").asString();
		
		String json = client.getBody();
		ObjectMapper objmap = new ObjectMapper();
		objmap.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		List<Match> matchs = new ArrayList<Match>();
		
		JsonNode jsonNode = objmap.readTree(json);
		
		Integer nbResults = 0;
		if (jsonNode.get("results")!=null)
			nbResults = jsonNode.get("results").asInt();
		JsonNode response = jsonNode.findPath("response");
		
		for(int i=0; i< nbResults; i++) {
			
			Match matchi = new Match();
			
			JsonNode respi = response.get(i);
			JsonNode fixturei = respi.findPath("fixture");
			JsonNode venue = respi.findPath("venue");
			JsonNode league = respi.findPath("league");
			JsonNode teamHome = respi.findPath("teams").findPath("home");
			JsonNode teamAway = respi.findPath("teams").findPath("away");
			JsonNode score = respi.findPath("score").findPath("fulltime");
			JsonNode status = respi.findPath("status").get("long");
			
			
			
			String datestring = fixturei.get("date").asText();
			LocalDateTime datetime = LocalDateTime.parse(datestring, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+00:00"));
			
			String date = datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			matchi.setId(fixturei.get("id").asInt());
			matchi.setDate(date);
			matchi.setTeamHome(objmap.treeToValue(teamHome, Team.class));
			matchi.setTeamAway(objmap.treeToValue(teamAway, Team.class));
			matchi.setVenue(objmap.treeToValue(venue,Venue.class));
			matchi.setLeague(objmap.treeToValue(league,League.class));
			matchi.setScoreHome(score.get("home").asInt());
			matchi.setScoreAway(score.get("away").asInt());
			matchi.setStatus(status.asText());
			
			matchs.add(matchi);
			
		}
		
		return matchs;

	}

	@Override
	public List<Match> findMatchNotPlayed() throws JsonProcessingException, IllegalArgumentException, UnirestException {
		HttpResponse<String> client = Unirest
				.get("https://api-football-v1.p.rapidapi.com/v3/fixtures?status=NS&season=2021&league=39")
				.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
				.header("x-rapidapi-key", "51b09b1246msh0cc2257e3f23c9bp16944fjsn2f6815b57387").asString();
		
		String json = client.getBody();
		ObjectMapper objmap = new ObjectMapper();
		objmap.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		List<Match> matchs = new ArrayList<Match>();
		
		JsonNode jsonNode = objmap.readTree(json);
		
		Integer nbResults = 0;
		if (jsonNode.get("results")!=null)
			nbResults = jsonNode.get("results").asInt();
		JsonNode response = jsonNode.findPath("response");
		
		for(int i=0; i< nbResults; i++) {
			
			Match matchi = new Match();
			
			JsonNode respi = response.get(i);
			JsonNode fixturei = respi.findPath("fixture");
			JsonNode venue = respi.findPath("venue");
			JsonNode league = respi.findPath("league");
			JsonNode teamHome = respi.findPath("teams").findPath("home");
			JsonNode teamAway = respi.findPath("teams").findPath("away");
			JsonNode score = respi.findPath("score").findPath("fulltime");
			JsonNode status = respi.findPath("status").get("long");
			
			String datestring = fixturei.get("date").asText();
			LocalDateTime datetime = LocalDateTime.parse(datestring, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+00:00"));
			
			String date = datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			matchi.setId(fixturei.get("id").asInt());
			matchi.setDate(date);
			matchi.setTeamHome(objmap.treeToValue(teamHome, Team.class));
			matchi.setTeamAway(objmap.treeToValue(teamAway, Team.class));
			matchi.setVenue(objmap.treeToValue(venue,Venue.class));
			matchi.setLeague(objmap.treeToValue(league,League.class));
			matchi.setScoreHome(score.get("home").asInt());
			matchi.setScoreAway(score.get("away").asInt());
			matchi.setStatus(status.asText());
			
			matchs.add(matchi);
			
		}
		
		return matchs;
		
		
	}

	@Override
	public List<Match> findMatchFinished(Integer leagueId, Integer season) throws JsonProcessingException, IllegalArgumentException, UnirestException {
		HttpResponse<String> client = Unirest
				.get("https://api-football-v1.p.rapidapi.com/v3/fixtures?status=FT&season="+season+"&league=39")
				.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
				.header("x-rapidapi-key", "51b09b1246msh0cc2257e3f23c9bp16944fjsn2f6815b57387").asString();
		
		String json = client.getBody();
		ObjectMapper objmap = new ObjectMapper();
		objmap.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		List<Match> matchs = new ArrayList<Match>();
		
		JsonNode jsonNode = objmap.readTree(json);
		
		Integer nbResults = 0;
		if (jsonNode.get("results")!=null)
			nbResults = jsonNode.get("results").asInt();
		JsonNode response = jsonNode.findPath("response");
		
		for(int i=0; i< nbResults; i++) {
			
			Match matchi = new Match();
			
			JsonNode respi = response.get(i);
			JsonNode fixturei = respi.findPath("fixture");
			JsonNode venue = respi.findPath("venue");
			JsonNode league = respi.findPath("league");
			JsonNode teamHome = respi.findPath("teams").findPath("home");
			JsonNode teamAway = respi.findPath("teams").findPath("away");
			JsonNode score = respi.findPath("score").findPath("fulltime");
			JsonNode status = respi.findPath("status").get("long");
			
			String datestring = fixturei.get("date").asText();
			LocalDateTime datetime = LocalDateTime.parse(datestring, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+00:00"));
			
			String date = datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			matchi.setId(fixturei.get("id").asInt());
			matchi.setDate(date);
			matchi.setTeamHome(objmap.treeToValue(teamHome, Team.class));
			matchi.setTeamAway(objmap.treeToValue(teamAway, Team.class));
			matchi.setVenue(objmap.treeToValue(venue,Venue.class));
			matchi.setLeague(objmap.treeToValue(league,League.class));
			matchi.setScoreHome(score.get("home").asInt());
			matchi.setScoreAway(score.get("away").asInt());
			matchi.setStatus(status.asText());
			
			matchs.add(matchi);
			
		}
		
		return matchs;
	}

	@Override
	public List<Match> findMatchByDate(Integer leagueId, Integer season, String fromDate) throws JsonProcessingException, IllegalArgumentException, UnirestException {
		HttpResponse<String> client = Unirest
				.get("https://api-football-v1.p.rapidapi.com/v3/fixtures?season="+season+"&league=39&from="+fromDate+"&to="+toDate(fromDate))
				.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
				.header("x-rapidapi-key", "51b09b1246msh0cc2257e3f23c9bp16944fjsn2f6815b57387").asString();
		
		String json = client.getBody();
		ObjectMapper objmap = new ObjectMapper();
		objmap.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		List<Match> matchs = new ArrayList<Match>();
		
		JsonNode jsonNode = objmap.readTree(json);
		Integer nbResults = 0;
		if (jsonNode.get("results")!=null)
			nbResults = jsonNode.get("results").asInt();
		JsonNode response = jsonNode.findPath("response");
		
		for(int i=0; i< nbResults; i++) {
			
			Match matchi = new Match();
			
			JsonNode respi = response.get(i);
			JsonNode fixturei = respi.findPath("fixture");
			JsonNode venue = respi.findPath("venue");
			JsonNode league = respi.findPath("league");
			JsonNode teamHome = respi.findPath("teams").findPath("home");
			JsonNode teamAway = respi.findPath("teams").findPath("away");
			JsonNode score = respi.findPath("score").findPath("fulltime");
			JsonNode status = respi.findPath("status").get("long");
			
			String datestring = fixturei.get("date").asText();
			LocalDateTime datetime = LocalDateTime.parse(datestring, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+00:00"));
			
			String date = datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			matchi.setId(fixturei.get("id").asInt());
			matchi.setDate(date);
			matchi.setTeamHome(objmap.treeToValue(teamHome, Team.class));
			matchi.setTeamAway(objmap.treeToValue(teamAway, Team.class));
			matchi.setVenue(objmap.treeToValue(venue,Venue.class));
			matchi.setLeague(objmap.treeToValue(league,League.class));
			matchi.setScoreHome(score.get("home").asInt());
			matchi.setScoreAway(score.get("away").asInt());
			matchi.setStatus(status.asText());
			
			matchs.add(matchi);
			
		}
		System.out.println("https://api-football-v1.p.rapidapi.com/v3/fixtures?season="+season+"&league=39&from="+fromDate+"&to="+toDate(fromDate));
		
		return matchs;
	}
	
	public List<Match> liveScore()
			throws UnirestException, JsonProcessingException, IllegalArgumentException, ParseException {
		HttpResponse<String> client = Unirest.get("https://api-football-v1.p.rapidapi.com/v3/fixtures?live=all")
				.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
				.header("x-rapidapi-key", "51b09b1246msh0cc2257e3f23c9bp16944fjsn2f6815b57387").asString();

		String json = client.getBody();
		ObjectMapper objmap = new ObjectMapper();
		objmap.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		List<Match> matchs = new ArrayList<Match>();

		JsonNode jsonNode = objmap.readTree(json);

		Integer nbResults = jsonNode.get("results").asInt();
		JsonNode response = jsonNode.findPath("response");

		for (int i = 0; i < nbResults; i++) {

			Match matchi = new Match();

			JsonNode respi = response.get(i);
			JsonNode fixturei = respi.findPath("fixture");
			JsonNode venue = respi.findPath("venue");
			JsonNode league = respi.findPath("league");
			JsonNode teamHome = respi.findPath("teams").findPath("home");
			JsonNode teamAway = respi.findPath("teams").findPath("away");
			JsonNode score = respi.findPath("score").findPath("fulltime");

			String datestring = fixturei.get("date").asText();
			LocalDateTime datetime = LocalDateTime.parse(datestring, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+00:00"));
			
			String date = datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			matchi.setId(fixturei.get("id").asInt());
			matchi.setDate(date);
			matchi.setTeamHome(objmap.treeToValue(teamHome, Team.class));
			matchi.setTeamAway(objmap.treeToValue(teamAway, Team.class));
			matchi.setVenue(objmap.treeToValue(venue, Venue.class));
			matchi.setLeague(objmap.treeToValue(league, League.class));
			matchi.setScoreHome(score.get("home").asInt());
			matchi.setScoreAway(score.get("away").asInt());
			matchs.add(matchi);
			for (Match match : matchs) {
				System.out.println("==========================================" + match);

			}
		}

		return matchs;

	}

	public Team teamById(Integer id) throws UnirestException, JsonMappingException, JsonProcessingException {

		HttpResponse<String> client = Unirest.get("https://api-football-v1.p.rapidapi.com/v3/teams?id="+id)
				.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
				.header("x-rapidapi-key", "51b09b1246msh0cc2257e3f23c9bp16944fjsn2f6815b57387").asString();
		Team team = new Team();
		String match = client.getBody();
		ObjectMapper objmap = new ObjectMapper();
		objmap.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		JsonNode jsonNode = objmap.readTree(match);
		JsonNode response = jsonNode.get("response");
		JsonNode jteam = response.findPath("team");
		JsonNode stade = response.findPath("venue");
		team.setId(jteam.get("id").asInt());
		team.setName(jteam.get("name").asText());
		team.setLogo(jteam.get("logo").asText());
		team.setCountry(jteam.get("country").asText());
		team.setCode(jteam.get("code").asText());
		team.setStade(objmap.treeToValue(stade,Venue.class));
		
		
		return team;

	}
	
	public Integer getLeaguebyTeamId(Integer id) throws UnirestException, JsonMappingException, JsonProcessingException{
		HttpResponse<String> response = Unirest.get("https://api-football-v1.p.rapidapi.com/v3/leagues?team="+id)
				.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
				.header("x-rapidapi-key", "51b09b1246msh0cc2257e3f23c9bp16944fjsn2f6815b57387")
				.asString();
		
		
		String lg = response.getBody();
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		JsonNode jsonNode = mapper.readTree(lg);
		JsonNode reponse = jsonNode.get("response");
		JsonNode jleague = reponse.findPath("league");
		return jleague.findPath("id").asInt();
			
		
	}
	public TeamStat teamStatsbyLeagueIdAndTeamId(Integer id, Integer leagueId) throws UnirestException, JsonMappingException, JsonProcessingException{
		
		
		HttpResponse<String> response = Unirest.get("https://api-football-v1.p.rapidapi.com/v3/teams/statistics?league="+leagueId+"&season=2021&team="+id)
				.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
				.header("x-rapidapi-key", "51b09b1246msh0cc2257e3f23c9bp16944fjsn2f6815b57387")
				.asString();
		TeamStat ts = new TeamStat();
		String client = response.getBody();
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES);
		JsonNode jsonNode = mapper.readTree(client);
		JsonNode reponse = jsonNode.get("response");
		ts.setForm(reponse.get("form").asText());
		JsonNode jstats = reponse.findPath("fixtures");
		JsonNode jplayed =  jstats.findPath("played");
		
	
		
		ts.setMatchPlayed(mapper.treeToValue(jplayed,MatchStats.class));
		
		JsonNode jwins =  jstats.findPath("wins");
		ts.setMatchWins(mapper.treeToValue(jwins,MatchStats.class));
		JsonNode jdraw =  jstats.findPath("loses");
		ts.setMatchDraws(mapper.treeToValue(jdraw,MatchStats.class));
		JsonNode jloses =  jstats.findPath("loses");
		ts.setMatchLost(mapper.treeToValue(jloses,MatchStats.class));
		JsonNode jgoal = reponse.findPath("goals");
		JsonNode jgoalFor = jgoal.findPath("for").findPath("total");
		ts.setGoalFor(mapper.treeToValue(jgoalFor, MatchStats.class));
		JsonNode jgoalagainst = jgoal.findPath("against").findPath("total");
		ts.setGoalAgainst(mapper.treeToValue(jgoalagainst, MatchStats.class));
		JsonNode jstreak = reponse.findPath("biggest").findPath("streak");
		ts.setSerieVictoire(mapper.treeToValue(jstreak, MatchStats.class));
		JsonNode jteam = reponse.findPath("team");
		ts.setTeam(mapper.treeToValue(jteam,Team.class));
		
		return ts;
		
	}

	private static String toDate(String fromDate) {
		Integer year = Integer.valueOf(fromDate.substring(0, 4));
		Integer month = Integer.valueOf(fromDate.substring(5, 7));
		Integer day = Integer.valueOf(fromDate.substring(8, 10));
		String monthStr;
		String dayStr;
		if (month<12)
			month+=1;
		if (month==12) {
			month=1;
			year+=1;
		}
		if (month<10)
			monthStr = "0"+month.toString();
		else
			monthStr = month.toString();
		if (day<10)
			dayStr = "0"+day.toString();
		else
			dayStr = day.toString();
					
			
		return year+"-"+monthStr+"-"+dayStr;
	}
	
	public static void main(String[] args) {
		System.out.println(toDate("2021-12-01"));
	}
	
	

}