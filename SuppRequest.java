package com.demo.spring;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import net.bytebuddy.implementation.bind.annotation.Super;

@Entity
@Table(name="SUPP_REQUESTS")
public class SuppRequest {
	
	@Id
	@Column(name="REQUEST_ID")
	private int requestid;
	private String email;
	private String software;
	private String os;
	private String problem;
	public String getEmail() {
		return email;
	}
	
	
	public SuppRequest() {
	}

	public int getRequestid() {
		return requestid;
	}


	public void setRequestid(int requestid) {
		this.requestid = requestid;
	}
	public SuppRequest(String email, String software, String os, String problem) {
		super();
		this.email = email;
		this.software = software;
		this.os = os;
		this.problem = problem;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSoftware() {
		return software;
	}
	public void setSoftware(String software) {
		this.software = software;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	

}
