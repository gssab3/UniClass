package it.unisa.uniclass.conversazioni.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ConversazioniDAO {
    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @PersistenceContext(unitName = "DBUniversityPU")
    private EntityManager emUniversity;

    // Da fare
}
