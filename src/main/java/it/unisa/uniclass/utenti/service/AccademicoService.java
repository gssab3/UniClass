package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.service.dao.AccademicoRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

@Stateless
public class AccademicoService {

    private AccademicoRemote accademicoDao;

    public AccademicoService() {
        try {
            InitialContext ctx = new InitialContext();
            accademicoDao = (AccademicoRemote) ctx.lookup("java:global/UniClass/AgendaDAO");
        }
        catch(NamingException e) {
            throw new RuntimeException("Errore durante il lookup di AccademicoDAO", e);
        }
    }

    public Accademico trovaAccademicoUniClass(String matricola) {
        try {
            return accademicoDao.trovaAccademicoUniClass(matricola);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<Accademico> trovaTuttiUniClass() {
        return accademicoDao.trovaTuttiUniClass();
    }


    public Accademico trovaEmailUniClass(String email) {
        try {
            return accademicoDao.trovaEmailUniClass(email);
        }
        catch(NoResultException e) {
            return null;
        }
    }



    public void aggiungiAccademico(Accademico accademico) {
        accademicoDao.aggiungiAccademico(accademico);
    }

    public void rimuoviAccademico(Accademico accademico) {
        accademicoDao.rimuoviAccademico(accademico);
    }
}
