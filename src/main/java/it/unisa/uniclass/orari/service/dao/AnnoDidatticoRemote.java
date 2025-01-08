package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.AnnoDidattico;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface AnnoDidatticoRemote {

    public List<AnnoDidattico> trovaAnno(String anno);
    public AnnoDidattico trovaId(int id);
    public List<AnnoDidattico> trovaTutti();
    public void aggiungiAnno(AnnoDidattico annoDidattico);
    public void rimuoviAnno(AnnoDidattico annoDidattico);
}
