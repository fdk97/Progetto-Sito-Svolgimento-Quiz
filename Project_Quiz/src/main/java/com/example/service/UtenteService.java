package com.example.service;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.model.Utente;
import com.example.repository.UtenteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    UtenteRepository utenteRepository;

    // Metodo per trovare un utente tramite l'ID
    public Utente findUtenteById(Long id) {
        return utenteRepository.findById(id).orElse(null);
    }

    // Metodo per trovare un utente tramite l'indirizzo email
    public Utente findUtenteByEmail(String mail) {
        return utenteRepository.findByMail(mail);
    }

    public boolean existsByMail(String mail) {
        return utenteRepository.existsByMail(mail);
    }

    // Metodo per recuperare tutti gli utenti presenti nel database
    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    // Metodo transazionale per aggiornare l'utente con un dato ID
    @Transactional
    public void updateUtente(Long id, String nome, String cognome, String mail, Long ruolo) {
    	
    	Optional<Utente> utente = Optional.of(utenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Utente non trovato")));;
    	
    	utente.get().setNome(nome);
    	utente.get().setCognome(cognome);
    	utente.get().setMail(mail);
    	utente.get().setRuolo(ruolo);
    	
        utenteRepository.save(utente);
    }
    
    public void salvaUtenteRegistrazione(Utente utente) {
    	
    	if(utente != null) {
    		if(utente.getCodDocente()!= null) {
    	if(utente.getCodiceDocente().equals("1234")) {
    		utente.setRuolo(1L);
    }
    		}
         utenteRepository.salvaUtente(utente.getRuolo(), utente.getNome(), utente.getCognome(), utente.getMail(), utente.getPassword_utente());
    	}
    	
    }
}