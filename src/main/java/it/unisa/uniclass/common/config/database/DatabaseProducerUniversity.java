package it.unisa.uniclass.common.config.database;

import it.unisa.uniclass.common.config.database.qualifier.UniversityDB;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class DatabaseProducerUniversity {
    @Produces
    @PersistenceContext(unitName = "DBUniversityPU")
    @UniversityDB
    private EntityManager em;
}
