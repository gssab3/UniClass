package it.unisa.uniclass.common.config.database;


import it.unisa.uniclass.common.config.database.qualifier.UniclassDB;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class DatabaseProducerUniClass {
    @Produces
    @PersistenceContext(unitName = "DBUniclassPU")
    @UniclassDB
    private EntityManager em;
}
