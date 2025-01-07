package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.Giorno;
import it.unisa.uniclass.orari.model.Lezione;
import jakarta.ejb.Remote;

import java.sql.Time;
import java.util.List;

@Remote
public interface LezioneRemote {
    public Lezione trovaLezione(long id);
    public List<Lezione> trovaLezioniCorso(String nomeCorso);
    public List<Lezione> trovaLezioniOre(Time oraInizio, Time oraFine);
    public List<Lezione> trovaLezioniOreGiorno(Time oraInizio, Time oraFine, Giorno giorno);
    public List<Lezione> trovaLezioniAule(String nome);
    public List<Lezione> trovaTutte();
    public void aggiungiLezione(Lezione l);
    public void rimuoviLezione(Lezione l);
}