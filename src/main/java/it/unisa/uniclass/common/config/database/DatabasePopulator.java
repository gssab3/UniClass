package it.unisa.uniclass.common.config.database;

import it.unisa.uniclass.orari.model.*;
import it.unisa.uniclass.orari.service.CorsoLaureaService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.ArrayList;
import java.sql.Time;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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

        // Creazione dei resti e associazione con il corso di laurea
        Resto resto0 = new Resto();
        resto0.setNome("Resto 0");
        resto0.setCorsoLaurea(corsoLaurea);

        Resto resto1 = new Resto();
        resto1.setNome("Resto 1");
        resto1.setCorsoLaurea(corsoLaurea);

        Resto resto2 = new Resto();
        resto2.setNome("Resto 2");
        resto2.setCorsoLaurea(corsoLaurea);

        // Aggiunta dei resti al corso di laurea
        corsoLaurea.getResti().add(resto0);
        corsoLaurea.getResti().add(resto1);
        corsoLaurea.getResti().add(resto2);

        // Creazione degli anni didattici
        AnnoDidattico anno1 = new AnnoDidattico("Anno 1");
        AnnoDidattico anno2 = new AnnoDidattico("Anno 2");
        AnnoDidattico anno3 = new AnnoDidattico("Anno 3");

        // Associazione degli anni didattici al corso di laurea
        corsoLaurea.getAnniDidattici().add(anno1);
        corsoLaurea.getAnniDidattici().add(anno2);
        corsoLaurea.getAnniDidattici().add(anno3);

        anno1.getCorsiLaurea().add(corsoLaurea);
        anno2.getCorsiLaurea().add(corsoLaurea);
        anno3.getCorsiLaurea().add(corsoLaurea);

        // Creazione del corso "Ingegneria del Software" e associazione con l'anno 3
        Corso corsoIDS = new Corso();
        corsoIDS.setNome("Ingegneria del Software");
        corsoIDS.setCorsoLaurea(corsoLaurea);
        corsoIDS.setAnnoDidattico(anno3);

        // Aggiunta delle lezioni al corso per ciascun resto
        for (Resto resto : List.of(resto0, resto1, resto2)) {
            List<Lezione> lezioni = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                Lezione lezione = new Lezione();
                lezione.setSemestre(1);
                lezione.setOraInizio(Time.valueOf("09:00:00"));
                lezione.setOraFine(Time.valueOf("11:00:00"));
                lezione.setGiorno(Giorno.LUNEDI); // Puoi variare i giorni se necessario
                lezione.setResto(resto);
                lezione.setCorso(corsoIDS);
                lezioni.add(lezione);
            }
            corsoIDS.getLezioni().addAll(lezioni);
        }

        // Persistenza degli oggetti
        em.persist(anno1);
        em.persist(anno2);
        em.persist(anno3);
        em.persist(resto0);
        em.persist(resto1);
        em.persist(resto2);
        em.persist(corsoLaurea);
        em.persist(corsoIDS);

        // Inizializzazione dei dati
        em.flush(); // Forza il salvataggio su database
        em.clear(); // Pulisce il contesto per simulare un nuovo fetch
    }






    public void popolaDBUniversity() {

    }
}
