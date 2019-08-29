package com.demo.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.Customer;

public interface CustJpaRepository extends JpaRepository<Customer, Integer> {

}
