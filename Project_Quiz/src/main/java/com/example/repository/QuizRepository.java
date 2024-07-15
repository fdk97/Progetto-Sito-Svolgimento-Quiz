package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{
	
	
	@Query("select  q from Quiz q Where q.id = :id") 
	Quiz findByid(@Param("id") Long id);
	
	@Query("SELECT MAX(q.id) FROM Quiz q")
    Optional<Long> findMaxId();
	
}