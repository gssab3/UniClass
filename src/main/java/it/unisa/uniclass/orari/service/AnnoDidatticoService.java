package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.AnnoDidattico;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import it.unisa.uniclass.orari.service.dao.AnnoDidatticoRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AnnoDidatticoService {

    private AnnoDidatticoRemote annoDidatticoDao;

    public AnnoDidatticoService() {
        try {
            InitialContext ctx = new InitialContext();
            this.annoDidatticoDao = (AnnoDidatticoRemote) ctx.lookup("java:global/UniClass/AnnoDidatticoDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di AnnoDidatticoDAO.", e);
        }
    }

    public List<AnnoDidattico> trovaAnno(String anno) {
        return annoDidatticoDao.trovaAnno(anno);
    }

    public AnnoDidattico trovaId(int id) {
        try {
            return annoDidatticoDao.trovaId(id);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<AnnoDidattico> trovaTutti() {
        return annoDidatticoDao.trovaTutti();
    }

    public void aggiungiAnno(AnnoDidattico annoDidattico) {
        annoDidatticoDao.aggiungiAnno(annoDidattico);
    }

    public void rimuoviAnno(AnnoDidattico annoDidattico) {
        annoDidatticoDao.rimuoviAnno(annoDidattico);
    }

    public List<AnnoDidattico> trovaTuttiCorsoLaurea(long id) {
        return annoDidatticoDao.trovaTuttiCorsoLaurea(id);
    }

    public AnnoDidattico trovaTuttiCorsoLaureaNome(long id, String anno) {
        try {
            return annoDidatticoDao.trovaCorsoLaureaNome(id, anno);
        }
        catch(NoResultException e) {
            return null;
        }
    }
}
