package fr.formation.inti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping({"/","/index"})
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/calendrier")
	public String calendrier(Model model) {
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
	
	@GetMapping("/inscription")
	public String inscription() {
		return "inscription";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
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
