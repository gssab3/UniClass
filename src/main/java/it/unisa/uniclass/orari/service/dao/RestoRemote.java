package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface RestoRemote {
    public List<Resto> trovaRestiCorsoLaurea(CorsoLaurea corsoLaurea);
    public List<Resto> trovaRestiCorsoLaurea(String nomeCorsoLaurea);
    public List<Resto> trovaResto(String nomeResto);
    public Resto trovaResto(long id);
    public List<Resto> trovaRestoNomeCorso(String nomeResto, CorsoLaurea corso);
    public List<Resto> trovaRestoNomeCorso(String nomeResto, String nomeCorso);
    public void aggiungiResto(Resto resto);
    public void rimuoviResto(Resto resto);
}