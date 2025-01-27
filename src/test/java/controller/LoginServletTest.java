package it.unisa.uniclass.utenti.controller;

import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.PersonaleTA;
import it.unisa.uniclass.utenti.service.AccademicoService;
import it.unisa.uniclass.utenti.service.PersonaleTAService;
import it.unisa.uniclass.common.security.CredentialSecurity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LoginServletTest {

    private LoginServlet loginServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private AccademicoService accademicoService;

    @Mock
    private PersonaleTAService personaleTAService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        loginServlet = new LoginServlet();
    }

    @Test
    public void testLoginSuccessAccademico() throws ServletException, IOException {
        // Simula la richiesta con email e password
        String email = "valid@example.com";
        String password = "validPassword";
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);

        // Mock dei servizi
        Accademico mockAccademico = mock(Accademico.class);
        when(accademicoService.trovaEmailPassUniclass(email, CredentialSecurity.hashPassword(password)))
                .thenReturn(mockAccademico);
        when(mockAccademico.isAttivato()).thenReturn(true);

        // Mock della sessione
        when(request.getSession(true)).thenReturn(session);

        // Esegui il metodo
        loginServlet.doPost(request, response);

        // Verifica che la sessione venga popolata correttamente
        verify(session).setAttribute("currentSessionUser", mockAccademico);

        // Verifica che la reindirizzi a /Home
        verify(response).sendRedirect(request.getContextPath() + "/Home");
    }

    @Test
    public void testLoginFailureAccademico() throws ServletException, IOException {
        // Simula la richiesta con email e password
        String email = "invalid@example.com";
        String password = "invalidPassword";
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);

        // Mock dei servizi
        when(accademicoService.trovaEmailPassUniclass(email, CredentialSecurity.hashPassword(password)))
                .thenReturn(null);
        when(personaleTAService.trovaEmailPass(email, CredentialSecurity.hashPassword(password)))
                .thenReturn(null);

        // Esegui il metodo
        loginServlet.doPost(request, response);

        // Verifica che venga fatto il redirect con errore
        verify(response).sendRedirect(request.getContextPath() + "/Login.jsp?action=error");
    }

    @Test
    public void testLoginSuccessPersonaleTA() throws ServletException, IOException {
        // Simula la richiesta con email e password
        String email = "ta@example.com";
        String password = "taPassword";
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);

        // Mock dei servizi
        PersonaleTA mockPersonaleTA = mock(PersonaleTA.class);
        when(personaleTAService.trovaEmailPass(email, CredentialSecurity.hashPassword(password)))
                .thenReturn(mockPersonaleTA);

        // Mock della sessione
        when(request.getSession(true)).thenReturn(session);

        // Esegui il metodo
        loginServlet.doPost(request, response);

        // Verifica che la sessione venga popolata correttamente
        verify(session).setAttribute("currentSessionUser", mockPersonaleTA);

        // Verifica che la reindirizzi a /Home
        verify(response).sendRedirect(request.getContextPath() + "/Home");
    }

    @Test
    public void testLoginNotActivatedAccademico() throws ServletException, IOException {
        // Simula la richiesta con email e password
        String email = "notactivated@example.com";
        String password = "validPassword";
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);

        // Mock dei servizi
        Accademico mockAccademico = mock(Accademico.class);
        when(accademicoService.trovaEmailPassUniclass(email, CredentialSecurity.hashPassword(password)))
                .thenReturn(mockAccademico);
        when(mockAccademico.isAttivato()).thenReturn(false);

        // Esegui il metodo
        loginServlet.doPost(request, response);

        // Verifica che venga fatto il redirect con "notactivated"
        verify(response).sendRedirect(request.getContextPath() + "/Login.jsp?action=notactivated");
    }
}

