package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.Agenda;
import it.unisa.uniclass.utenti.model.Studente;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface AgendaRemote {

    public Agenda trovaAgenda(long id);
    public Agenda trovaAgenda(Studente studente);
    public List<Agenda> trovaTutte();
    public void aggiungiAgenda(Agenda agenda);
    public void rimuoviAgenda(Agenda agenda);
}
