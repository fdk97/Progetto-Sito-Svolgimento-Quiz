package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="quiz_utenti")
public class Quiz_Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Long id_quiz;
	@Column
	private Long id_utente;
	@Column
	private boolean completato;
	
	
	public Quiz_Utente() {}
	
	
	public Quiz_Utente(Long id_quiz, Long id_utente, boolean completato) {
		setId_quiz (id_quiz);
		setId_utente (id_utente);
		setCompletato (completato);
	}
	
	public Long getId_quiz() {
		return id_quiz;
	}



	public void setId_quiz(Long id_quiz) {
		this.id_quiz = id_quiz;
	}



	public Long getId_utente() {
		return id_utente;
	}



	public void setId_utente(Long id_utente) {
		this.id_utente = id_utente;
	}


	public boolean isCompletato() {
		return completato;
	}



	public void setCompletato(boolean completato) {
		this.completato = completato;
	}



	
	@Override
	public String toString() {
		return "Quiz_Utente [id_quiz=" + id_quiz + ", id_utenti=" + id_utente + ", completato=" + completato + "]";
	}


	

}
