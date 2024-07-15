package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="quiz_domande")
public class Quiz_Domanda {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long id_quiz;

	@Column
	private Long id_domanda;
	

	
	public Quiz_Domanda() {}

	public Quiz_Domanda(Long id_domanda, Long id_quiz) {
		setId_domanda (id_domanda);
		setId_quiz (id_quiz);
	}

	public Long getId_domanda() {
		return id_domanda;
	}

	public void setId_domanda(Long id_domanda) {
		this.id_domanda = id_domanda;
	}

	public Long getId_quiz() {
		return id_quiz;
	}

	public void setId_quiz(Long id_quiz) {
		this.id_quiz = id_quiz;
	}

	@Override
	public String toString() {
		return "Quiz_Domanda [id_domanda=" + id_domanda + ", id_quiz=" + id_quiz + "]";
	}
	
	
	
	
}
