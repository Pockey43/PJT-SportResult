package fr.formation.inti.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	
	public List<Match> findMatchByLeague(Integer leagueId) throws UnirestException, JsonMappingException, JsonProcessingException, ParseException {
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
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date = formatter.parse(datestring);
			
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
