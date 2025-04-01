package com.harmonia.HmAuthService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthService {

	@GetMapping("/check/email")
	public ResponseEntity<Object> checkEmail() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
	
	@GetMapping("/check/password")
	public ResponseEntity<Object> checkPassword() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
	
	@PostMapping("/registry")
	public ResponseEntity<Object> registry() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
	
	@PostMapping("/otp/send/email")
	public ResponseEntity<Object> sendOtpCode() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
	
	@PostMapping("otp/verify")
	public ResponseEntity<Object> verifyOtpCode() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
}
