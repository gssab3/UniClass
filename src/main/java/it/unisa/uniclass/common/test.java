/*
package it.unisa.uniclass.common;

import it.unisa.uniclass.orari.model.Corso;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import it.unisa.uniclass.utenti.model.Studente;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class test {

    public static void main(String[] args) {

        // Crea un'istanza di EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBUniClassPU");

        // Crea l'EntityManager
        EntityManager em = emf.createEntityManager();

        // Creare un nuovo studente
        CorsoLaurea corsoLaurea = new CorsoLaurea("sperma") ;
        Resto resto = new Resto("Resto 0",  corsoLaurea);

        Studente studente = new Studente(
                "Mario", // Nome
                "Rossi", // Cognome
                LocalDate.of(1998, 5, 15), // Data di nascita
                "mario.rossi@email.com", // Email
                "password123", // Password
                "12345", // Matricola
                LocalDate.of(2022, 9, 1), // Data di iscrizione
                corsoLaurea,
                resto
        );

        // Inizia la transazione
        em.getTransaction().begin();

        // Persisti l'entità nel database
        em.persist(studente);

        // Commit della transazione
        em.getTransaction().commit();

        // Visualizza l'ID generato
        System.out.println("Studente salvato con ID: " + studente.getMatricola());

        // Chiudi EntityManager e EntityManagerFactory
        em.close();
        emf.close();
    }
}
*/