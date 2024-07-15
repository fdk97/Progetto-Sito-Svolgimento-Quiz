package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Utente;

import jakarta.transaction.Transactional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    // Metodo per trovare un utente tramite l'indirizzo email
    Utente findByMail(String mail);

    // Query customizzata per aggiornare i campi di un utente specifico
    @Modifying
    @Transactional
    @Query("update Utente u set u.nome = :nome, u.cognome = :cognome, u.mail = :mail, u.ruolo = :ruolo where u.id = :id_utente")
    void updateUtente(@Param("id_utente") Long id_utente, @Param("nome") String nome, @Param("cognome") String cognome, @Param("mail") String mail, @Param("ruolo") Long ruolo);

    // salva oggetto Utente Optional
	void save(Optional<Utente> utente);
	
//    @Query("update Utente u set u.nome = :nome, u.cognome = :cognome, u.mail = :mail where u.id = :id_utente")
//    void updateUtente(@Param("id_utente") Long id_utente, @Param("nome") String nome, @Param("cognome") String cognome,
//            @Param("mail") String mail);
    
    
    @Query("select u from Utente u Where u.mail = :mail") 
	Utente findBymail(@Param("mail") String mail);
    
    
    @Query("select nome from Utente u Where u.mail = :mail") 
  	String trovaNomeBymail(@Param("mail") String mail);
	

    @Query("select id from Utente u Where u.mail = :mail") 
    Long trovaidBymail(@Param("mail") String mail);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO utenti (ruolo, nome, cognome, mail, password_utente) VALUES (:ruolo, :nome, :cognome, :mail, :password_utente)", nativeQuery = true)
    void salvaUtente(@Param("ruolo") Long ruolo, @Param("nome") String nome, @Param("cognome") String cognome, @Param("mail") String mail, @Param("password_utente") String password);
    
    boolean existsByMail(String mail);
    
    @Modifying
    @Transactional
    @Query("update Utente u set u.token = :token where u.mail = :mail")
    void updateTokenbymail(@Param("token") String token, @Param("mail") String mail);
    
    @Query("select u from Utente u Where u.token = :token") 
   	Utente findBytoken(@Param("token") String token);
    
    @Modifying
    @Transactional
    @Query("update Utente u set u.password_utente = :password where u.mail = :mail")
    void updatePasswordbymail(@Param("password") String password, @Param("mail") String mail);

}