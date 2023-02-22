package com.prasad.security.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/hello")
	public String sayHello() {
		return "hello";
	}
	
}
