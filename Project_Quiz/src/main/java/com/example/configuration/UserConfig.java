package com.example.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.UserDetailsImpl;
import com.example.model.Utente;
import com.example.repository.RuoloRepository;
import com.example.repository.UtenteRepository;



@Configuration
public class UserConfig implements AuthenticationProvider {

@Autowired
private UtenteRepository repository;

@Autowired
RuoloRepository ruoloRepository;

@Bean
PasswordEncoder passwordEncoder(){
	return new BCryptPasswordEncoder();
}

@Override
public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	String username = authentication.getName();
	String password = authentication.getCredentials().toString();
	Utente user = repository.findBymail(username);
	
	if (user!=null) {
		System.out.println("password inserita= " + password);
		System.out.println("Password database= " + user.getPassword_utente());
		
		
		
		
		 if (passwordEncoder().matches(password, user.getPassword_utente())) {
			 if (user.getRuolo() != 3) { // Verifica isEnabled prima di creare il token
	                // ... logica esistente per creare e restituire UsernamePasswordAuthenticationToken
	           
	            System.out.println("PASSWORD CORRETTA");
	            System.out.println("Ruolo = " + ruoloRepository.findByid(user.getRuolo()));
	            List<GrantedAuthority> authorities = getGrantedAuthority(ruoloRepository.findByid(user.getRuolo()));
	            UserDetails userDetails = new UserDetailsImpl(user.getMail(), user.getPassword_utente(), authorities);
	            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), authorities); // Creare e restituire UsernamePasswordAuthenticationToken
			 } else {
				 System.out.println("Account disabilitato");
	                throw new BadCredentialsException("Account disabilitato");
	            } 
			 } else {
	            System.out.println("PASS NON VALIDA");
	            throw new BadCredentialsException("PASSWORD NON VALIDA");
	        }
	    } else {
	        System.out.println("Utente non trovato");
	        throw new BadCredentialsException("Utente non trovato");
	    }
		
		

}


private List<GrantedAuthority> getGrantedAuthority(String string){
	
	List<GrantedAuthority> grantedAuthority = new ArrayList<>();
	
		grantedAuthority.add(new SimpleGrantedAuthority(string));
	return grantedAuthority;
	
}

@Override
public boolean supports(Class<?> authentication) {
	return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
}
	
	
	
}
