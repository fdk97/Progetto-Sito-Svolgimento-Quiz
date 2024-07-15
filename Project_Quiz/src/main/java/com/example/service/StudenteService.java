package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Quiz_Utente;
import com.example.repository.Quiz_UtenteRepository;

@Service
public class StudenteService {
	
	@Autowired
	private Quiz_UtenteRepository quiz_UtenteRepository;
	
	public List<Long> trovaQuizDisponibili(Long id){
		
		List<Long> quizUtente = quiz_UtenteRepository.findByid(id);
		

		return quizUtente;
	
	}
	
	
	
	public void updateCompletato(Long id_utenti, Long id_quiz) {
        quiz_UtenteRepository.updateCompletato(id_utenti,id_quiz);
    }
	

}
