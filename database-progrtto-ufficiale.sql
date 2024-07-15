CREATE DATABASE IF NOT EXISTS GestioneUtenti_PIW02;
use GestioneUtenti_PIW02;

create table ruoli (
id bigint primary key auto_increment not null,
ruolo varchar (50)
);

create table utenti (
id bigint primary key auto_increment not null,
ruolo bigint not null,
nome varchar (50) not null,
cognome varchar (50) not null,
mail varchar (50) not null,
password_utente varchar (200) not null,
abilitato boolean,
FOREIGN KEY (ruolo) REFERENCES ruoli (id)
);

create table linguaggi(
id bigint primary key auto_increment,
nome_argomento varchar (50)
);

create table domande_risposte(
id bigint primary key auto_increment,
id_linguaggio bigint,
domanda varchar(500),
risposta_uno varchar (200),
risposta_due varchar (200),
risposta_tre varchar (200),
risposta_quattro varchar (200),
risposta_esatta int,
FOREIGN KEY (id_linguaggio) REFERENCES linguaggi (id)
);

create table quiz (
id bigint primary key auto_increment,
id_linguaggio bigint,
FOREIGN KEY (id_linguaggio) REFERENCES linguaggi (id)
);

CREATE TABLE quiz_domande (
id bigint primary key auto_increment,
    id_domanda bigint,
    id_quiz bigint,
    FOREIGN KEY (id_domanda) REFERENCES domande_risposte(id),
    FOREIGN KEY (id_quiz) REFERENCES quiz (id)
);

CREATE TABLE quiz_utenti (
	id_quiz bigint,
    id_utente bigint,
	completato boolean,
    FOREIGN KEY (id_quiz) REFERENCES quiz(id),
    FOREIGN KEY (id_utente) REFERENCES utenti (id)
);
