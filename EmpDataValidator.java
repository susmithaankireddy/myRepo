package com.demo.spring;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EmpDataValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.equals(Customer.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		Customer c = (Customer)target;
		if(c.getEmail().isEmpty()) {
			errors.rejectValue("customer", "invalidId", "Id is not valid");
		}if(c.getFirstname().isEmpty()) {
			errors.rejectValue("customer", "invalidName", "Name should not be empty");
		}if(c.getLastname().isEmpty()) {
			errors.rejectValue("customer", "invalidName", "name should not be empty");
		}
		
	}

}
