package com.springboot.rest.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity
@Table(name="user")
//@JsonIgnoreProperties({"id","firstname"})
//@JsonFilter(value="userFilter")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="username", length=50, nullable=false, unique=true)
	@NotEmpty(message="Username must not be empty. Please provide the username.")
	private String username;
	
	@Size(min=2,message="Firstname must have atleast 2 characters.")
	@Column(name="firstname", length=50, nullable=false)
	private String firstname;
	
	@Column(name="lastname", length=50, nullable=false)
	private String lastname;
	
	@Column(name="email", length=50, nullable=false)
	private String email;
	
	@Column(name="role", length=50, nullable=false)
	private String role;
	
	@Column(name="ssn", length=50, nullable=false)
	private String ssn;

	@OneToMany(mappedBy="user")
	private List<Order> orders;
	
	public User() {
		super();
	}


	public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getSsn() {
		return ssn;
	}


	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
	
	
}
