package com.harmonia.HmAuthService.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="login", length=25, unique=true, nullable=false)
	private String login;
	@Column(name="email", length=319, unique=true, nullable=false)
	private String email;
	@Getter
	@Column(name="password", length=70, nullable=false)
	private String password;
	@Column(name="joined_at", nullable=false)
	private Date joinedAt;
	
	public User(String login, String email, String password, Date joinedAt) {
		this.login = login;
		this.email = email;
		this.password = password;
		this.joinedAt = joinedAt;
	}
}
