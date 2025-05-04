package com.harmonia.HmAuthService.model;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AccessJwtProvider {
	private static PrivateKey privateKey;
	private static PublicKey publicKey;
	private static final Long EXPIRATION_TIME = 3_600_000L;//2_592_000_000L; TODO
	
	public AccessJwtProvider(@Value("${access-token-private-key}") String privateAccessKey, @Value("${access-token-public-key}") String publicAccessKey) {
		KeyFactory keyFactory;
		try {
			keyFactory = KeyFactory.getInstance("EC");
			privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateAccessKey.getBytes())));
			publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicAccessKey.getBytes())));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean validateSigningToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token.replaceAll("Bearer ", ""));
			return true;
		} catch(JwtException e) {
			return false;
		}
	}
	
	public static String generateToken(String data, String claimName) {
		return "Bearer " + Jwts.builder().claim(claimName, data).setIssuedAt(new Date())
												 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
												 .signWith(privateKey, SignatureAlgorithm.ES256)
												 .compact();
	}
	
	public static String generateToken(Map<String, Object> claims) {
		
		return "Bearer " + Jwts.builder().setClaims(claims).setIssuedAt(new Date())
												 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
												 .signWith(privateKey, SignatureAlgorithm.ES256)
												 .compact();
	}
	
	public static String getData(String token, String claimName) {
		Map<String, Object> map = Jwts.parserBuilder()
		.setSigningKey(publicKey)
		.build()
		.parseClaimsJws(token.replaceAll("Bearer ", ""))
		.getBody();
		return map.get(claimName).toString();
	}
	
	public static Map<String, Object> getData(String token) {
		Map<String, Object> map = Jwts.parserBuilder()
		.setSigningKey(publicKey)
		.build()
		.parseClaimsJws(token.replaceAll("Bearer ", ""))
		.getBody();
		return map;
	}
}
