package com.harmonia.HmAuthService.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harmonia.HmAuthService.dao.UserRepository;
import com.harmonia.HmAuthService.model.EmailOtpService;
import com.harmonia.HmAuthService.model.entity.User;

@RequestMapping("/auth")
@RestController
public class AuthController {
	private final UserRepository userRepository;
	private final EmailOtpService otpService;
	
	AuthController(UserRepository userRepository, @Autowired EmailOtpService otpService) {
		this.userRepository = userRepository;
		this.otpService = otpService;
	}
	
	@GetMapping("/check/username")
	public ResponseEntity<Boolean> checkUserName(@RequestParam("name") String userName) {
		if(userName.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		if(!userRepository.existsByLogin(userName)) return ResponseEntity.badRequest().body(false);
		return ResponseEntity.ok().body(true);
	}

	@GetMapping("/check/email")
	public ResponseEntity<Boolean> checkEmail(@RequestParam("email") String email) {
		if(email.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		if(!userRepository.existsByEmail(email)) return ResponseEntity.badRequest().body(false);
		return ResponseEntity.ok().body(true);
	}
	
	@GetMapping("/check/password")
	public ResponseEntity<Boolean> checkPassword(@RequestParam("name") String userName, @RequestParam("password") String password) {
		if(userName.isEmpty() || password.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		User user = userRepository.findByLogin(userName);
		if(user == null) return ResponseEntity.badRequest().body(false);
		if(!BCrypt.checkpw(password, user.getPassword())) return ResponseEntity.badRequest().body(false);
		return ResponseEntity.ok().body(true);
	}
	
	@PostMapping("/email/otp/send")
	public ResponseEntity<Object> sendOtpCode(@RequestParam("email") String email) {
		if(email.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		try {
			otpService.send(email);
		} catch(MailException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/email/otp/verify")
	public ResponseEntity<Object> verifyOtpCode(@RequestParam("email") String email, @RequestParam("code") String code) {
		if(email.isEmpty() || code.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		if(!otpService.verify(email, code)) return ResponseEntity.badRequest().build();
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/registry")
	public ResponseEntity<Object> registry(@RequestParam("name") String userName, @RequestParam("email") String email, @RequestParam("password") String password) {
		if(userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		if(userRepository.existsByLogin(userName)) return ResponseEntity.status(HttpStatus.CONFLICT).build();
		User newUser = new User(userName, email, BCrypt.hashpw(password, BCrypt.gensalt()), new Date());
		userRepository.save(newUser);
		return ResponseEntity.created(null).build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestParam("name") String userName, @RequestParam("password") String password) {
		if(userName.isEmpty() || password.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		if(!userRepository.existsByLogin(userName)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		if(!BCrypt.checkpw(password, userRepository.findByLogin(userName).getPassword())) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		return ResponseEntity.ok().build();
	}
}
