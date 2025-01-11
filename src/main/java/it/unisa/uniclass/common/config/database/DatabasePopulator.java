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
        Corso corso3 = new Corso();
        corso3.setNome("Programmazione Distribuita");

        //Associazione dei corsi ai corsiLaurea
        corso1.setCorsoLaurea(corsoLaurea);
        corso2.setCorsoLaurea(corsoLaurea);
        corso3.setCorsoLaurea(corsoLaurea);

        //Associazione dei corsilaurea ai corsi
        corsoLaurea.getCorsi().add(corso1);
        corsoLaurea.getCorsi().add(corso2);
        corsoLaurea.getCorsi().add(corso3);

        //Associazione dei corsi agli anni
        corso1.setAnnoDidattico(anno3);
        corso2.setAnnoDidattico(anno3);
        corso3.setAnnoDidattico(anno3);

        //anni ai corsi
        anno3.getCorsi().add(corso1);
        anno3.getCorsi().add(corso2);
        anno3.getCorsi().add(corso3);

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

        Docente docente2 = new Docente();
        docente2.setNome("Alberto");
        docente2.setCognome("Rossi");
        docente2.setCorsoLaurea(corsoLaurea);
        docente2.setDipartimento("Informatica");
        docente2.setIscrizione(LocalDate.now());
        docente2.setMatricola("0512111090");
        docente2.setDataNascita(LocalDate.of(1980,1,13));
        docente2.setPassword("1111");
        docente2.setTipo(Tipo.Docente);
        docente2.getCorsi().add(corso2);
        docente2.setEmail("albertorossi@unisa.it");
        corso2.getDocenti().add(docente2);

        Docente docente3 = new Docente();
        docente3.setNome("Giacomo");
        docente3.setCognome("Poretti");
        docente3.setCorsoLaurea(corsoLaurea);
        docente3.setDipartimento("Informatica");
        docente3.setIscrizione(LocalDate.now());
        docente3.setMatricola("0512111101");
        docente3.setDataNascita(LocalDate.of(1990,1,10));
        docente3.setPassword("2222");
        docente3.setTipo(Tipo.Docente);
        docente3.getCorsi().add(corso3);
        docente3.setEmail("giacomoporetti@unisa.it");
        corso3.getDocenti().add(docente3);

        //Creazione Lezioni
        //Lezioni di IS resto 0
        Lezione lezione1 = new Lezione();
        Lezione lezione2 = new Lezione();
        Lezione lezione3 = new Lezione();

        //Lezioni di FIA resto 0
        Lezione lezione4 = new Lezione();
        Lezione lezione5 = new Lezione();

        //Lezioni di PD resto 0
        Lezione lezione6 = new Lezione();
        Lezione lezione7 = new Lezione();
        Lezione lezione8 = new Lezione();


        //Lezioni di IS resto1
        Lezione lezione1_1 = new Lezione();
        Lezione lezione2_1 = new Lezione();
        Lezione lezione3_1 = new Lezione();

        //Lezioni di FIA resto1
        Lezione lezione4_1 = new Lezione();
        Lezione lezione5_1 = new Lezione();

        //Lezioni di PD resto1
        Lezione lezione6_1 = new Lezione();
        Lezione lezione7_1 = new Lezione();
        Lezione lezione8_1 = new Lezione();


        //Associazione lezioni
        lezione1.setCorso(corso1);
        lezione1.setGiorno(Giorno.LUNEDI);
        lezione1.setResto(resto);
        lezione1.setOraFine(Time.valueOf("14:00:00"));
        lezione1.setOraInizio(Time.valueOf("11:00:00"));
        lezione1.setSemestre(1);

        lezione2.setCorso(corso1);
        lezione2.setGiorno(Giorno.GIOVEDI);
        lezione2.setResto(resto);
        lezione2.setOraInizio(Time.valueOf("9:00:00"));
        lezione2.setOraFine(Time.valueOf("11:00:00"));
        lezione2.setSemestre(1);

        lezione3.setCorso(corso1);
        lezione3.setGiorno(Giorno.VENERDI);
        lezione3.setResto(resto);
        lezione3.setOraInizio(Time.valueOf("11:00:00"));
        lezione3.setOraFine(Time.valueOf("13:00:00"));
        lezione3.setSemestre(1);

        lezione4.setCorso(corso2);
        lezione4.setGiorno(Giorno.MARTEDI);
        lezione4.setResto(resto);
        lezione4.setOraInizio(Time.valueOf("13:30:00"));
        lezione4.setOraFine(Time.valueOf("15:30:00"));
        lezione4.setSemestre(1);

        lezione5.setCorso(corso2);
        lezione5.setGiorno(Giorno.GIOVEDI);
        lezione5.setResto(resto);
        lezione5.setOraInizio(Time.valueOf("13:30:00"));
        lezione5.setOraFine(Time.valueOf("15:30:00"));
        lezione5.setSemestre(1);

        lezione6.setCorso(corso3);
        lezione6.setGiorno(Giorno.LUNEDI);
        lezione6.setResto(resto);
        lezione6.setOraInizio(Time.valueOf("14:30:00"));
        lezione6.setOraFine(Time.valueOf("17:30:00"));
        lezione6.setSemestre(1);

        lezione7.setCorso(corso3);
        lezione7.setGiorno(Giorno.GIOVEDI);
        lezione7.setResto(resto);
        lezione7.setOraInizio(Time.valueOf("9:00:00"));
        lezione7.setOraFine(Time.valueOf("11:00:00"));
        lezione7.setSemestre(1);

        lezione8.setCorso(corso3);
        lezione8.setGiorno(Giorno.VENERDI);
        lezione8.setResto(resto);
        lezione8.setOraInizio(Time.valueOf("11:00:00"));
        lezione8.setOraFine(Time.valueOf("13:00:00"));
        lezione8.setSemestre(1);


        lezione1_1.setCorso(corso1);
        lezione1_1.setGiorno(Giorno.LUNEDI);
        lezione1_1.setResto(resto2);
        lezione1_1.setOraInizio(Time.valueOf("11:00:00"));
        lezione1_1.setOraFine(Time.valueOf("14:00:00"));
        lezione1_1.setSemestre(1);

        lezione2_1.setCorso(corso1);
        lezione2_1.setGiorno(Giorno.GIOVEDI);
        lezione2_1.setResto(resto2);
        lezione2_1.setOraInizio(Time.valueOf("11:00:00"));
        lezione2_1.setOraFine(Time.valueOf("13:00:00"));
        lezione2_1.setSemestre(1);

        lezione3_1.setCorso(corso1);
        lezione3_1.setGiorno(Giorno.VENERDI);
        lezione3_1.setResto(resto2);
        lezione3_1.setOraInizio(Time.valueOf("9:00:00"));
        lezione3_1.setOraFine(Time.valueOf("13:00:00"));
        lezione3_1.setSemestre(1);

        lezione4_1.setCorso(corso2);
        lezione4_1.setGiorno(Giorno.MARTEDI);
        lezione4_1.setResto(resto2);
        lezione4_1.setOraInizio(Time.valueOf("13:30:00"));
        lezione4_1.setOraFine(Time.valueOf("15:30:00"));
        lezione4_1.setSemestre(1);

        lezione5_1.setCorso(corso2);
        lezione5_1.setCorso(corso2);
        lezione5_1.setGiorno(Giorno.GIOVEDI);
        lezione5_1.setResto(resto2);
        lezione5_1.setOraInizio(Time.valueOf("13:30:00"));
        lezione5_1.setOraFine(Time.valueOf("15:30:00"));
        lezione5_1.setSemestre(1);

        //Aula
        Aula Sammet = new Aula();
        Sammet.setNome("Lab. Sammet");
        Sammet.setEdificio("F2");
        lezione1.setAula(Sammet);
        lezione6.setAula(Sammet);
        Sammet.getLezioni().add(lezione1);
        Sammet.getLezioni().add(lezione6);

        Aula hopper = new Aula();
        hopper.setNome("Lab. Hopper");
        hopper.setEdificio("F2");
        lezione1_1.setAula(hopper);
        hopper.getLezioni().add(lezione1_1);

        Aula p4 = new Aula();
        p4.setNome("P4");
        p4.setEdificio("F3");
        lezione2_1.setAula(p4);
        lezione3_1.setAula(p4);
        p4.getLezioni().add(lezione2_1);
        p4.getLezioni().add(lezione3_1);

        Aula f8 = new Aula();
        f8.setNome("F8");
        f8.setEdificio("F2");
        lezione2.setAula(f8);
        lezione3.setAula(f8);
        lezione4.setAula(f8);
        lezione5.setAula(f8);
        lezione7.setAula(f8);
        lezione8.setAula(f8);
        lezione4_1.setAula(f8);
        lezione5_1.setAula(f8);
        f8.getLezioni().add(lezione2);
        f8.getLezioni().add(lezione3);
        f8.getLezioni().add(lezione4);
        f8.getLezioni().add(lezione5);
        f8.getLezioni().add(lezione7);
        f8.getLezioni().add(lezione8);
        f8.getLezioni().add(lezione4_1);
        f8.getLezioni().add(lezione5_1);

        //Tra lezioni e docenti
        lezione1.getDocenti().add(docente1);
        lezione2.getDocenti().add(docente1);
        lezione3.getDocenti().add(docente1);
        lezione4.getDocenti().add(docente2);
        lezione5.getDocenti().add(docente2);
        lezione6.getDocenti().add(docente3);
        lezione7.getDocenti().add(docente3);
        lezione8.getDocenti().add(docente3);

        docente1.getLezioni().add(lezione1);
        docente1.getLezioni().add(lezione2);
        docente1.getLezioni().add(lezione3);
        docente2.getLezioni().add(lezione4);
        docente2.getLezioni().add(lezione5);
        docente3.getLezioni().add(lezione6);
        docente3.getLezioni().add(lezione7);
        docente3.getLezioni().add(lezione8);


        // Persistenza degli oggetti
        em.persist(anno1);
        em.persist(anno2);
        em.persist(anno3);
        em.persist(corsoLaurea);
        em.persist(corsoLaurea2);
        em.persist(corso2);
        em.persist(corso1);
        em.persist(corso3);
        em.persist(docente1);
        em.persist(docente2);
        em.persist(docente3);
        em.persist(Sammet);
        em.persist(hopper);
        em.persist(p4);
        em.persist(f8);
        em.persist(lezione1);
        em.persist(lezione2);
        em.persist(lezione3);
        em.persist(lezione4);
        em.persist(lezione5);
        em.persist(lezione6);
        em.persist(lezione7);
        em.persist(lezione8);
        em.persist(lezione1_1);
        em.persist(lezione2_1);
        em.persist(lezione3_1);

        em.flush();
        em.clear();
    }




    public void popolaDBUniversity() {

    }
}
