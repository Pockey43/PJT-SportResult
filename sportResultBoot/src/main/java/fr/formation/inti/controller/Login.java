package fr.formation.inti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Login {
	
	@PostMapping("/login")
	public String login(Model model) {
		model.addAttribute("success", "Votre compte a bien été créé");
		return "login";
	}
	
	
}
