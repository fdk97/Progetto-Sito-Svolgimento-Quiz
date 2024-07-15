//package com.example.model;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//// la tabella quiz_utenti è formata da due chiavi composte id_quiz e id_utente
//// Jpa non è in grado di fare alcune operazioni sulle chiavi composte ( memorizzazione sulla cache o altre operazioni specifiche )
//// per questo motivo chiede di Serializzare le chiavi composte cioè converte l'oggetto ( es. id_quiz ) in flusso di byte
//// fa le operazioni necessarie e poi lo riconverte in oggetto
//
//
//public class Quiz_UtenteId implements Serializable { 
//	
//	
//    private Long id_quiz;
//    private Long id_utente;
//
//    // Costruttore di default
//    public Quiz_UtenteId() {}
//
//    // Construttore parametrico
//    public Quiz_UtenteId(Long id_quiz, Long id_utente) {
//        this.id_quiz = id_quiz;
//        this.id_utente = id_utente;
//    }
//
//    // equals() and hashCode()
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Quiz_UtenteId that = (Quiz_UtenteId) o;
//        return Objects.equals(id_quiz, that.id_quiz) && Objects.equals(id_utente, that.id_utente);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id_quiz, id_utente);
//    }
//
//    // toString()
//	@Override
//	public String toString() {
//		return "Quiz_UtenteId [id_quiz=" + id_quiz + ", id_utente=" + id_utente + "]";
//	}
//        
//    
//    
//    
//    
//}
