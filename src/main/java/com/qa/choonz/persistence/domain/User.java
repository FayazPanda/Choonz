package com.qa.choonz.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.sun.istack.NotNull;

@Entity
@Data
@NoArgsConstructor
public class User {

	// Id
	// username
	// password
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;

	public User(long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
}
