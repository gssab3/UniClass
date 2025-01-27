package com.example.controller;

import it.unisa.uniclass.common.security.CredentialSecurity;
import it.unisa.uniclass.utenti.controller.LoginServlet;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.service.AccademicoService;
import it.unisa.uniclass.utenti.service.PersonaleTAService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class LoginServletTest {

    @Test
    public void testLoginSuccess() throws Exception {
        // Mock delle dipendenze
        AccademicoService accademicoService = mock(AccademicoService.class);
        PersonaleTAService personaleTAService = mock(PersonaleTAService.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        // Mock del comportamento
        when(request.getParameter("email")).thenReturn("test@unisa.it");
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getSession(true)).thenReturn(session);

        Accademico accademico = new Accademico();
        accademico.setEmail("test@unisa.it");
        accademico.setPassword(CredentialSecurity.hashPassword("password123"));
        accademico.setAttivato(true);

        when(accademicoService.trovaEmailPassUniclass(anyString(), anyString())).thenReturn(accademico);

        // Configura la servlet
        LoginServlet servlet = new LoginServlet();
        servlet.setAccademicoService(accademicoService);
        servlet.setPersonaleTAService(personaleTAService);

        // Esegui la servlet
        servlet.doPost(request, response);

        // Verifica il comportamento
        verify(session).setAttribute(eq("currentSessionUser"), eq(accademico));
        verify(response).sendRedirect(contains("/Home"));
    }

    @Test
    public void testLoginFailure() throws Exception {
        // Mock delle dipendenze
        AccademicoService accademicoService = mock(AccademicoService.class);
        PersonaleTAService personaleTAService = mock(PersonaleTAService.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock del comportamento
        when(request.getParameter("email")).thenReturn("wrong@unisa.it");
        when(request.getParameter("password")).thenReturn("wrongpassword");

        when(accademicoService.trovaEmailPassUniclass(anyString(), anyString())).thenReturn(null);
        when(personaleTAService.trovaEmailPass(anyString(), anyString())).thenReturn(null);

        // Configura la servlet
        LoginServlet servlet = new LoginServlet();
        servlet.setAccademicoService(accademicoService);
        servlet.setPersonaleTAService(personaleTAService);

        // Esegui la servlet
        servlet.doPost(request, response);

        // Verifica il comportamento
        verify(response).sendRedirect(contains("/Login.jsp?action=error"));
    }
}
