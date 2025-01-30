package it.unisa.uniclass.utenti.service.dao;

import it.unisa.uniclass.utenti.model.Accademico;
import jakarta.activation.DataSource;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import jakarta.resource.cci.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Stateless(name = "AccademicoDAO")
public class AccademicoDAO implements AccademicoRemote {

    private DataSource dataSource;

    }

    // Costruttore che riceve un DataSource
    public AccademicoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Metodo per ottenere una connessione JDBC standard
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();  // Assicurati che sia una Connection JDBC
    }

    // Esempio di un metodo che usa la connessione
    public void doSomethingWithConnection() {
        try (Connection connection = getConnection()) {
            // Esegui le operazioni sul database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniclass;

    @Override
    public Accademico trovaAccademicoUniClass(String matricola){
        try {
            TypedQuery<Accademico> query = emUniclass.createNamedQuery(Accademico.TROVA_ACCADEMICO, Accademico.class);
            query.setParameter("matricola", matricola);
            return query.getSingleResult();
        } catch (jakarta.persistence.PersistenceException e) {
            return null;
        }
    }

    @Override
    public List<Accademico> trovaTuttiUniClass() {
        TypedQuery<Accademico> query = emUniclass.createNamedQuery(Accademico.TROVA_TUTTI, Accademico.class);
        return query.getResultList();
    }

    @Override
    public Accademico trovaEmailUniClass(String email) {
        try {
            TypedQuery<Accademico> query = emUniclass.createNamedQuery(Accademico.TROVA_EMAIL, Accademico.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Accademico> trovaAttivati(boolean attivazione) {
        TypedQuery<Accademico> query = emUniclass.createNamedQuery(Accademico.TROVA_ATTIVATI, Accademico.class);
        query.setParameter("attivato", attivazione);
        return query.getResultList();
    }



    @Override
    public void aggiungiAccademico(Accademico accademico) {
        emUniclass.merge(accademico);
    }

    @Override
    public void rimuoviAccademico(Accademico accademico) {
        emUniclass.remove(accademico);
    }

    @Override
    public List<String> retrieveEmail() {
        TypedQuery<String> query = emUniclass.createNamedQuery(Accademico.RETRIEVE_EMAIL, String.class);
        return query.getResultList();
    }

    @Override
    public void cambiaAttivazione(Accademico accademico, boolean attivazione) {
        accademico.setAttivato(attivazione);
        emUniclass.merge(accademico);
    }
}
