package it.unisa.uniclass.conversazioni.service;

import it.unisa.uniclass.conversazioni.model.Conversazione;
import it.unisa.uniclass.conversazioni.service.dao.ConversazioneRemote;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.service.AccademicoService;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ConversazioneService {

    private ConversazioneRemote conversazioneDao;

    public ConversazioneService() {
        try {
            InitialContext ctx = new InitialContext();
            conversazioneDao = (ConversazioneRemote) ctx.lookup("java:global/UniClass/ConversazioneDAO");
        }
        catch(NamingException e) {
            throw new RuntimeException("Impossibile avere messaggioDAO", e);
        }
    }

    public Conversazione trovaConversazione(long id) {
        try {
            return conversazioneDao.trovaConversazione(id);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<Conversazione> trovaTutte() {
        return conversazioneDao.trovaTutte();
    }

    public List<Conversazione> trovaConversazioneAccademico(Accademico accademico) {
        AccademicoService service = new AccademicoService();
        List<Conversazione> results = new ArrayList<>();
        List<Conversazione> conversazioni = conversazioneDao.trovaTutte();
        for(Conversazione c : conversazioni) {
            if(c.getMessaggeri().contains(accademico))
                results.add(c);
        }
        return results;
    }

    public void aggiungiConversazione(Conversazione c) {
        if(c != null) {
            conversazioneDao.aggiungiConversazione(c);
        }
    }

    public void rimuoviConversazione(Conversazione c) {
        if(c != null) {
            conversazioneDao.rimuoviConversazione(c);
        }
    }

    public Accademico trovaAltroConversazione(Conversazione c, Accademico acc) {
        for(Accademico x : c.getMessaggeri()) {
            if(!x.getMatricola().equals(acc.getMatricola())) {
                return x;
            }
        }
        return null;
    }

    public List<Accademico> trovaAccademiciConversazione(Accademico acc) {
        List<Conversazione> conversazioniAccademico = trovaConversazioneAccademico(acc);
        List<Accademico> results = new ArrayList<>();
        for(Conversazione c : conversazioniAccademico) {
            results.add(trovaAltroConversazione(c,acc));
        }
        return results;
    }
}
