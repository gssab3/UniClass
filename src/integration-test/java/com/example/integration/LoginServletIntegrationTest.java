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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.mockito.Mockito.*;

public class LoginIntegrationTest {

    // Mock HTTP components
    // Mock objects for the servlet request, response, and session
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;

    // Mock objects for database-related components
    @Mock private InitialContext initialContext;
    @Mock private DataSource dataSource;
    @Mock private Connection connection;
    @Mock private PreparedStatement preparedStatement;
    @Mock private ResultSet resultSet;

    // The actual components we're testing
    private AccademicoDAO accademicoDAO;
    private AccademicoService accademicoService;
    private LoginServlet loginServlet;

    @BeforeEach
    void setUp() throws Exception {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);

        // Set up the JNDI lookup for the DataSource
        when(initialContext.lookup("java:comp/env/jdbc/UniClass")).thenReturn(dataSource);
        when(dataSource.getConnection()).thenReturn(connection);

        // Create real DAO with mocked database connection
        accademicoDAO = new AccademicoDAO() {
            @Override
            protected Connection getConnection() {
                return connection;
            }
        };

        // Create real AccademicoService with the real DAO
        accademicoService = new AccademicoService() {
            @Override
            protected AccademicoDAO getAccademicoDAO() {
                return accademicoDAO;
            }
        };

        // Create LoginServlet with the real AccademicoService
        loginServlet = new LoginServlet() {
            @Override
            protected AccademicoService getAccademicoService() {
                return accademicoService;
            }
        };

        // Set up common request attributes
        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testSuccessfulLogin() throws ServletException, IOException {
        // Arrange: Set up test data
        String email = "test@example.com";
        String password = "password123";

        // Set up request parameters
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);

        // Mock database query results for a successful login
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("email")).thenReturn(email);
        when(resultSet.getString("password")).thenReturn(password);
        when(resultSet.getBoolean("attivato")).thenReturn(true);

        // Act: Perform the login
        loginServlet.doPost(request, response);

        // Assert: Verify the expected outcomes
        verify(session).setAttribute(eq("currentSessionUser"), any(Accademico.class));
        verify(response).sendRedirect("/Home");
    }

    @Test
    void testFailedLogin() throws ServletException, IOException {
        // Arrange: Set up test data
        String email = "test@example.com";
        String password = "wrongpassword";

        // Set up request parameters
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);

        // Mock database query results for a failed login (no user found)
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        // Act: Perform the login
        loginServlet.doPost(request, response);

        // Assert: Verify the expected outcomes
        verify(session, never()).setAttribute(eq("currentSessionUser"), any(Accademico.class));
        verify(response).sendRedirect(contains("error"));
    }
}
