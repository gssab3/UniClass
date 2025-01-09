package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.AnnoDidattico;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface AnnoDidatticoRemote {

    public List<AnnoDidattico> trovaAnno(String anno);
    public AnnoDidattico trovaId(int id);
    public List<AnnoDidattico> trovaTutti();
    public List<AnnoDidattico> trovaTuttiCorsoLaurea(long id);
    public AnnoDidattico trovaCorsoLaureaNome(long id, String anno);
    public void aggiungiAnno(AnnoDidattico annoDidattico);
    public void rimuoviAnno(AnnoDidattico annoDidattico);
}
