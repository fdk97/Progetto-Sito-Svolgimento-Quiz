package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Ruolo;

public interface RuoloRepository extends JpaRepository<Ruolo, Long>{

	@Query("select ruolo from Ruolo r Where r.id = :id") 
	String findByid(@Param("id") long id);
}
