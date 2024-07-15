package com.example.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.model.Quiz_Domanda;

import jakarta.transaction.Transactional;

@Repository
public interface Quiz_DomandaRepository extends JpaRepository<Quiz_Domanda, Long>{
	
	// Studente
	@Query("select q from Quiz_Domanda q Where q.id_quiz = :id") 
	List<Quiz_Domanda> findByid_quiz(@Param("id") Long id);
	
	
	// Docente
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO Quiz_Domande (id_domanda, id_quiz) VALUES (:idDomanda, :idQuiz)", nativeQuery = true)
	void aggiungiQuiz(@Param("idQuiz") Long idQuiz, @Param("idDomanda") Long idDomanda);

}
