package com.demo.spring;

public class EmpClassNotFound extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmpClassNotFound() {
		super("Emp id not found");
	}

}
