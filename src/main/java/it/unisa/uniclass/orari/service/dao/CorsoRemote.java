package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.Corso;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface CorsoRemote {
    public Corso trovaCorso(long id);
    public List<Corso> trovaCorsiCorsoLaurea(String nomeCorsoLaurea);
    public List<Corso> trovaTutti();
    public void aggiungiCorso(Corso corso);
    public void rimuoviCorso(Corso corso);
}
