package com.example.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.exception.MyException;
import com.example.service.UtenteService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@ControllerAdvice
public class GlobalExceptionHandlerController {

	@Autowired
	UtenteService utenteService;
	
	
	private String getLoggedInUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
	
	@ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NoHandlerFoundException ex, Model model, HttpSession session) {
		
//		String username = getLoggedInUsername();
//		String nome = utenteService.findUtenteByEmail(username).getNome();
		
        model.addAttribute("errorMessage", "Pagina non trovata.");
//        session.setAttribute("nomeUtente", nome);
        return "notfoundview"; // Restituisci la vista della pagina di errore
    }
	
	
	
	
    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleMyException(MyException ex, Model model, HttpSession session) {
    	
//    	String username = getLoggedInUsername();
//		String nome = utenteService.findUtenteByEmail(username).getNome();
		
        model.addAttribute("errorMessage", ex.getMessage());
//        session.setAttribute("nomeUtente", nome);
        return "/errori.html"; // Restituisci la vista della pagina di errore
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericException(Exception ex, Model model, HttpSession session) {
    	
//    	String username = getLoggedInUsername();
//		String nome = utenteService.findUtenteByEmail(username).getNome();
		
        model.addAttribute("errorMessage", "Si è verificato un errore. Per favore riprova più tardi.");
//        session.setAttribute("nomeUtente", nome);
        return "/errori.html";
    }
}