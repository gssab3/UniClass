package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.Agenda;
import it.unisa.uniclass.orari.service.dao.AgendaRemote;
import it.unisa.uniclass.utenti.model.Studente;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

@Stateless
public class AgendaService {

    private AgendaRemote agendaRemote;

    public AgendaService() {
        try {
            InitialContext ctx = new InitialContext();
            this.agendaRemote = (AgendaRemote) ctx.lookup("java:global/UniClass/AgendaDAO");
        }
        catch(NamingException e) {
            throw new RuntimeException("Errore durante il lookup di AgendaDAO", e);
        }
    }

    public Agenda trovaAgenda(long id) {
        try {
            return agendaRemote.trovaAgenda(id);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public Agenda trovaAgenda(Studente studente) {
        try {
            return agendaRemote.trovaAgenda(studente);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<Agenda> trovaTutte() {
        return agendaRemote.trovaTutte();
    }

    public void aggiungiAgenda(Agenda agenda) {
        if(agenda != null) {
            agendaRemote.aggiungiAgenda(agenda);
        }
        else {
            throw new RuntimeException("Impossibile creare l'agenda.");
        }
    }

    public void rimuoviAgenda(Agenda agenda) {
        if(agenda != null) {
            agendaRemote.rimuoviAgenda(agenda);
        }
        else {
            throw new RuntimeException("Impossibile eliminare l'agenda. Agenda = null");
        }
    }


}
