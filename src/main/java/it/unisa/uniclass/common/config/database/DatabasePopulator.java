package it.unisa.uniclass.common.config.database;

import it.unisa.uniclass.orari.model.*;
import it.unisa.uniclass.orari.service.CorsoLaureaService;
import it.unisa.uniclass.utenti.model.Docente;
import it.unisa.uniclass.utenti.model.Tipo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Time;
import java.time.LocalDate;

@Singleton
@Startup
@DataSourceDefinition(
        name = "java:app/jdbc/DBUniClass",
        className = "org.postgresql.Driver",
        url = "jdbc:postgresql://localhost:5432/dbuniclass",
        user = "postgres",
        password = "password"
)
@LocalBean
public class DatabasePopulator {

    @Inject
    private EntityManager em;

    @PostConstruct
    public void popola() {
        System.out.println("AAAAAA");
        popolaDBUniversity();
        popolaDBUniClass();
        System.out.println("BBBBBB");
    }

    public void popolaDBUniClass() {
        // Creazione del corso di laurea
        CorsoLaurea corsoLaurea = new CorsoLaurea();
        corsoLaurea.setNome("Informatica");

        CorsoLaurea corsoLaurea2 = new CorsoLaurea();
        corsoLaurea2.setNome("Fisica");

        // Creazione dei resti e associazione con il corso di laurea
        Resto resto = new Resto();
        resto.setNome("Resto 0");
        resto.setCorsoLaurea(corsoLaurea);

        Resto resto2 = new Resto();
        resto2.setNome("Resto 1");
        resto2.setCorsoLaurea(corsoLaurea);

        Resto resto3 = new Resto();
        resto3.setNome("Resto 2");
        resto3.setCorsoLaurea(corsoLaurea);

        // Aggiunta dei resti al corso di laurea
        corsoLaurea.getResti().add(resto);
        corsoLaurea.getResti().add(resto2);
        corsoLaurea.getResti().add(resto3);

        // Creazione degli anni didattici
        AnnoDidattico anno1 = new AnnoDidattico("Anno 1");
        AnnoDidattico anno2 = new AnnoDidattico("Anno 2");
        AnnoDidattico anno3 = new AnnoDidattico("Anno 3");

        // Associazione degli anni didattici con il corso di laurea "Informatica"
        corsoLaurea.getAnniDidattici().add(anno1);
        corsoLaurea.getAnniDidattici().add(anno2);
        corsoLaurea.getAnniDidattici().add(anno3);

        // Associazione inversa (opzionale, utile per consistenza bidirezionale)
        anno1.getCorsiLaurea().add(corsoLaurea);
        anno2.getCorsiLaurea().add(corsoLaurea);
        anno3.getCorsiLaurea().add(corsoLaurea);

        //Creazione dei corsi
        Corso corso1 = new Corso();
        corso1.setNome("Ingegneria del Software");
        Corso corso2 = new Corso();
        corso2.setNome("Fondamenti di Intelligenza Artificiale");

        //Associazione dei corsi ai corsiLaurea
        corso1.setCorsoLaurea(corsoLaurea);
        corso2.setCorsoLaurea(corsoLaurea);

        //Associazione dei corsilaurea ai corsi
        corsoLaurea.getCorsi().add(corso1);
        corsoLaurea.getCorsi().add(corso2);

        //Associazione dei corsi agli anni
        corso1.setAnnoDidattico(anno3);
        corso2.setAnnoDidattico(anno3);

        //anni ai corsi
        anno3.getCorsi().add(corso1);
        anno3.getCorsi().add(corso2);

        //Creazione Docente e associazione con corsi
        Docente docente1 = new Docente();
        docente1.setNome("Giovanni");
        docente1.setCognome("Mastroianni");
        docente1.setCorsoLaurea(corsoLaurea);
        docente1.setDipartimento("Informatica");
        docente1.setIscrizione(LocalDate.now());
        docente1.setMatricola("0512110000");
        docente1.setDataNascita(LocalDate.of(1980, 10, 20));
        docente1.setPassword("0000");
        docente1.setTipo(Tipo.Docente);
        docente1.getCorsi().add(corso1);
        docente1.setEmail("giovanni@unisa.it");
        corso1.getDocenti().add(docente1);

        //Creazione Lezioni
        Lezione lezione1 = new Lezione();

        //Associazione lezioni
        lezione1.setCorso(corso1);
        lezione1.setGiorno(Giorno.LUNEDI);
        lezione1.setResto(resto);
        lezione1.setOraFine(Time.valueOf("11:00:00"));
        lezione1.setOraInizio(Time.valueOf("9:00:00"));
        lezione1.setSemestre(1);

        //Aula
        Aula aula = new Aula();
        aula.setNome("F8");
        aula.setEdificio("F2");
        lezione1.setAula(aula);
        aula.getLezioni().add(lezione1);

        // Persistenza degli oggetti
        em.persist(anno1);
        em.persist(anno2);
        em.persist(anno3);
        em.persist(corsoLaurea);
        em.persist(corsoLaurea2);
        em.persist(corso2);
        em.persist(corso1);
        em.persist(docente1);
        em.persist(aula);
        em.persist(lezione1);

        em.flush();
        em.clear();
    }




    public void popolaDBUniversity() {

    }
}
