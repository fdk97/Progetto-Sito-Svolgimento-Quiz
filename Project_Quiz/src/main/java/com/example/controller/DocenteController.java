package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.model.UserDetailsImpl;
import com.example.model.Utente;
import com.example.repository.RuoloRepository;
import com.example.repository.UtenteRepository;


@Controller
public class DocenteController {
	
	@Autowired
	UtenteRepository utenteRepository;
	
	@Autowired
	RuoloRepository ruoloRepository;
	
	 @GetMapping("/landingPageDocente")
	    public String showLandingPageDocente(Model model) {
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
	        return "landingPageDocente";  // Questo restituidce il template landingPageDocente.html
	    }

}
