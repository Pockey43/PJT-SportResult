package fr.formation.inti.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import fr.formation.inti.entity.Matchs2;

@RestController
@RequestMapping("/api")
public class ApiTest {
	
	@GetMapping("/")
	private String allMatch(Model model) throws UnirestException, JsonMappingException, JsonProcessingException {
		HttpResponse<String> client = Unirest
				.get("https://api-football-v1.p.rapidapi.com/v3/fixtures?id=710679")
				.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
				.header("x-rapidapi-key", "51b09b1246msh0cc2257e3f23c9bp16944fjsn2f6815b57387").asString();
		
		String match = client.getBody();
		ObjectMapper objmap = new ObjectMapper();
		objmap.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		String carJson =
			    "{ \"id\" : \"5\", \"date\" : 20100, \"referee\" : 2000,\"score\" : 25}";
		
		Matchs2 matchs = objmap.readValue(match, Matchs2.class);
		model.addAttribute("matchs", matchs);
		System.out.println(matchs+"=================================="+match);
		return "apitest";
	}

}
