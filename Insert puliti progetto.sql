use GestioneUtenti_PIW02;

-- insert linguaggi
INSERT INTO linguaggi (nome_argomento) VALUES ('JavaSE');
INSERT INTO linguaggi (nome_argomento) VALUES ('SQL');
INSERT INTO linguaggi (nome_argomento) VALUES ('HTML');


-- insert ruoli
INSERT INTO ruoli (ruolo) VALUES ('Docente');
INSERT INTO ruoli (ruolo) VALUES ('Studente');

-- insert domande_risposte
INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'Il linguaggio Java è stato creato da James Gosling nel', '1985', '1991', '1995', '1999', 3);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'Il "byte code" in Java è...', 'Un meccanismo che permette al programma di rimuovere dalla memoria variabili ed oggetti non più utilizzati', 'Un codice parzialmente convertito dal codice sorgente per essere eseguito da Jit', 'Una raccolta di librerie di base della struttura di Java che ci forniscono le fondamenta per poter programmare', 'Uno strumento del JRE (Java Runtime Environment) per autocorreggere gli errori al proprio interno', 2);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'Quali tra i seguenti componenti NON fa parte di JDK (Java Development Kit)?', 'MVC framework', 'JavaDoc', 'JDBC', 'Collections', 1);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'Il Garbage Collector in Java è...', 'Un pacchetto di Eclipse per mantenere puliti ed in ordine i nostri progetti nel Workspace', 'Un "Deamon" che gestisce in autonomia gli oggetti nella memoria heap eliminandoli se necessario', 'Un plugin che ci avverte quando una parte del nostro codice contiene errori o warning permettendoci di correggerli mentre scriviamo codice', 'Una parte di JDK che ci permette di aggiungere dipendenze ai nostri progetti in modo semplcie.', 2);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'Su quale paradigma di programmazione si basa il linguaggio Java?', 'Programmazione procedurale', 'Programmazione imperativa', 'Programmazione funzionale', 'Programmazione ad oggetti', 4);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'Quale è la definizione corretta per Oggetto in Java?', 'rappresenta un tipo di dato definito dal programmatore', 'è la concretizzazione o istanza di una classe e rappresenta un elemento reale e definito', 'è il prototipo generale ed astratto di un''entità osservabile nella realtà', 'rappresenta un''entità astratta usata per la creazione di espressioni Lambda', 2);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'Quale tra le seguenti definizioni di classi è sbagliata?', 'public abstract NomeClasse {...}', 'final NomeClasse {...}', 'private abstract NomeClasse {...}', 'NomeClasse {...}', 3);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'Quale tra i seguenti meccanismi non fa parte delle basi della programmazione ad oggetti?', 'Dependency Injection', 'Overriding', 'Ereditarietà', 'Incapsulamento', 1);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'Dichiarare un campo o un metodo "private", rende questo membro di classe:', 'non utilizzabile', 'utilizzabile solo nel package di appartenenza', 'utilizzabile solo nelle classi che ereditano la classe in cui si trova', 'utilizzabile solo nella classe in cui si trova', 4);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'Quanti costruttori posso avere all''interno di una classe? Trova la risposta SBAGLIATA', 'almeno 1', 'nessuno', 'virtualmente infiniti a patto che differiscano per tipo e/o ordine dei parametri in ingresso', 'almeno il costruttore di default e/o un costruttore parametrico', 2);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'In Java, quale parola chiave è utilizzata per definire una classe non estendibile?', 'abstract', 'final', 'static', 'volatile', 2);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'Qual è la parola chiave utilizzata per gestire eccezioni?', 'try', 'catch', 'throw', 'throws', 1);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'In quale package si trova la classe String?', 'java.io', 'java.lang', 'java.util', 'java.net', 2);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'Come si chiama il processo di rimozione dei bug nel software?', 'Testing', 'Debugging', 'Profiling', 'Logging', 2);

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES (1, 'Qual è la dimensione di un int in Java?', '8 bit', '16 bit', '32 bit', '64 bit', 3);

-- Linguaggio Sql 

INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES
  (2, 'Qual è il comando SQL per selezionare tutti i campi da una tabella denominata "Utenti"?', 'A) SELECT * FROM Utenti;', 'B) SELECT ALL FROM Utenti;', 'C) SELECT Utenti;', 'D) GET * FROM Utenti;', 1),
  (2, 'Quale comando SQL è usato per inserire nuovi dati in una tabella?', 'A) ADD', 'B) INSERT INTO', 'C) UPDATE', 'D) APPEND', 2),
  (2, 'Qual è il comando SQL per aggiornare i dati esistenti in una tabella?', 'A) INSERT INTO', 'B) MODIFY', 'C) UPDATE', 'D) CHANGE', 3),
  (2, 'Quale comando SQL elimina i dati da una tabella senza rimuovere la struttura della tabella?', 'A) REMOVE', 'B) DELETE', 'C) DROP', 'D) CLEAR', 2),
  (2, 'Quale comando SQL crea una nuova tabella nel database?', 'A) CREATE TABLE', 'B) NEW TABLE', 'C) ADD TABLE', 'D) MAKE TABLE', 1),
  (2, 'Qual è il comando SQL per eliminare una tabella dal database?', 'A) DELETE TABLE', 'B) REMOVE TABLE', 'C) DROP TABLE', 'D) CLEAR TABLE', 3),
  (2, 'Quale comando SQL viene usato per rimuovere tutti i dati da una tabella, ma mantenere la struttura della tabella?', 'A) TRUNCATE TABLE', 'B) DROP TABLE', 'C) DELETE TABLE', 'D) REMOVE TABLE', 1),
  (2, 'Come si chiama la clausola SQL usata per filtrare i risultati di una query?', 'A) WHERE', 'B) FILTER', 'C) HAVING', 'D) CONDITION', 1),
  (2, 'Quale clausola SQL è usata per ordinare i risultati di una query?', 'A) ORDER', 'B) SORT', 'C) ORDER BY', 'D) SORT BY', 2),
  (2, 'Quale parola chiave SQL è usata per combinare righe da due o più tabelle, basate su una condizione correlata tra di esse?', 'A) CONNECT', 'B) JOIN', 'C) UNITE', 'D) LINK', 2),
  (2, 'Qual è la funzione SQL usata per contare il numero di righe in una colonna?', 'A) SUM()', 'B) COUNT()', 'C) NUMBER()', 'D) TOTAL()', 2),
  (2, 'Quale comando SQL crea un nuovo indice su una tabella?', 'A) CREATE INDEX', 'B) NEW INDEX', 'C) ADD INDEX', 'D) MAKE INDEX', 1),
  (2, 'Qual è il comando SQL per recuperare dati senza duplicati?', 'A) SELECT UNIQUE', 'B) SELECT NO DUPLICATES', 'C) SELECT DISTINCT', 'D) SELECT DIFFERENT', 3),
  (2, 'Qual è la clausola SQL usata per raggruppare i risultati di una query basati su uno o più campi?', 'A) GROUP BY', 'B) ORDER BY', 'C) AGGREGATE', 'D) CLUSTER', 1),
  (2, 'Quale clausola SQL viene usata per specificare una condizione su gruppi creati con la clausola GROUP BY?', 'A) WHERE', 'B) HAVING', 'C) GROUP WHERE', 'D) GROUP CONDITION', 2);
  -- html
  INSERT INTO domande_risposte (id_linguaggio, domanda, risposta_uno, risposta_due, risposta_tre, risposta_quattro, risposta_esatta)
VALUES
  (3, 'Qual è lo scopo principale di HTML?', 'A) Stile delle pagine web', 'B) Struttura delle pagine web', 'C) Interattività delle pagine web', 'D) Animazioni delle pagine web', 2),
  (3, 'Qual è il tag corretto per un link ipertestuale in HTML?', 'A) <link>', 'B) <a>', 'C) <href>', 'D) <url>', 2),
  (3, 'Quale attributo HTML viene usato per specificare un URL di destinazione per un link?', 'A) name', 'B) src', 'C) href', 'D) link', 3),
  (3, 'Quale tag HTML è utilizzato per creare un elenco puntato?', 'A) <ol>', 'B) <ul>', 'C) <li>', 'D) <dl>', 2),
  (3, 'Qual è il tag corretto per inserire un\'immagine in HTML?', 'A) <image>', 'B) <img>', 'C) <picture>', 'D) <src>', 2),
  (3, 'Quale attributo HTML è usato per specificare il testo alternativo di un\'immagine?', 'A) title', 'B) alt', 'C) src', 'D) desc', 2),
  (3, 'Qual è la differenza tra il tag <div> e il tag <span>? ', 'A) <div> è per blocchi di contenuto, <span> è per contenuto in linea.', 'B) <div> è per contenuto in linea, <span> è per blocchi di contenuto.', 'C) Non c\'è alcuna differenza.', 'D) <div> è obsoleto, <span> è il nuovo standard.', 1),
  (3, 'Qual è il tag HTML per creare una tabella?', 'A) <tbl>', 'B) <table>', 'C) <tab>', 'D) <t>', 2),
  (3, 'Quale attributo HTML viene usato per combinare celle in una tabella?', 'A) merge', 'B) rowspan e colspan', 'C) combine', 'D) join', 2),
  (3, 'Qual è il tag HTML per creare un campo di input di testo in un modulo?', 'A) <form>', 'B) <textarea>', 'C) <input type="text">', 'D) <textfield>', 3),
  (3, 'Quale tag HTML viene usato per definire una sezione di intestazione di un documento o di una sezione?', 'A) <header>', 'B) <section>', 'C) <head>', 'D) <h1>', 1),
  (3, 'Come si specifica un commento in HTML?', 'A) // Questo è un commento', 'B) ', 'C) /* Questo è un commento */', 'D) * Questo è un commento *', 2),
  (3, 'Qual è lo scopo dell\'attributo target="_blank" in un tag <a>? ', 'A) Aprire il link in una nuova scheda o finestra.', 'B) Aprire il link nello stesso frame.', 'C) Aprire il link in un frame specifico.', 'D) Caricare il link dopo un certo periodo.', 1),
  (3, 'Quale tag HTML è usato per includere un file CSS esterno?', 'A) <script>', 'B) <link>', 'C) <style>', 'D) <css>', 2);
  





