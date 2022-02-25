package fr.formation.inti.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/api")
public class ApiTest {
	
	@GetMapping("/{id}")
	private Match findMatchById(@PathVariable Integer id) throws UnirestException, JsonMappingException, JsonProcessingException, ParseException {
		HttpResponse<String> client = Unirest
				.get("https://api-football-v1.p.rapidapi.com/v3/fixtures?id="+id)
				.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
				.header("x-rapidapi-key", "51b09b1246msh0cc2257e3f23c9bp16944fjsn2f6815b57387").asString();
		
		String match = client.getBody();
		ObjectMapper objmap = new ObjectMapper();
		objmap.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		
		JsonNode jsonNode = objmap.readTree(match);

		JsonNode response =jsonNode.get("response");
		System.out.println(response);

		
		JsonNode fixture = response.findPath("fixture");
		JsonNode venue = response.findPath("venue");
		JsonNode league = response.findPath("league");
		JsonNode teamHome = response.findPath("teams").findPath("home");
		JsonNode teamAway = response.findPath("teams").findPath("away");
		JsonNode score = response.findPath("score").findPath("fulltime");
		JsonNode status = response.findPath("status").get("long");
		Match match2 = new Match();
		
//		String datestring = fixture.get("date").asText();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//		Date date = formatter.parse(datestring);
		
		String datestring = fixture.get("date").asText();
		LocalDateTime datetime = LocalDateTime.parse(datestring, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+00:00"));
		
		String date = datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		match2.setId(fixture.get("id").asInt());
		match2.setDate(date);
		match2.setTeamHome(objmap.treeToValue(teamHome, Team.class));
		match2.setTeamAway(objmap.treeToValue(teamAway, Team.class));
		match2.setVenue(objmap.treeToValue(venue,Venue.class));
		match2.setLeague(objmap.treeToValue(league,League.class));
		match2.setScoreHome(score.get("home").asInt());
		match2.setScoreAway(score.get("away").asInt());
		match2.setStatus(status.asText());
		System.out.println(match2);
		
		return match2;
	}
	
	
	@GetMapping("/listMatchByLeague/{leagueId}")
	private List<Match> findMatchByLeague(@PathVariable Integer leagueId) throws UnirestException, JsonMappingException, JsonProcessingException, ParseException {
		HttpResponse<String> client = Unirest
				.get("https://api-football-v1.p.rapidapi.com/v3/fixtures?league="+leagueId+"&season=2020")
				.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
				.header("x-rapidapi-key", "51b09b1246msh0cc2257e3f23c9bp16944fjsn2f6815b57387").asString();
		
		String json = client.getBody();
		ObjectMapper objmap = new ObjectMapper();
		objmap.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		List<Match> matchs = new ArrayList<Match>();
		
		JsonNode jsonNode = objmap.readTree(json);
		
		Integer nbResults = jsonNode.get("results").asInt();
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
			
			matchs.add(matchi);
			
		}
		
		return matchs;

	}
	

	@GetMapping("/not")
	private List<Match> findMatchnotYet() throws UnirestException, JsonMappingException, JsonProcessingException, ParseException {
		HttpResponse<String> client = Unirest
				.get("https://api-football-v1.p.rapidapi.com/v3/fixtures?status=NS&season=2021&league=39")
				.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
				.header("x-rapidapi-key", "51b09b1246msh0cc2257e3f23c9bp16944fjsn2f6815b57387").asString();
		
		String json = client.getBody();
		ObjectMapper objmap = new ObjectMapper();
		objmap.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		List<Match> matchs = new ArrayList<Match>();
		
		JsonNode jsonNode = objmap.readTree(json);
		
		Integer nbResults = jsonNode.get("results").asInt();
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
			
			matchs.add(matchi);
			
		}
		
		return matchs;
	}
	
	

}