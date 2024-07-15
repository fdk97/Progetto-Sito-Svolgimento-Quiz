package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Domanda_Risposta;
import com.example.model.Linguaggio;
import com.example.model.Quiz;
import com.example.model.Quiz_Domanda;
import com.example.model.Quiz_Utente;
import com.example.model.Utente;
import com.example.repository.Domanda_RispostaRepository;
import com.example.repository.LinguaggioRepository;
import com.example.repository.QuizRepository;
import com.example.repository.Quiz_DomandaRepository;
import com.example.repository.Quiz_UtenteRepository;
import com.example.repository.UtenteRepository;

import jakarta.transaction.Transactional;

@Service
public class QuizService {
	
	@Autowired
	private QuizRepository quizRepository;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
    private Quiz_UtenteRepository quiz_UtenteRepository;
	
	@Autowired
	private LinguaggioRepository linguaggioRepository;
	
	@Autowired
	private Domanda_RispostaRepository domandaRispostaRepository;

	@Autowired
	private Quiz_DomandaRepository quizDomandaRepository;
	
	// Metodo per recuperare tutti i linguaggi
    public List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }
    
    // Metodo per recuperare un linguaggio per ID
    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id); 
    }
    
    // Metodo per recuperare tutti gli utenti
    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }
    
    
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        // itera nella lista quizzes 
        for (Quiz quiz : quizzes) {
        	// recupera l'id_linguaggio di quiz tramite il findbyId dell'oggetto linguaggio
            Linguaggio linguaggio = linguaggioRepository.findById(quiz.getId_linguaggio()).orElse(null);
            System.out.println("sono nel service | LINGUAGGIO -> " + linguaggio);
            // se il linguaggio diverso da null -> trova il nome corrispondente
            if (linguaggio != null) {
                quiz.setNomeLinguaggio(linguaggio.getNomeArgomento());
                System.out.println("sono nel service | SONO NEL if > for > getAllQuiz() | NOME LINGUAGGIO -> " + linguaggio.getNomeArgomento());
            }
        }
        return quizzes;
    }
    
    
    // Metodo per assegnare il quiz ad uno studente
    @Transactional
    public void assegnaQuiz(Long quizId, List<Long> utenteIdList) {
        for (Long utenteId : utenteIdList) {
        	// Controlla se esiste già un quiz ( id_quiz ) assegnato ad uno Studente ( id_utente )
            boolean exists = quiz_UtenteRepository.existsByIdQuizAndIdUtente(quizId, utenteId);
            // se esiste esce il messaggio passato nel model del metodo POST nel Controller
            if (!exists) { // Altrimenti salva i dati
                Quiz_Utente quizUtente = new Quiz_Utente();
                quizUtente.setId_quiz(quizId);
                quizUtente.setId_utente(utenteId);
                quiz_UtenteRepository.save(quizUtente);
            }
        }
    }
    
    
	public Quiz createQuizWithRandomDomande(Long linguaggioId) {

		System.err.println("sono nel metodo createQuizWithRandomDomande in QuizService");
		
		Long ultimoId = quizRepository.findMaxId().orElse(0L);
		System.err.println("ultimo -> " + ultimoId);
		Long nuovoId = ultimoId + 1;
		System.err.println("nuovo id -> " + nuovoId);

		// Crea un nuovo quiz associato al linguaggio
		Quiz quiz = new Quiz();
		System.err.println(quiz);
		quiz.setId_linguaggio(linguaggioId);
		quiz.setId(nuovoId);
		System.err.println(quiz);
		quiz = quizRepository.save(quiz); // Salva e ritorna il quiz creato // NON SALVA
		System.err.println("sono in QuizService | salvato il nuovo quiz -> " + quiz);

		// Aggiungi 10 domande casuali al quiz
		assignRandomDomandeToQuiz(quiz.getId(), linguaggioId);
		

		return quiz;
	}

	public void assignRandomDomandeToQuiz(Long quizId, Long linguaggioId) {
		
		System.err.println("sono nel metodo assignRandomDomandeToQuiz in QuizService");
		
		// Recupera tutte le domande risposta dal repository
		List<Domanda_Risposta> domandeRisposta = domandaRispostaRepository.findRandomDomandeByLinguaggioId(linguaggioId);

		// Verifica se ci sono almeno 10 domande risposta disponibili
		if (domandeRisposta.size() < 10) {
			throw new RuntimeException("Non ci sono abbastanza domande risposta nel sistema.");
		}

		// Assegna 10 domande risposta casuali al quiz
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			
			System.err.println("sono nel metodo assignRandomDomandeToQuiz in QuizService nel for");
			
			int randomIndex = random.nextInt(domandeRisposta.size());
			Domanda_Risposta domandaRisposta = domandeRisposta.get(randomIndex);

			// Crea un'istanza di Quiz_Domanda per associare questa domanda risposta al quiz
			Quiz_Domanda quizDomanda = new Quiz_Domanda();
			quizDomanda.setId_quiz(quizId);
			quizDomanda.setId_domanda(domandaRisposta.getId());

			// Salva l'associazione nel repository Quiz_DomandaRepository
			quizDomandaRepository.aggiungiQuiz(quizId, domandaRisposta.getId());
			System.err.println("sono in QuizService | metodo assignRandomDomandeToQuiz | quiz aggiunto");
			 
			// Rimuove la domanda selezionata per evitare duplicati
            domandeRisposta.remove(randomIndex);
		}
	}
    

}
