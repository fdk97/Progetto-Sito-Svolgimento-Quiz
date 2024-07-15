package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.model.UserDetailsImpl;
import com.example.model.Utente;
import com.example.repository.RuoloRepository;
import com.example.repository.UtenteRepository;
import com.example.service.UtenteService;

@Controller
public class UtenteController {
	
	@Autowired
	UtenteRepository utenteRepository;
	
	@Autowired
	RuoloRepository ruoloRepository;
	
    @Autowired
    private UtenteService utenteService;
    

    
    // richiesta GET a "/modificaStudente"
    @GetMapping("/modificaStudente")
    public String getAllUtenti(Model model) {
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
		 Utente studenti = new Utente();
		
	model.addAttribute("connesso",connesso);
	model.addAttribute("ruolo",ruolo);					//////////////////////////////////// FINE
        // Aggiunge un nuovo oggetto Utente al modello per il form di inserimento/modifica
        model.addAttribute("Studenti", studenti);
        // Recupera tutti gli utenti dal servizio e li aggiunge al modello per la visualizzazione
        model.addAttribute("Utente", utenteService.getAllUtenti());
        // Ritorna il nome della vista da visualizzare (modificaStudente.html in questo caso)
        return "modificaStudente";
    }

    //  richiesta POST a "/modificaStudente" per aggiornare un utente
    @PostMapping("/modificaStudente")
    public String updateUtente(@ModelAttribute("Studenti") Utente Studenti,
//            @RequestParam("id") Long id,
//            @RequestParam("nome") String nome,
//            @RequestParam("cognome") String cognome,
//            @RequestParam("mail") String mail,
//            @RequestParam("ruolo") Long ruolo,
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

//    	System.out.println("DATI PROVENTINTI DAL GETMAPPING -> " + Studenti.getId() + Studenti.getNome() + Studenti.getCognome() + Studenti.getMail() + Studenti.getRuolo());
    	
    	System.out.println("-------- SONO NEL POST MAPPING ---------");
//    	
//        // Verifica che i parametri non siano vuoti prima di procedere
        if (Studenti.getNome().isEmpty() || Studenti.getCognome().isEmpty() || Studenti.getMail().isEmpty()) {
        	
        	System.out.println("------- CONTROLLO SE I CAMPI SONO VUOTI ---------");
        	
            // Se uno dei parametri è vuoto, aggiunge un messaggio di errore al modello
            model.addAttribute("error", "Tutti i campi sono obbligatori.");
            
            System.err.println("-------- ERRORE CAMPI VUOTI ---------");
            
            					// Ricarica gli utenti nel modello
            					model.addAttribute("Utente", utenteService.getAllUtenti());
            
            // Ritorna alla pagina di modifica con il messaggio di errore
            return "modificaStudente";
        }

        System.err.println("------ SONO FUORI IL TRY / CATCH -------");
        // Chiamata al servizio per aggiornare l'utente nel database
        System.out.println(Studenti);

        try {
        	System.out.println("------- TRY -> PROVO AD AGGIORNARE I DATI -------");
            utenteRepository.updateUtente(Studenti.getId(), Studenti.getNome(), Studenti.getCognome(), Studenti.getMail(), Studenti.getRuolo());
            // Se l'aggiornamento va a buon fine, reindirizza alla pagina principale per il docente
         // Aggiunge un messaggio di successo al modello
            model.addAttribute("success", "Utente modificato con successo.");
            // Stampa dati utente aggiornati
            					// Ricarica gli utenti nel modello
            					model.addAttribute("Utente", utenteService.getAllUtenti());
//            System.out.println("UTENTE AGGIORNATO CON SUCCESSO: \n" + "[ id: " + id + " ]" + "[ nome: " + nome + " ]" + "[ cognome: " + cognome + " ]" + "[ mail: " + mail + "]" + "[ ruolo: " + ruolo + "]");
            System.out.println("REGISTRAZIONE AVVENUTA CON SUCECSSO");
            return "modificaStudente";
        } catch (Exception e) {
        	
        	System.out.println("------ CATCH -> SONO NEL CATCH: ERRORE");
//        	
//            // Se si verifica un'eccezione durante l'aggiornamento, gestisce l'errore
            model.addAttribute("error", "Si è verificato un errore durante l'aggiornamento dell'utente.");
            // Ritorna alla pagina di modifica con un messaggio di errore
            return "modificaStudente";
        }
    	
    }
}
    


