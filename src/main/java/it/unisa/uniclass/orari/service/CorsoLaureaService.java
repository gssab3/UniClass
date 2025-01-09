package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.AnnoDidattico;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import it.unisa.uniclass.orari.service.dao.CorsoLaureaRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CorsoLaureaService {

    private CorsoLaureaRemote corsoLaureaDAO;

    public CorsoLaureaService() {
        try {
            InitialContext ctx = new InitialContext();
            this.corsoLaureaDAO = (CorsoLaureaRemote) ctx.lookup("java:global/UniClass/CorsoLaureaDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di CorsoLaureaDAO.", e);
        }
    }

    public CorsoLaurea trovaCorsoLaurea(long id) {
        try {
            return corsoLaureaDAO.trovaCorsoLaurea(id);
        }
        catch (NoResultException e) {
            return null;
        }
    }

    public CorsoLaurea trovaCorsoLaurea(String nome) {
        try {
            return corsoLaureaDAO.trovaCorsoLaurea(nome);
        }
        catch (NoResultException e) {
            return null;
        }
    }

    public List<CorsoLaurea> trovaTutti() {
        try {
            return corsoLaureaDAO.trovaTutti();
        } catch (Exception e) {
            throw new RuntimeException("Errore durante il recupero dei corsi di laurea.", e);
        }
    }

    public void aggiungiCorsoLaurea(CorsoLaurea corsoLaurea) {
        if (corsoLaurea == null || corsoLaurea.getNome() == null || corsoLaurea.getNome().isEmpty()) {
            throw new IllegalArgumentException("Il corso di laurea deve avere un nome valido.");
        }
        corsoLaureaDAO.aggiungiCorsoLaurea(corsoLaurea);
    }

    public void rimuoviCorsoLaurea(CorsoLaurea corsoLaurea) {
        if (corsoLaurea == null) {
            throw new IllegalArgumentException("Il corso di laurea da rimuovere non può essere null.");
        }
        corsoLaureaDAO.rimuoviCorsoLaurea(corsoLaurea);
    }
}
