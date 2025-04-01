package com.harmonia.HmAuthService.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name="habit")
public class Habit {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="name", length=30, nullable=false)
	private String name;
	@Column(name="time", length=5, nullable=false)
	private String time;
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	private Replay replay;
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	private User user;
	
}
