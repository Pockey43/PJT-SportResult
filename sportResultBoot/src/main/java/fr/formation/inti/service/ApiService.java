package fr.formation.inti.service;

import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import fr.formation.inti.entity.Match;
import fr.formation.inti.entity.Team;
import fr.formation.inti.entity.TeamStat;

public interface ApiService {
	
	 List<Match> findMatchByLeague(Integer leagueId, Integer season) throws UnirestException, JsonMappingException, JsonProcessingException, ParseException;
	 
	 List<Match> findMatchNotPlayed() throws JsonProcessingException, IllegalArgumentException, UnirestException;
	 
	 List<Match> findMatchFinished(Integer leagueId, Integer season) throws JsonProcessingException, IllegalArgumentException, UnirestException;
	 
	 List<Match> findMatchByDate(Integer leagueId, Integer season, String date) throws JsonProcessingException, IllegalArgumentException, UnirestException;

	 TeamStat teamStatsbyLeagueIdAndTeamId(Integer id, Integer leagueId) throws UnirestException, JsonMappingException, JsonProcessingException;
	 
	 Integer getLeaguebyTeamId(Integer id) throws UnirestException, JsonMappingException, JsonProcessingException;
	 
	 Team teamById(Integer id) throws UnirestException, JsonMappingException, JsonProcessingException;
	 
	 List<Match> liveScore() throws UnirestException, JsonProcessingException, IllegalArgumentException, ParseException;
}