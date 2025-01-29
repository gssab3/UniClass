package com.example.integration;

import com.zaxxer.hikari.HikariDataSource;
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

import jakarta.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginServletIntegrationTest {

    private DataSource dataSource;
    private Connection connection;
    private AccademicoDAO accademicoDAO;
    private AccademicoService accademicoService;
    private LoginServlet loginServlet;

    @BeforeEach
    void setUp() throws SQLException {
        // Crea un DataSource per H2 in memoria
        HikariDataSource h2DataSource = new HikariDataSource();
        h2DataSource.setJdbcUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"); // Database in memoria
        h2DataSource.setUsername("sa");
        h2DataSource.setPassword("");

        // Usa il DataSource per ottenere la connessione
        dataSource = h2DataSource;
        connection = dataSource.getConnection();

        // Inizializza DAO e Service (AccademicoDAO deve ricevere una Connection)
        accademicoDAO = new AccademicoDAO(dataSource);  // Passa il DataSource al costruttore di AccademicoDAO
        accademicoService = new AccademicoService(accademicoDAO);
        loginServlet = new LoginServlet();  // Usa la servlet come al solito
    }

    @Test
    void testSuccessfulLogin() throws ServletException, IOException {
        // Dati di test
        String email = "test@example.com";
        String password = "password123";

        // Inserimento di un utente di test nel database H2
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO accademico (email, password, attivato) VALUES (?, ?, ?)")) {
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setBoolean(3, true);
            ps.executeUpdate();
        }

        // Mock della richiesta, risposta e sessione HTTP
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);

        // Esegui il login
        loginServlet.doPost(request, response);

        // Verifica che l'utente sia stato autenticato correttamente
        verify(session).setAttribute(eq("currentSessionUser"), any(Accademico.class));
        verify(response).sendRedirect("/Home");
    }

    @Test
    void testFailedLogin() throws ServletException, IOException {
        // Dati di test per login fallito
        String email = "nonEsiste@example.com";
        String password = "wrongpassword";

        // Mock della richiesta, risposta e sessione HTTP
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);

        // Esegui il login
        loginServlet.doPost(request, response);

        // Verifica che nessun utente sia stato autenticato
        verify(session, never()).setAttribute(eq("currentSessionUser"), any(Accademico.class));
        verify(response).sendRedirect(contains("error"));
    }
}
