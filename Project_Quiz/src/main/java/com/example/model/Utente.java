package com.example.model;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "utenti",uniqueConstraints = @UniqueConstraint (columnNames = "mail"))
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotNull(message="il campo non può essere vuoto")
	private Long ruolo;
	
	@Column(length = 50)
	@NotEmpty(message="il campo non può essere vuoto")
	private String nome;
	
	@Column(length = 50)
	@NotEmpty(message="il campo non può essere vuoto")
	private String cognome;
	
	@Column(length = 50)
	@NotEmpty(message="il campo non può essere vuoto")
	@Email
	private String mail;
	
	@Column(length = 200)
	@NotEmpty
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-])[a-zA-Z0-9!@#$%^&*()_+=-]{8,}$",message="la password deve essere lunga almeno 8 caratteri, contenere almeno una lettera maiuscola, un numero e un carattere speciale")
	private String password_utente;
	
	@Transient // indica che il dato non è presente del DB
	private String codiceDocente;
	
	@Column(length = 200)
	private String token;
	
	public Utente() {}
	
	public Utente( @NotEmpty(message = "il campo non può essere vuoto") Long ruolo,
			@NotEmpty(message = "il campo non può essere vuoto") String nome,
			@NotEmpty(message = "il campo non può essere vuoto") String cognome,
			@NotEmpty(message = "il campo non può essere vuoto") @Email String mail,
			@NotEmpty @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-])[a-zA-Z0-9!@#$%^&*()_+=-]{8,}$", message = "la password deve essere lunga almeno 8 caratteri, contenere almeno una lettera maiuscola, un numero e un carattere speciale") String password_utente,
			boolean abilitato) {

	
		setRuolo(ruolo);
		setNome(nome);
		setCognome(cognome);
		setMail(mail);
		setPassword_utente(password_utente);
	}

	public Utente(Long id, @NotEmpty(message = "il campo non può essere vuoto") Long ruolo,
			@NotEmpty(message = "il campo non può essere vuoto") String nome,
			@NotEmpty(message = "il campo non può essere vuoto") String cognome,
			@NotEmpty(message = "il campo non può essere vuoto") @Email String mail,
			@NotEmpty @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-])[a-zA-Z0-9!@#$%^&*()_+=-]{8,}$", message = "la password deve essere lunga almeno 8 caratteri, contenere almeno una lettera maiuscola, un numero e un carattere speciale") String password_utente,
			boolean abilitato) {

		setId(id);
		setRuolo(ruolo);
		setNome(nome);
		setCognome(cognome);
		setMail(mail);
		setPassword_utente(password_utente);
	}
	
	public Utente( @NotEmpty(message = "il campo non può essere vuoto") Long ruolo,
			@NotEmpty(message = "il campo non può essere vuoto") String nome,
			@NotEmpty(message = "il campo non può essere vuoto") String cognome,
			@NotEmpty(message = "il campo non può essere vuoto") @Email String mail,
			@NotEmpty @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-])[a-zA-Z0-9!@#$%^&*()_+=-]{8,}$", message = "la password deve essere lunga almeno 8 caratteri, contenere almeno una lettera maiuscola, un numero e un carattere speciale") String password_utente, String codiceDocente, String token) {
	
		setRuolo(ruolo);
		setNome(nome);
		setCognome(cognome);
		setMail(mail);
		setPassword_utente(password_utente);
		setCodiceDocente(codiceDocente);
		setToken(token);
	}
	
	public String getCodDocente() {                                   // CODICE STUDENTE = primi 3 caratteri nome + ultimi 2 caratteri cognome + primi due caratteri email.
        // Controlla che tutti i campi necessari non siano nulli
        if (nome != null && cognome != null && mail != null) {
            // Prende i primi tre caratteri del nome, se il nome è lungo almeno 3 caratteri
            String firstNamePart = nome.length() >= 3 ? nome.substring(0, 3) : nome;
            // Prende gli ultimi due caratteri del cognome, se il cognome è lungo almeno 2 caratteri
            String lastNamePart = cognome.length() >= 2 ? cognome.substring(cognome.length() - 2) : cognome;
            // Prende i primi due caratteri dell'email, se l'email è lunga almeno 2 caratteri
            String emailPart = mail.length() >= 2 ? mail.substring(0, 2) : mail;
            // Concatena le parti estratte e le ritorna come campo personalizzato
            return firstNamePart + lastNamePart + emailPart;
           
        }
        // Ritorna null se uno dei campi necessari è nullo
      
        System.out.println("test return null");
        return null;
     
        
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRuolo() {
		return ruolo;
	}

	public void setRuolo(Long ruolo) {
		this.ruolo = ruolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword_utente() {
		return password_utente;
	}

	public void setPassword_utente(String password_utente) {
		this.password_utente = password_utente;
	}
	

	public String getCodiceDocente() {
		return codiceDocente;
	}

	public void setCodiceDocente(String codiceDocente) {
		this.codiceDocente = codiceDocente;
	}
	
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", ruolo=" + ruolo + ", nome=" + nome + ", cognome=" + cognome + ", mail=" + mail
				+ ", password_utente=" + password_utente + ", codiceDocente="
				+ codiceDocente + "]";
	}
	
	
	
	
	

}
