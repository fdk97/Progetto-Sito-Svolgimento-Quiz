package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Quiz_Utente;

import jakarta.transaction.Transactional;
@Repository
public interface Quiz_UtenteRepository extends JpaRepository<Quiz_Utente, Long>{
	
	
	@Query("select id_quiz q from Quiz_Utente q Where completato = false and q.id_utente = :id") 
	List<Long> findByid(@Param("id") Long id);	

	@Modifying
	@Transactional
	@Query("update Quiz_Utente q set q.completato = true where q.id_utente = :id_utente and q.id_quiz = :id_quiz")
	void updateCompletato(@Param("id_utente") Long id_utente, @Param("id_quiz") Long id_quiz);
	
    @Query("SELECT CASE WHEN COUNT(q) > 0 THEN true ELSE false END FROM Quiz_Utente q WHERE q.id_quiz = :id_quiz AND q.id_utente = :id_utente")
    boolean existsByIdQuizAndIdUtente(@Param("id_quiz") Long id_quiz, @Param("id_utente") Long id_utente);
	
}
