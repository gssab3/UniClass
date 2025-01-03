package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.Giorno;
import it.unisa.uniclass.orari.model.Lezione;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import java.sql.Time;
import java.util.List;

@Stateless
public class LezioneService {
    @EJB
    private LezioneDAO dao;

    public Lezione trovaLezione(long id) {
        try {
            return dao.trovaLezione(id);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<Lezione> trovaLezioniCorso(String nomeCorso) {
        return dao.trovaLezioniCorso(nomeCorso);
    }

    public List<Lezione> trovaLezioniOre(Time oraInizio, Time oraFine) {
        return dao.trovaLezioniOre(oraInizio, oraFine);
    }

    public List<Lezione> trovaLezioniOreGiorno(Time oraInizio, Time oraFine, Giorno giorno) {
        return dao.trovaLezioniOreGiorno(oraInizio, oraFine, giorno);
    }

    public List<Lezione> trovaLezioniAule(String nome) {
        return dao.trovaLezioniAule(nome);
    }

    public List<Lezione> trovaTutte() {
        return dao.trovaTutte();
    }
}
