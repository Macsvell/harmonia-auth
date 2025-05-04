package com.harmonia.HmAuthService.model;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EmailOtpService {
	private JavaMailSender sender;
	private static final int EXPIRE = 5 * 60;
	
	public EmailOtpService(@Autowired JavaMailSender sender) {
		this.sender = sender;
	}
	
	public void send(String email) throws MailException {
		int code = generateCode();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Harmonia:OTP-code");
		message.setText("Ваш одноразовый код для подтверждения почты : " + code);
		sender.send(message);
		RedisProvider.get().setex(Hasher.hash(email), EXPIRE, BCrypt.hashpw(String.valueOf(code), BCrypt.gensalt()));
	}
	
	public boolean verify(String email, String code) {
		String hashCode = RedisProvider.get().get(Hasher.hash(email));
		if(hashCode == null) return false;
		boolean isValid = BCrypt.checkpw(code, hashCode);
		if(isValid) RedisProvider.get().del(Hasher.hash(email));
		return isValid;
	}
	
	private int generateCode() {
		return new Random().nextInt(899_999)+100_000;
	}
	
}
