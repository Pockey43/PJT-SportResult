package fr.formation.inti.service;

import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import fr.formation.inti.entity.Match;

public interface ApiService {
	
	 List<Match> findMatchByLeague(Integer leagueId) throws UnirestException, JsonMappingException, JsonProcessingException, ParseException;

}
