package com.demo.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.SuppRequest;

public interface SuppJpaRepository extends JpaRepository<SuppRequest, Integer>{

}
