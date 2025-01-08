package it.unisa.uniclass.common.config.database;

import it.unisa.uniclass.orari.model.AnnoDidattico;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import it.unisa.uniclass.orari.service.CorsoLaureaService;
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
        resto.setCorsoLaurea(corsoLaurea); // Associazione al corso di laurea

        Resto resto2 = new Resto();
        resto2.setNome("Resto 1");
        resto2.setCorsoLaurea(corsoLaurea); // Associazione al corso di laurea

        Resto resto3 = new Resto();
        resto3.setNome("Resto 2");
        resto3.setCorsoLaurea(corsoLaurea); // Associazione al corso di laurea

        // Aggiunta dei resti al corso di laurea
        corsoLaurea.getResti().add(resto);
        corsoLaurea.getResti().add(resto2);
        corsoLaurea.getResti().add(resto3);

        // Persistenza degli oggetti
        em.persist(corsoLaurea);
        em.persist(corsoLaurea2);
    }

    public void popolaDBUniversity() {

    }
}
