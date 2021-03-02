package com.example.demo.model;

import javax.annotation.Resource;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "Chess_Users")
@SequenceGenerator(name = "user_seq", initialValue = 1, allocationSize = 1)

@NamedQueries({
	@NamedQuery(name = "findByUserID", query = "SELECT u FROM User u WHERE u.id = :uIdParam"),
	@NamedQuery(name = "findByUsername", query = "SELECT u FROM User u WHERE u.username = :uUsernameParam")
})

public class User {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO , generator = "user_seq")
	private long id;
	
	@Resource
	private String username;
	
	@Resource
	private String password;
	
	@Resource
	private String email;
	
	private boolean loggedIn;
	private boolean activationStatus;
	
	public User(){
		super();
	}
	
	public User( String username, String password, String email){
		this.username = username;
		this.password = password;
		this.email = email;
		this.loggedIn = false;
		this.activationStatus = true;
	}
	
	public User(String username, String password){
		this.password = password;
		this.username = username;
	}
	
	public User(String username){
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean isActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}
	

}
