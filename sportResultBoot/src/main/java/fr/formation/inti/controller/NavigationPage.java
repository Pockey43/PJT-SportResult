package fr.formation.inti.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import fr.formation.inti.entity.Match;
import fr.formation.inti.entity.Team;
import fr.formation.inti.entity.TeamStat;
import fr.formation.inti.service.ApiService;

@Controller
public class NavigationPage {
	
	@Autowired
	private ApiService apiService;
	
	private static Boolean previous=true;
	private static Boolean next=true;
	private static Boolean active=false;
	
	
	
	
	@GetMapping({"/index"})
	public String index(Model model) {
		return "index";
	}

	
	@GetMapping("/calendrier1")
	public String calendrier1(Model model, @RequestParam("season") Integer season, HttpSession session) throws JsonMappingException, JsonProcessingException, UnirestException, ParseException {
		active = (Boolean) session.getAttribute("session");
		model.addAttribute("previous", previous);
		model.addAttribute("next", next);
		String fromDate = season+"-01-01";
		String monthYear=monthYear(fromDate);
		model.addAttribute("season", season);
		
		model.addAttribute("monthYear", monthYear);
		model.addAttribute("fromDate", fromDate);
		List<Match> matchs = apiService.findMatchByLeague(39, season);
		model.addAttribute("matchs", matchs);
		return "/calendrier";
	}
	
	@GetMapping("/matchToCome")
	public String matchnotStarted(Model model, HttpSession session) throws JsonMappingException, JsonProcessingException, UnirestException, ParseException {
		active = (Boolean) session.getAttribute("session");
		model.addAttribute("previous", previous);
		model.addAttribute("next", next);
		Integer season = 2021;
		String fromDate = season+"-01-01";
		String monthYear=monthYear(fromDate);
		model.addAttribute("season", season);

		model.addAttribute("monthYear", monthYear);
		model.addAttribute("fromDate", fromDate);
		List<Match> matchs = apiService.findMatchNotPlayed();
		model.addAttribute("matchs", matchs);
		model.addAttribute("toCome", "toCome");
		return "/calendrier";
	}
	
	@GetMapping("/matchFinished")
	public String matchFinished(Model model, @RequestParam("season") Integer season, HttpSession session) throws JsonMappingException, JsonProcessingException, UnirestException, ParseException {
		
		model.addAttribute("previous", previous);
		model.addAttribute("next", next);
		String fromDate = season+"-01-01";
		String monthYear=monthYear(fromDate);
		
		model.addAttribute("season", season);

		model.addAttribute("monthYear", monthYear);
		model.addAttribute("fromDate", fromDate);
		
		List<Match> matchs = apiService.findMatchFinished(39,season);
		model.addAttribute("matchs", matchs);
		model.addAttribute("finished", "finished");
		return "/calendrier";
	}
	

	@GetMapping("/matchByDate")
	public String matchByDate(Model model, @RequestParam("fromDate") String fromDate, HttpSession session) throws JsonMappingException, JsonProcessingException, UnirestException, ParseException {
		active = (Boolean) session.getAttribute("session");
		model.addAttribute("previous", previous);
		model.addAttribute("next", next);
		String monthYear=monthYear(fromDate);
		Integer season = toSeason(fromDate);
		List<Match> matchs = apiService.findMatchByDate(39,season, fromDate);
		model.addAttribute("monthYear", monthYear);
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("season", season);
		model.addAttribute("matchs", matchs);
		model.addAttribute("byDate", "byDate");
		return "/calendrier";
	}
	
	@GetMapping("/matchByDateNext")
	public String matchByDateNext(Model model, @RequestParam("fromDate") String fromDate, HttpSession session) throws JsonMappingException, JsonProcessingException, UnirestException, ParseException {
		
		active = (Boolean) session.getAttribute("session");
		model.addAttribute("previous", previous);
		model.addAttribute("next", next);
		Integer season = toSeason(fromDate);

		fromDate = toNextMonth(fromDate);
		List<Match> matchs = apiService.findMatchByDate(39,season, fromDate);;
		
		while (matchs.size()==0 && !fromDate.equals("2022-06-01")) {
			System.out.println(fromDate);
			fromDate = toNextMonth(fromDate);
			season = toSeason(fromDate);
			matchs = apiService.findMatchByDate(39,season, fromDate);
		}
		
				
		String monthYear=monthYear(fromDate);
	
			
		
		model.addAttribute("monthYear", monthYear);
		model.addAttribute("fromDate", fromDate);
	
		model.addAttribute("season", season);
		model.addAttribute("matchs", matchs);
		
		System.out.println(matchs.size());
		System.out.println(fromDate);
		model.addAttribute("byDate", "byDate");
		return "/calendrier";
	
	}
	
	@GetMapping("/matchByDatePrevious")
	public String matchByDatePrevious(Model model, @RequestParam("fromDate") String fromDate, HttpSession session) throws JsonMappingException, JsonProcessingException, UnirestException, ParseException {
		active = (Boolean) session.getAttribute("session");
		model.addAttribute("previous", previous);
		model.addAttribute("next", next);
		Integer season = toSeason(fromDate);
		List<Match> matchs= new ArrayList<Match>();

		while (matchs.size()==0 && !fromDate.equals("2020-08-01")) {
			System.out.println(fromDate);
			fromDate = toPreviousMonth(fromDate);
			season = toSeason(fromDate);
			matchs = apiService.findMatchByDate(39,season, fromDate);
		}
	
		String monthYear=monthYear(fromDate);
	
		model.addAttribute("monthYear", monthYear);
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("season", season);
		model.addAttribute("matchs", matchs);
		
		System.out.println(matchs.size());
		System.out.println(fromDate);
		if (fromDate.equals("2020-08-01")) {
			previous=false;
			model.addAttribute("previous", previous);
			model.addAttribute("byDate", "byDate");
			return "/calendrier";
			
		}
		model.addAttribute("byDate", "byDate");
		return "/calendrier";
	}
	
	
	
	@GetMapping("/calendrier")
	public String calendrier(Model model, HttpServletRequest req) throws JsonMappingException, JsonProcessingException, UnirestException, ParseException {
		active = (Boolean) req.getAttribute("session");
		model.addAttribute("session", active);
		
		model.addAttribute("previous", previous);
		model.addAttribute("next", next);
		Integer season = 2021;
		String fromDate = "2021-01-01";
		List<Match> matchs = apiService.findMatchByLeague(39, season);
		
		String monthYear=monthYear(fromDate);
		
		model.addAttribute("monthYear", monthYear);
		model.addAttribute("matchs", matchs);
		model.addAttribute("season", season);
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("fromDateNext", toNextMonth(fromDate));
		model.addAttribute("fromDatePrevious", toPreviousMonth(fromDate));
		
		return "/calendrier";
	}
	
	@GetMapping("/classement1")
	public String classement1() {
		return "classement1";
	}
	
	@GetMapping("/classement2")
	public String classement2() {
		return "classement2";
	}
	
	
	@GetMapping("/mesJoueurs")
	public String mesJoueurs() {
		return "mesJoueurs";
	}
	@GetMapping("/monChampionnat")
	public String monChampionnat() {
		return "monChampionnat";
	}
	@GetMapping("/monEquipe")
	public String monEquipe() {
		return "monEquipe";
	}
	@GetMapping("/retrouverCompte")
	public String retrouverCompte() {
		return "retrouverCompte";
	}
	
	@GetMapping("/matchendirect")
	public String matchEnDirect(Model model) throws JsonProcessingException, IllegalArgumentException, UnirestException, ParseException {
		List<Match> matchs = apiService.liveScore();
		model.addAttribute("livematch",matchs);
		return "matchendirect";
	}
	
	@GetMapping("/statistique")
	public String Statistique(Model model, @RequestParam("id")Integer id) throws JsonMappingException, JsonProcessingException, UnirestException {
		
		Team team = apiService.teamById(id);
		model.addAttribute("team", team);
		return "statistique";
	}
	
	@GetMapping("/teamStat")
	public String Stat(Model model, @RequestParam("id")Integer id) throws JsonMappingException, JsonProcessingException, UnirestException{
		
		Integer leagueId = apiService.getLeaguebyTeamId(id); 
		TeamStat ts = new TeamStat();
		ts = apiService.teamStatsbyLeagueIdAndTeamId(id, leagueId);
		model.addAttribute("teamStat", ts);
		System.out.println("================================"+ts);
		return "teamStat";
		
	}
	
	private String monthYear(String fromDate) {
		Integer year = Integer.valueOf(fromDate.substring(0, 4));
		Integer month = Integer.valueOf(fromDate.substring(5, 7));
		Integer day = Integer.valueOf(fromDate.substring(8, 10));
		String monthStr;
		if (month<10)
			monthStr = "0"+month.toString();
		else
			monthStr = month.toString();
		
		return monthStr+"-"+year;
	}
	
	private Integer toSeason(String fromDate) {
		Integer year = Integer.valueOf(fromDate.substring(0, 4));
		Integer month = Integer.valueOf(fromDate.substring(5, 7));
		if (month<7)
			year-=1;
		return year;
	}
	
	private  String toNextMonth(String fromDate) {
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
	
	private  String toPreviousMonth(String fromDate) {
		Integer year = Integer.valueOf(fromDate.substring(0, 4));
		Integer month = Integer.valueOf(fromDate.substring(5, 7));
		Integer day = Integer.valueOf(fromDate.substring(8, 10));
		String monthStr;
		String dayStr;
		if (month!=1)
			month-=1;
		if (month==1) {
			month=12;
			year-=1;
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
	
	

}