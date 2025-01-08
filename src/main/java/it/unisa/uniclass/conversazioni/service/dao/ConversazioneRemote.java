package it.unisa.uniclass.conversazioni.service.dao;

import it.unisa.uniclass.conversazioni.model.Conversazione;

import java.util.List;

public interface ConversazioneRemote {
    public Conversazione trovaConversazione(long id);
    public List<Conversazione> trovaTutte();
    public void aggiungiConversazione(Conversazione c);
    public void rimuoviConversazione(Conversazione c);
}
