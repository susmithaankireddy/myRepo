package com.demo.spring.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.spring.Greeting;


public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name",defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(),String.format(template, name));
	}

}
