package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.Quiz_UtenteRepository;

@Service
public class Quiz_UtenteService {
	
	@Autowired
	private Quiz_UtenteRepository quiz_UtenteRepository;
	
    public boolean checkIfQuizUtenteExists(Long id_quiz, Long id_utente) {
        return quiz_UtenteRepository.existsByIdQuizAndIdUtente(id_quiz, id_utente);
    }
	
	

}
