package it.unisa.uniclass.common.config.database;

import it.unisa.uniclass.orari.model.CorsoLaurea;
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
        CorsoLaurea corsoLaurea = new CorsoLaurea();
        corsoLaurea.setNome("Informatica");
        CorsoLaurea corsoLaurea2 = new CorsoLaurea();
        corsoLaurea2.setNome("Fisica");
        em.persist(corsoLaurea);
        em.persist(corsoLaurea2);
    }

    public void popolaDBUniversity() {

    }
}
