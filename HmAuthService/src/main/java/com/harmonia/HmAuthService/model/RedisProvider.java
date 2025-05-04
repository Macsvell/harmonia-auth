package com.harmonia.HmAuthService.model;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
public class RedisProvider {
	private static final String HOST = "localhost";
	private static final int PORT = 6379;
	private static Jedis jedis;
	
	public RedisProvider() {
		jedis = new Jedis(HOST, PORT);
	}
	
	public static Jedis get() {
		return jedis;
	}
}
