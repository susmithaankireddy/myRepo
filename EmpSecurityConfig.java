/*package com.demo.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class EmpSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	DataSource datasource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/check")
		.permitAll()
		.antMatchers("/update**")
		.hasRole("USER")
		.antMatchers("/reg**")
		.hasRole("USER")
		.and().httpBasic().and().csrf().disable();
	}
	
/*	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("murthy")
		.password(passwordEncoder()
				.encode("12345")).roles("USER");
		
		auth.inMemoryAuthentication().withUser("harsha")
		.password("12345").roles("USER");
		
		auth.inMemoryAuthentication().withUser("jo")
		.password("12345").roles("USER").disabled(true);

		auth.inMemoryAuthentication().withUser("susmitha")
		.password("12345").roles("ADMIN");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      
		auth.jdbcAuthentication().dataSource(datasource)
		.authoritiesByUsernameQuery("select username,password,"
				+ "enabled from users where username=?")
		.authoritiesByUsernameQuery("select username,authority "
				+ "from authorities where username=?");
	
	}
	

	//@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
*/