package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface CorsoLaureaRemote {

    public CorsoLaurea trovaCorsoLaurea(long id);
    public CorsoLaurea trovaCorsoLaurea(String nome);
    public List<CorsoLaurea> trovaTutti();
    public void aggiungiCorsoLaurea(CorsoLaurea corsoLaurea);
    public void rimuoviCorsoLaurea(CorsoLaurea corsoLaurea);
}
