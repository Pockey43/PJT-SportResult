package fr.formation.inti.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import fr.formation.inti.entity.Match;
import fr.formation.inti.entity.User;
import fr.formation.inti.service.ApiService;
import fr.formation.inti.service.IUserService;

@Controller
public class NavigationPage {
	
	@Autowired
	private ApiService apiService;
	
	@Autowired
	private IUserService userService;
	
	@GetMapping({"/{leagueId}"})
	public String testApi(Model model,@PathVariable Integer leagueId) throws JsonMappingException, JsonProcessingException, UnirestException, ParseException {
		List<Match> matchs = apiService.findMatchByLeague(leagueId);
		model.addAttribute("matchs", matchs);
		
		
		return "tesApi";
	}
	
	
	@GetMapping({"/index"})
	public String index(Model model) {
		return "index";
	}

	
	@GetMapping("/calendrier/{leagueId}")
	public String calendrier(Model model,@PathVariable Integer leagueId) throws JsonMappingException, JsonProcessingException, UnirestException, ParseException {
		List<Match> matchs = apiService.findMatchByLeague(leagueId);
		model.addAttribute("matchs", matchs);
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

}
