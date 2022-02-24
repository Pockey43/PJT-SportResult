package fr.formation.inti.service;

import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import fr.formation.inti.entity.Match;

public interface ApiService {
	
	 List<Match> findMatchByLeague(Integer leagueId, Integer season) throws UnirestException, JsonMappingException, JsonProcessingException, ParseException;
	 
	 List<Match> findMatchNotPlayed() throws JsonProcessingException, IllegalArgumentException, UnirestException;
	 
	 List<Match> findMatchFinished(Integer leagueId, Integer season) throws JsonProcessingException, IllegalArgumentException, UnirestException;
	 
	 List<Match> findMatchByDate(Integer leagueId, Integer season, String date) throws JsonProcessingException, IllegalArgumentException, UnirestException;

}
