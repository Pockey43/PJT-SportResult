package fr.formation.inti.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import fr.formation.inti.entity.Team;
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
