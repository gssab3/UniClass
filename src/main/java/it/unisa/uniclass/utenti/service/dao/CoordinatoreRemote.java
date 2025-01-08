package it.unisa.uniclass.utenti.service.dao;

import it.unisa.uniclass.utenti.model.Coordinatore;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface CoordinatoreRemote {
    public Coordinatore trovaCoordinatoreEmailUniclass(String email);
    public Coordinatore trovaCoordinatoreUniClass(String matricola);
    public List<Coordinatore> trovaCoordinatoriCorsoLaurea(String nomeCorsoLaurea);
    public List<Coordinatore> trovaTutti();
    public void aggiungiCoordinatore(Coordinatore coordinatore);
    public void rimuoviCoordinatore(Coordinatore coordinatore);
}
