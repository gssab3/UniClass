package com.example.service;

import it.unisa.uniclass.common.exceptions.AuthenticationException;
import it.unisa.uniclass.utenti.controller.LoginServlet;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.PersonaleTA;
import it.unisa.uniclass.utenti.model.Utente;
import it.unisa.uniclass.utenti.service.UtenteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginServletTest {

    // Mock degli oggetti necessari per il test
    @InjectMocks
    private LoginServlet loginServlet; // La servlet da testare

    @Mock
    private UtenteService utenteService; // Mock del servizio UtenteService

    @Mock
    private HttpServletRequest request; // Mock della richiesta HTTP

    @Mock
    private HttpServletResponse response; // Mock della risposta HTTP

    @Mock
    private HttpSession session; // Mock della sessione HTTP

    @Captor
    private ArgumentCaptor<String> captor; // Per catturare i redirect

    @BeforeEach
    void setUp() {
        // Inizializzazione dei mock e della servlet
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccessForAccademico() throws IOException {
        // Simula i parametri della richiesta (email e password)
        when(request.getParameter("email")).thenReturn("accademico@unisa.it");
        when(request.getParameter("password")).thenReturn("password123");

        // Crea un utente Accademico valido e attivato
        Accademico accademico = new Accademico();
        accademico.setEmail("accademico@unisa.it");
        accademico.setPassword("password123");
        accademico.setAttivato(true);

        // Simula il comportamento del servizio per restituire l'utente Accademico
        when(utenteService.retrieveByUserAndPassword("accademico@unisa.it", "password123")).thenReturn(accademico);

        // Simula la sessione HTTP
        when(request.getSession(true)).thenReturn(session);

        // Esegue il metodo della servlet
        loginServlet.doPost(request, response);

        // Verifica che l'utente venga salvato nella sessione
        verify(session).setAttribute("currentSessionUser", accademico);

        // Verifica che l'utente venga reindirizzato alla Home
        verify(response).sendRedirect(contains("/Home"));
    }

    @Test
    void testLoginSuccessForPersonaleTA() throws IOException {
        // Simula i parametri della richiesta (email e password)
        when(request.getParameter("email")).thenReturn("personale@unisa.it");
        when(request.getParameter("password")).thenReturn("password123");

        // Crea un utente PersonaleTA valido
        PersonaleTA personaleTA = new PersonaleTA();
        personaleTA.setEmail("personale@unisa.it");
        personaleTA.setPassword("password123");

        // Simula il comportamento del servizio per restituire l'utente PersonaleTA
        when(utenteService.retrieveByUserAndPassword("personale@unisa.it", "password123")).thenReturn(personaleTA);

        // Simula la sessione HTTP
        when(request.getSession(true)).thenReturn(session);

        // Esegue il metodo della servlet
        loginServlet.doPost(request, response);

        // Verifica che l'utente venga salvato nella sessione
        verify(session).setAttribute("currentSessionUser", personaleTA);

        // Verifica che l'utente venga reindirizzato alla Home
        verify(response).sendRedirect(contains("/Home"));
    }

    @Test
    void testLoginFailureInvalidPassword() throws IOException {
        // Simula i parametri della richiesta (email e password)
        when(request.getParameter("email")).thenReturn("accademico@unisa.it");
        when(request.getParameter("password")).thenReturn("wrongpassword");

        // Simula il comportamento del servizio per generare un'eccezione di autenticazione
        when(utenteService.retrieveByUserAndPassword("accademico@unisa.it", "wrongpassword"))
                .thenThrow(new AuthenticationException("Credenziali non valide"));

        // Esegue il metodo della servlet
        loginServlet.doPost(request, response);

        // Verifica che l'utente venga reindirizzato alla pagina di login con un messaggio di errore
        verify(response).sendRedirect(contains("/Login.jsp?action=error"));
    }

    @Test
    void testLoginFailureUserNotActivated() throws IOException {
        // Simula i parametri della richiesta (email e password)
        when(request.getParameter("email")).thenReturn("accademico@unisa.it");
        when(request.getParameter("password")).thenReturn("password123");

        // Crea un utente Accademico valido ma non attivato
        Accademico accademico = new Accademico();
        accademico.setEmail("accademico@unisa.it");
        accademico.setPassword("password123");
        accademico.setAttivato(false);

        // Simula il comportamento del servizio per restituire l'utente Accademico
        when(utenteService.retrieveByUserAndPassword("accademico@unisa.it", "password123")).thenReturn(accademico);

        // Esegue il metodo della servlet
        loginServlet.doPost(request, response);

        // Verifica che l'utente venga reindirizzato alla pagina di login con un messaggio di "non attivato"
        verify(response).sendRedirect(contains("/Login.jsp?action=notactivated"));
    }

    @Test
    void testLoginFailureUserNotFound() throws IOException {
        // Simula i parametri della richiesta (email e password)
        when(request.getParameter("email")).thenReturn("nonexistent@unisa.it");
        when(request.getParameter("password")).thenReturn("password123");

        // Simula il comportamento del servizio per restituire null (utente non trovato)
        when(utenteService.retrieveByUserAndPassword("nonexistent@unisa.it", "password123")).thenReturn(null);

        // Esegue il metodo della servlet
        loginServlet.doPost(request, response);

        // Verifica che l'utente venga reindirizzato alla pagina di login con un messaggio di errore
        verify(response).sendRedirect(contains("/Login.jsp?action=error"));
    }
}
