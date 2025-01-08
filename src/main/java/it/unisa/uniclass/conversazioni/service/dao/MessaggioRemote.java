package it.unisa.uniclass.conversazioni.service.dao;

import it.unisa.uniclass.conversazioni.model.Messaggio;
import jakarta.ejb.Remote;

import java.time.LocalDateTime;
import java.util.List;

@Remote
public interface MessaggioRemote {

    public Messaggio trovaMessaggio(long id);
    public List<Messaggio> trovaMessaggiInviati(String matricola);
    public List<Messaggio> trovaMessaggiInviatiConversazione(String matricola, long id);
    public List<Messaggio> trovaMessaggeri(String matricola1, String matricola2);
    public List<Messaggio> trovaTutti();
    public List<Messaggio> trovaAvvisi();
    public List<Messaggio> trovaAvvisiAutore(String autore);
    public List<Messaggio> trovaMessaggiData(LocalDateTime dateTime);
    public void aggiungiMessaggio(Messaggio messaggio);
    public void rimuoviMessaggio(Messaggio messaggio);

}
