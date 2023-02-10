package com.mobi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping("/home")
	public String welcome() {
		return "The name is Bond, James Bond.";
	}
	
	@RequestMapping("/admin")
	public String admin() {
		return "Hey ADMIN!!!!!";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "Hey USER!!!!";
	}
	
	@RequestMapping("/onlyuser")
	public String onlyUser() {
		return "Only User!!!";
	}
	
}