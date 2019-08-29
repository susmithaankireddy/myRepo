package com.demo.spring;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.demo.spring.Customer;

@Component
public class CustomerValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(Customer.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Customer customer = (Customer)target;
		if(customer.getFirstname().isEmpty()) {
			errors.rejectValue("firstname", "firstname", "First Name cannot be empty.");
		}
		if(customer.getLastname().isEmpty()) {
			errors.rejectValue("lastname", "lastname", "Second Name cannot be empty.");
		}
		if(customer.getPhonenumber().isEmpty() || customer.getPhonenumber().length() < 10) {
			errors.rejectValue("phonenumber", "phonenumber", "Number not valid.");
		}
	}

}
