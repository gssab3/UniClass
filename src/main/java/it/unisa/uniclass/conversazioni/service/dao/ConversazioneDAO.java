package it.unisa.uniclass.conversazioni.service.dao;

import it.unisa.uniclass.conversazioni.model.Conversazione;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless(name = "ConversazioneDAO")
public class ConversazioneDAO implements ConversazioneRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @Override
    public Conversazione trovaConversazione(long id) {
        TypedQuery<Conversazione> query = emUniClass.createNamedQuery(Conversazione.TROVA_CONVERSAZIONE, Conversazione.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Conversazione> trovaTutte() {
        TypedQuery<Conversazione> query = emUniClass.createNamedQuery(Conversazione.TROVA_TUTTE, Conversazione.class);
        return query.getResultList();
    }

    @Override
    public void aggiungiConversazione(Conversazione c) {
        emUniClass.merge(c);
    }

    @Override
    public void rimuoviConversazione(Conversazione c) {
        emUniClass.remove(c);
    }
}
