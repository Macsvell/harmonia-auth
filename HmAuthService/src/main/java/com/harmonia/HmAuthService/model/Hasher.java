package com.harmonia.HmAuthService.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class Hasher {
	private static MessageDigest messageDigest;
	
	public Hasher() {
		try {
			messageDigest = MessageDigest.getInstance("SHA256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static String hash(String text) {
		return new String(messageDigest.digest(text.getBytes()));
	}
}
