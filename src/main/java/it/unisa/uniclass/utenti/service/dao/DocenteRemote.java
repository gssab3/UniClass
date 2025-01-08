package it.unisa.uniclass.utenti.service.dao;

import it.unisa.uniclass.utenti.model.Docente;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface DocenteRemote {
    public Docente trovaDocenteUniClass(String matricola);
    public List<Docente> trovaDocenteCorsoLaurea(String nomeCorsoLaurea);
    public List<Docente> trovaTuttiUniClass();
    public Docente trovaEmailUniClass(String email);
    public void aggiungiDocente(Docente docente);
    public void rimuoviDocente(Docente docente);
}