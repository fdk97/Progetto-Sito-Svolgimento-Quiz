package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Linguaggio;

@Repository
public interface LinguaggioRepository extends JpaRepository<Linguaggio, Long>{
    
	//boolean existsByLinguaggio(String nomeArgomento);

	boolean existsByNomeArgomentoIgnoreCase(String nomeArgomento);
	
	
	
	
     
}
