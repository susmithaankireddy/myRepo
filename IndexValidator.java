package com.demo.spring;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.demo.spring.SuppRequest;

@Component
public class IndexValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(SuppRequest.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		SuppRequest suppRequests = (SuppRequest)target;
		if(suppRequests.getEmail().isEmpty() || suppRequests.getEmail().equals(" ")) {
			errors.rejectValue("email","email", "*invalid mail");
		}
		if(suppRequests.getOs().isEmpty()) {
			errors.rejectValue("os","os", "*invalid text");
		}
		if(suppRequests.getSoftware().isEmpty()) {
			errors.rejectValue("software","software", "*invalid software");
		}
	}

}
