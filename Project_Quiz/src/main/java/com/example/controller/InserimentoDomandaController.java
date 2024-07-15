package com.example.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Domanda_Risposta;
import com.example.model.Linguaggio;
import com.example.model.UserDetailsImpl;
import com.example.model.Utente;
import com.example.repository.RuoloRepository;
import com.example.repository.UtenteRepository;
import com.example.service.DomandaRispostaService;
import com.example.service.LinguaggioService;



@Controller
public class InserimentoDomandaController {
	
	
	@Autowired
    private LinguaggioService linguaggioService;
	
	@Autowired
	private DomandaRispostaService domandaRispostaService;
	
	@Autowired
	UtenteRepository utenteRepository;
	
	@Autowired
	RuoloRepository ruoloRepository;
	

	// Mostra il form di registrazione
    @GetMapping("/inserimentoDomanda")
    public String mostraFormInserimentoDomande(Model model) {
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
    	
    	
        model.addAttribute("formDomande", new Domanda_Risposta());
        model.addAttribute("linguaggi", linguaggioService.getAllLinguaggi()); 

        
        System.out.println("Sono nel controller GET");
                       
               
        return "inserimentoDomanda";
    }
    
    @PostMapping("/inserimentoDomanda")
    public String inserimentoDomande(
    		@RequestParam("linguaggio.id") Long linguaggioId,
            @RequestParam("domanda") String domanda,
            @RequestParam("risposta_uno") String risposta_uno,
            @RequestParam("risposta_due") String risposta_due,
            @RequestParam("risposta_tre") String risposta_tre,
            @RequestParam("risposta_quattro") String risposta_quattro,
            @RequestParam(name = "risposta_esatta", required = false) Integer risposta_esatta,
            RedirectAttributes redirectAttributes, Model model) {
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

    	
        // Recupera il linguaggio selezionato dal database
      Optional<Linguaggio> linguaggio = linguaggioService.getLinguaggioById(linguaggioId);
      if (!linguaggio.isPresent()) {
          // Gestione errore se il linguaggio non esiste
          System.out.println("Linguaggio non trovato!");
          redirectAttributes.addFlashAttribute("messaggio", "Errore: Linguaggio non trovato!");
          return "inserimentoDomanda"; // O un'altra pagina di errore
      }

      // Validazione dei campi
      if (domanda == null || domanda.trim().isEmpty() ||
          risposta_uno == null || risposta_uno.trim().isEmpty() ||
          risposta_due == null || risposta_due.trim().isEmpty() ||
          risposta_tre == null || risposta_tre.trim().isEmpty() ||
          risposta_quattro == null || risposta_quattro.trim().isEmpty() ||
          risposta_esatta == null) {
          redirectAttributes.addFlashAttribute("messaggio", "Errore: Tutti i campi devono essere compilati e una risposta corretta deve essere selezionata!");
          return "redirect:/inserimentoDomanda";
      }
    	
      
      
        System.out.println("Creo l'oggetto nuovaDomanda");
        Domanda_Risposta nuovaDomanda = new Domanda_Risposta();
        System.out.println("Oggetto nuovaDomanda creato");
        
        

        nuovaDomanda.setLinguaggio(linguaggio.get());
        nuovaDomanda.setDomanda(domanda);
        nuovaDomanda.setRisposta_uno(risposta_uno);
        nuovaDomanda.setRisposta_due(risposta_due);
        nuovaDomanda.setRisposta_tre(risposta_tre);
        nuovaDomanda.setRisposta_quattro(risposta_quattro);
        nuovaDomanda.setRisposta_esatta(risposta_esatta);

        
        domandaRispostaService.salvaInserimentoDomandaRisposta(nuovaDomanda);
     

        redirectAttributes.addFlashAttribute("messaggio", "Domanda inserita correttamente!");
        System.out.println("--------------------------------");
        System.out.println("Linguaggio selezionato nel form: " + linguaggio.get());
        System.out.println("--------------------------------");
        System.out.println("Domande inserite nel form: " + nuovaDomanda);

        return "redirect:/inserimentoDomanda";
    }


}
