package com.example.integration;

import it.unisa.uniclass.utenti.controller.LoginServlet;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.service.AccademicoService;
import it.unisa.uniclass.utenti.service.dao.AccademicoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServletIntegrationTest {

    private AccademicoDAO accademicoDAO;
    private AccademicoService accademicoService;
    private LoginServlet loginServlet;
    private Connection connection;

    @BeforeEach
    void setUp() throws NamingException, SQLException {
        // Recupero il DataSource dal contesto JNDI
        InitialContext ctx = new InitialContext();
        DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/UniClass");

        // Recupero una connessione reale al database
        connection = dataSource.getConnection();

        // Inizializzo il DAO con connessione reale
        accademicoDAO = new AccademicoDAO();

        // Inizializzo il servizio con il DAO reale
        accademicoService = new AccademicoService();

        // Inizializzo la servlet con il servizio reale
        loginServlet = new LoginServlet();
    }

    @Test
    void testSuccessfulLogin() throws ServletException, IOException, SQLException {
        // Dati di test
        String email = "test@example.com";
        String password = "password123";

        // Inserisco un utente di test nel database
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO accademico (email, password, attivato) VALUES (?, ?, ?)")) {
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setBoolean(3, true);
            ps.executeUpdate();
        }

        // Creo un'istanza di request e response
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();

        request.setParameter("email", email);
        request.setParameter("password", password);

        // Eseguo il login
        loginServlet.doPost(request, response);

        // Verifico che l'utente sia stato autenticato correttamente
        Accademico utenteAutenticato = (Accademico) session.getAttribute("currentSessionUser");
        assertNotNull(utenteAutenticato);
        assertEquals(email, utenteAutenticato.getEmail());
    }

    @Test
    void testFailedLogin() throws ServletException, IOException {
        // Dati di test per login fallito
        String email = "nonEsiste@example.com";
        String password = "wrongpassword";

        // Creo un'istanza di request e response
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();

        request.setParameter("email", email);
        request.setParameter("password", password);

        // Eseguo il login
        loginServlet.doPost(request, response);

        // Verifico che nessun utente sia stato autenticato
        Accademico utenteAutenticato = (Accademico) session.getAttribute("currentSessionUser");
        assertNull(utenteAutenticato);
    }
}
