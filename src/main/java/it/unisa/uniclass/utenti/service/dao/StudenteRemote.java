package it.unisa.uniclass.utenti.service.dao;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.utenti.model.Studente;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface StudenteRemote {
    public Studente trovaStudenteUniClass(String matricola);
    public List<Studente> trovaStudentiCorso(CorsoLaurea corsoLaurea);
    public List<Studente> trovaTuttiUniClass();
    public Studente trovaStudenteEmailUniClass(String email);
    public void aggiungiStudente(Studente studente);
    public void rimuoviStudente(Studente studente);
}
