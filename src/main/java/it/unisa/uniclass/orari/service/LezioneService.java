package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.*;
import it.unisa.uniclass.orari.service.dao.CorsoLaureaRemote;
import it.unisa.uniclass.orari.service.dao.LezioneDAO;
import it.unisa.uniclass.orari.service.dao.LezioneRemote;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class LezioneService {

    private LezioneRemote lezioneDao;

    public LezioneService() {
        try {
            InitialContext ctx = new InitialContext();
            this.lezioneDao = (LezioneRemote) ctx.lookup("java:global/UniClass/LezioneDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di LezioneDAO.", e);
        }
    }

    public Lezione trovaLezione(long id) {
        try {
            return lezioneDao.trovaLezione(id);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<Lezione> trovaLezioniCorso(String nomeCorso) {
        return lezioneDao.trovaLezioniCorso(nomeCorso);
    }

    public List<Lezione> trovaLezioniOre(Time oraInizio, Time oraFine) {
        return lezioneDao.trovaLezioniOre(oraInizio, oraFine);
    }

    public List<Lezione> trovaLezioniOreGiorno(Time oraInizio, Time oraFine, Giorno giorno) {
        return lezioneDao.trovaLezioniOreGiorno(oraInizio, oraFine, giorno);
    }

    public List<Lezione> trovaLezioniAule(String nome) {
        return lezioneDao.trovaLezioniAule(nome);
    }

    public List<Lezione> trovaTutte() {
        return lezioneDao.trovaTutte();
    }

    public List<Lezione> trovaLezioniCorsoLaureaRestoAnno(long clid, long rid, int annoid) {
        return lezioneDao.trovaLezioniCorsoLaureaRestoAnno(clid, rid, annoid);
    }

    public List<Lezione> trovaLezioniCorsoLaureaRestoAnnoSemestre(long clid, long rid, int annoid, int semestre) {
        return lezioneDao.trovaLezioniCorsoLaureaRestoAnnoSemestre(clid, rid, annoid, semestre);
    }

    public List<Lezione> trovaLezioniDocente(String nomeDocente) {
        return lezioneDao.trovaLezioniDocente(nomeDocente);
    }

    public void aggiungiLezione(Lezione lezione) {
        lezioneDao.aggiungiLezione(lezione);
    }

    public void rimuoviLezione(Lezione lezione) {
        lezioneDao.rimuoviLezione(lezione);
    }


}
