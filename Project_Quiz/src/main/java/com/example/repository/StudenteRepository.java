package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Utente;

public interface StudenteRepository extends JpaRepository<Utente, Long>{
	
	

}

