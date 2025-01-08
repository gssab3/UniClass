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

    public List<AnnoDidattico> trovaAnno(int anno) {
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

    public List<AnnoDidattico> trovaPerCorsoLaurea(CorsoLaurea corsoLaurea) {
        String query = "SELECT a FROM AnnoDidattico a " +
                "JOIN FETCH a.corsiLaurea c " +
                "WHERE c = :corsoLaurea";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBUniClassPU");
        EntityManager em = emf.createEntityManager();

        return em.createQuery(query, AnnoDidattico.class)
                .setParameter("corsoLaurea", corsoLaurea)
                .getResultList();
    }

    /*
    public List<AnnoDidattico> trovaPerCorsoLaurea(CorsoLaurea corso) {
        List<AnnoDidattico> anni = annoDidatticoDao.trovaTutti();
        List<AnnoDidattico> results = new ArrayList<AnnoDidattico>();
        for(AnnoDidattico annoDidattico : anni) {
            if(annoDidattico.getCorsiLaurea().contains(corso))
                results.add(annoDidattico);
        }
        return results;
    }*/
}
