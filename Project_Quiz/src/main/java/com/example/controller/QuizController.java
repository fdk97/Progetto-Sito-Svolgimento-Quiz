package com.example.controller;

import java.util.ArrayList;
import java.util.List;
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

import com.example.model.Linguaggio;
import com.example.model.Quiz;
import com.example.model.UserDetailsImpl;
import com.example.model.Utente;
import com.example.repository.RuoloRepository;
import com.example.repository.UtenteRepository;
import com.example.service.LinguaggioService;
import com.example.service.QuizService;
import com.example.service.Quiz_UtenteService;
import com.example.service.UtenteService;

@Controller
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private Quiz_UtenteService quiz_UtenteService;
	
	@Autowired	
	private UtenteService utenteService;
	
	@Autowired
	private LinguaggioService linguaggioService;
	
	@Autowired
	UtenteRepository utenteRepository;
	
	@Autowired
	RuoloRepository ruoloRepository;
	
	@GetMapping("/assegnazioneQuizStudente")
	public String mostraAssegnazioneQuizStudente(Model model) {
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
		
		model.addAttribute("quizzes", quizService.getAllQuizzes());
		model.addAttribute("utenti", utenteService.getAllUtenti());
		
		return "assegnazioneQuizStudente";
	}
	
	@PostMapping("/assegnazioneQuizStudente")
	public String assegnazioneQuizStudente(
			@RequestParam Long quizId, 
			@RequestParam List<Long> utenteIdList,
			Model model
			) {
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
		
        List<String> successMessages = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();
		
        for (Long utenteId : utenteIdList) {
        	
        	System.out.println("QUIZ -> " + quizId + " | UTENTE -> " + utenteId);
        	
            boolean exists = quiz_UtenteService.checkIfQuizUtenteExists(quizId, utenteId);      
            
            if (exists) {
            	errorMessages.add("Studente già assegnato al quiz selezionato");
//            	model.addAttribute("errorMessages", errorMessages);
//                model.addAttribute("quizzes", quizService.getAllQuizzes());
//                model.addAttribute("utenti", utenteService.getAllUtenti());
//               return "assegnazioneQuizStudente";
            } else {
            	quizService.assegnaQuiz(quizId, utenteIdList);
            	
            	Utente utente = utenteService.findUtenteById(utenteId);
            	Optional<Quiz> quiz = quizService.getQuizById(quizId);
            	
            	Optional<Linguaggio> linguaggio = linguaggioService.getLinguaggioById(quiz.get().getId_linguaggio());
            	
            	String nomeLinguaggio = linguaggio.get().getNomeArgomento();
            	
            	System.out.println("sono nel controller | LINGUAGGIO QUIZ -> " + nomeLinguaggio);

            	successMessages.add(utente.getNome() + " " + utente.getCognome() + " <" + utente.getMail() + "> assegnato a " + nomeLinguaggio + " QUIZ " + quiz.get().getId());
            	
            }
        }
        if (!successMessages.isEmpty()) {
        	model.addAttribute("successMessages", successMessages);
        	System.err.println("Messaggio di successo INVIATO");
        }	
        if (!errorMessages.isEmpty()) {
        	model.addAttribute("errorMessages", errorMessages);
        	System.err.println("Messaggio di errore INVIATO");
        }	
        	model.addAttribute("quizzes", quizService.getAllQuizzes());
        	model.addAttribute("utenti", utenteService.getAllUtenti());
		
		return "assegnazioneQuizStudente";
	}
	
	
    @GetMapping("/creaQuiz")
    public String showCreaQuizPage(Model model) {
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
        List<Linguaggio> linguaggi = linguaggioService.getAllLinguaggi();
        model.addAttribute("linguaggi", linguaggi);
        return "creaQuiz"; 
    }
	
    @PostMapping("/creaQuiz")
    public String createQuizWithRandomDomande(@RequestParam("linguaggioId") Long linguaggioId,Model model) {
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
    	
    	System.err.println("entro nel POSTMAPPING");
    	
        try {
        	System.err.println("entro nel TRY");
            // Crea un nuovo quiz associato al linguaggio e assegna 10 domande casuali
            quizService.createQuizWithRandomDomande(linguaggioId);
            System.err.println("quiz creato");
            List<Linguaggio> linguaggi = linguaggioService.getAllLinguaggi();
            model.addAttribute("linguaggi", linguaggi);
            model.addAttribute("successMessage", "Quiz creato con successo");
            return "creaQuiz"; 
        } catch (Exception e) {
        	List<Linguaggio> linguaggi = linguaggioService.getAllLinguaggi();
            model.addAttribute("linguaggi", linguaggi);
            model.addAttribute("errorMessage", "Si è verificato un errore durante la creazione del quiz.");
            return "creaQuiz";
        }
    }

}
