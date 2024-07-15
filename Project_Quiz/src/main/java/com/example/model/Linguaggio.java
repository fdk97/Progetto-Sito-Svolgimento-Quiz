package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "linguaggi")
public class Linguaggio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_argomento", length = 50)
	private String nomeArgomento;
	
    // Costruttori
    public Linguaggio() {}

    public Linguaggio(Long Id, String nomeArgomento) {
    	this.id = Id;
        this.nomeArgomento = nomeArgomento;
    }
	
	
	// Getters e Setters
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	public String getNomeArgomento() {
		return nomeArgomento;
	}
	public void setNomeArgomento(String nomeArgomento) {
		this.nomeArgomento = nomeArgomento;
	}

	
	
	
	// override metodo toString()
	@Override
	public String toString() {
		return "Linguaggio [id=" + id + ", nome_argomento=" + nomeArgomento + "]";
	}
	
	
	
}
