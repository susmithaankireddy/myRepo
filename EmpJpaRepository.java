package com.demo.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.Emp;
import com.demo.spring.SuppRequest;

public interface EmpJpaRepository extends JpaRepository<Emp, Integer>{
	
	

}
