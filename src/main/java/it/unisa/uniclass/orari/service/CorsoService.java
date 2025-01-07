package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.Corso;
import it.unisa.uniclass.orari.service.dao.CorsoRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

@Stateless
public class CorsoService {

    private CorsoRemote corsoDao;

    public CorsoService() throws NamingException {
        try {
            InitialContext ctx = new InitialContext();
            this.corsoDao = (CorsoRemote) ctx.lookup("java:global/UniClass/CorsoDAO");
        }
        catch(NamingException e) {
            throw new RuntimeException("Errore durante il lookup di CorsoDAO", e);
        }
    }

    public Corso trovaCorso(long id) {
        try {
            return corsoDao.trovaCorso(id);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<Corso> trovaCorsiCorsoLaurea(String nomeCorsoLaurea) {
        return corsoDao.trovaCorsiCorsoLaurea(nomeCorsoLaurea);
    }

    public List<Corso> trovaTutti() {
        return corsoDao.trovaTutti();
    }

    public void aggiungiCorso(Corso corso) {
        corsoDao.aggiungiCorso(corso);
    }

    public void rimuoviCorso(Corso corso) {
        corsoDao.rimuoviCorso(corso);
    }
}
