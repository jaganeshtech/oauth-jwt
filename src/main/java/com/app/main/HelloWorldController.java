package com.app.main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping("/book")
	public String book() {
		return "{\"eBooks\":[{\"language\":\"Pascal\",\"edition\":\"third\"},{\"language\":\"Python\",\"edition\":\"four\"},{\"language\":\"SQL\",\"edition\":\"second\"}]}";
	}
	
	
	@RequestMapping("/student")
	public String student() {
		return "{\"student\":[{\"id\":\"01\",\"name\":\"Tom\",\"lastname\":\"Price\"},{\"id\":\"02\",\"name\":\"Nick\",\"lastname\":\"Thameson\"}]}";
	}
	
	
	

}
