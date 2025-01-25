package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.Aula;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface AulaRemote {
    public Aula trovaAula(int id);
    public Aula trovaAula(String nome);
    public List<Aula> trovaTutte();
    public List<Aula> trovaAuleEdificio(String edificio);
    public List<String> trovaEdifici();
    public void aggiungiAula(Aula aula);
    public void rimuoviAula(Aula aula);
}
