package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Linguaggio;
import com.example.model.UserDetailsImpl;
import com.example.model.Utente;
import com.example.repository.LinguaggioRepository;
import com.example.repository.RuoloRepository;
import com.example.repository.UtenteRepository;
import com.example.service.LinguaggioService;

@Controller
public class LinguaggioController {

	@Autowired
	private LinguaggioService linguaggioService;

	@Autowired
	UtenteRepository utenteRepository;

	@Autowired
	RuoloRepository ruoloRepository;

	@Autowired
	LinguaggioRepository linguaggioRepository;

	@GetMapping("/inserimentoLinguaggio")
	public String mostraInserimentoLinguaggio(Model model) {
		String mail = null; /////////////////////////////////// INIZIO
		String ruolo = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean connesso = true;
		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			if (principal.equals("anonymousUser")) {
				connesso = false; /// METODO PER NAVBAR
			}
			if (principal instanceof UserDetails) {
				UserDetails user = (UserDetails) principal;
				UserDetailsImpl user2 = (UserDetailsImpl) user;
				mail = user2.getUsername();
				Utente utente = utenteRepository.findBymail(mail);
				ruolo = ruoloRepository.findByid(utente.getRuolo());
			}
		}

		model.addAttribute("connesso", connesso);
		model.addAttribute("ruolo", ruolo); //////////////////////////////////// FINE

		// model.addAttribute("utenti", utenteService.getAllUtenti());
		model.addAttribute("linguaggi", new Linguaggio());
		System.out.println("Sono nel GET di inserimentoLinguaggio");

		return "inserimentoLinguaggio";
	}

	@PostMapping("/inserimentoLinguaggio")
	public String inserimentoLinguaggio(@RequestParam("nomeArgomento") String nomeArgomento,
            Model model) {
		String mail = null;								/////////////////////////////////// INIZIO
		String ruolo = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean connesso = true;
		 if (authentication != null && authentication.isAuthenticated()) {							
			    Object principal = authentication.getPrincipal();
			    if (principal.equals("anonymousUser")) {	
			    	connesso = false;													/// METODO PER NAVBAR
			  } 	
			    if (principal instanceof UserDetails) {
				      UserDetails user = (UserDetails) principal;
				      UserDetailsImpl user2 = (UserDetailsImpl) user;
				       mail = user2.getUsername(); 
				       Utente utente = utenteRepository.findBymail(mail);
				       ruolo = ruoloRepository.findByid(utente.getRuolo());
				    } 
		 }
		
	model.addAttribute("connesso",connesso);
	model.addAttribute("ruolo",ruolo);					//////////////////////////////////// FINE
		
		boolean linguaggioPresente = linguaggioService.existsByNomeArgomentoIgnoreCase(nomeArgomento);

            if (linguaggioPresente) {
                model.addAttribute("messaggio", "Errore:Linguaggio non inserito perché è già presente nel database");
            } else {
                Linguaggio nuovoLinguaggio = new Linguaggio();
                nuovoLinguaggio.setNomeArgomento(nomeArgomento);
                linguaggioService.salvaLinguaggio(nuovoLinguaggio);
                model.addAttribute("messaggio", "Linguaggio inserito con successo");
            }

            // Aggiungi altri attributi necessari al modello
            model.addAttribute("linguaggi", new Linguaggio());

            // Ritorna il nome della vista (template Thymeleaf)
            return "inserimentoLinguaggio";
        }
}