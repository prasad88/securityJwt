package com.prasad.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasad.security.models.dtos.AuthenticationRequest;
import com.prasad.security.models.dtos.AuthenticationResponse;
import com.prasad.security.models.dtos.RegisterRequest;
import com.prasad.security.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerReq) {
		return ResponseEntity.ok(authenticationService.register(registerReq));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authReq) {
		return ResponseEntity.ok(authenticationService.authenticate(authReq));
	}
}
