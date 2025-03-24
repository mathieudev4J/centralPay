package com.spring.services;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.UriComponents;



@RestController
@RequestMapping("/api")
public class UrlShortController {
	

	@GetMapping("/coucou")
	public String url() {
		return new String("coucou");

	}
	
     
}
