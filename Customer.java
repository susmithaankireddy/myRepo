package com.demo.spring;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMERS")
public class Customer {
	
	@Id
	@Column(name="EMAIL")
	private String email;
	@Column(name="FNAME")
	private String firstname;
	@Column(name="LNAME")
	private String lastname;
	@Column(name="PHONE")
	private String phonenumber;
	public Customer() {}
	public Customer(String email, String firstname, String lastname, String phonenumber) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

}
