package com.prasad.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prasad.security.models.Role;
import com.prasad.security.models.User;
import com.prasad.security.models.dtos.AuthenticationRequest;
import com.prasad.security.models.dtos.AuthenticationResponse;
import com.prasad.security.models.dtos.RegisterRequest;
import com.prasad.security.repositories.UserRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(RegisterRequest registerReq) {
		User user = new User();
		user.setFisrtName(registerReq.getFirstname());
		user.setLastName(registerReq.getLastname());
		user.setEmail(registerReq.getEmail());
		user.setPassword(passwordEncoder.encode(registerReq.getPassword()));
		user.setRole(Role.USER);
		userRepository.save(user);
		String token = jwtService.generateToken(user);
		return new AuthenticationResponse(token);
	}

	public AuthenticationResponse authenticate(AuthenticationRequest authReq) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword()));
		User user = userRepository.findByEmail(authReq.getEmail()).orElseThrow();
		String token = jwtService.generateToken(user);
		return new AuthenticationResponse(token);
	}

	
}
