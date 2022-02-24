package fr.formation.inti.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.inti.entity.Role;
import fr.formation.inti.entity.User;
import fr.formation.inti.service.IRoleService;
import fr.formation.inti.service.IUserService;

@Controller
public class Login {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	@GetMapping("/inscription")
	public String inscription(Model model) {
		List<Role> roles= roleService.findAll();
		model.addAttribute("roles", roles);
		model.addAttribute("user", new User());
		
		return "inscription";
	}
	
	@PostMapping("/inscription")
	public String saveUser(@RequestParam String confirmPass ,@Valid User user, BindingResult result, Model model) {
	    if(!confirmPass.equals(user.getPassword()) ) {
	    	model.addAttribute("passwordNotEqual", "Veillez vérifier votre mot de passe");
	    	return "inscription";
	    }
	    if (result.hasErrors()) 
	    	return "inscription";
	    User userExist = userService.findByEmail(user.getEmail());
	    if(userExist!=null) {
	    	model.addAttribute("emailUsed", "Un compte existe déjà avec cette addresse mail");
	    	return "inscription";
	    }
	    model.addAttribute("user", user);
	    model.addAttribute("success", "Votre compte a bien été enregistré");
	    userService.saveUser(user);
	
	    return "login";
	}
	
	


	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPost(Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
		User user = userService.findByEmailAndPassword(email, password);
		
		if(user!=null) {
			model.addAttribute("success", "Vous êtes bien connecté");
			return "index";
		}
		model.addAttribute("fail", "Vous avez saisi des mauvais identifiants");	
		return "login";
	}
	
	
}
