package com.harmonia.HmAuthService.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="email", length=319, unique=true, nullable=false)
	private String email;
	@Column(name="password", length=70, nullable=false)
	private String password;
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Task> tasks = new ArrayList<>();
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Habit> habits = new ArrayList<>();
}
