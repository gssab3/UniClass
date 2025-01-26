package it.unisa.uniclass.utenti.service.dao;

import it.unisa.uniclass.utenti.model.Accademico;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface AccademicoRemote {
    public Accademico trovaAccademicoUniClass(String matricola);
    public List<Accademico> trovaTuttiUniClass();
    public Accademico trovaEmailUniClass(String email);
    public List<Accademico> trovaAttivati(boolean attivazione);
    public void aggiungiAccademico(Accademico accademico);
    public void rimuoviAccademico(Accademico accademico);
    public List<String> retrieveEmail();
}
