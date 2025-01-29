package com.example.controller;

import it.unisa.uniclass.common.security.CredentialSecurity;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.PersonaleTA;
import it.unisa.uniclass.utenti.controller.LoginServlet;
import it.unisa.uniclass.utenti.service.AccademicoService;
import it.unisa.uniclass.utenti.service.PersonaleTAService;
import it.unisa.uniclass.utenti.service.dao.AccademicoRemote;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

class LoginServletTest {

    @Mock
    private AccademicoRemote academicDao;

    @Mock
    private PersonaleTAService personaleTAService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    private LoginServlet loginServlet;
    private AccademicoService academicService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        academicService = new AccademicoService(academicDao);
        loginServlet = new LoginServlet();
        loginServlet.setAccademicoService(academicService);
        loginServlet.setPersonaleTAService(personaleTAService);
    }

    @Test
    void doPost_ValidAccademico_SuccessfulLogin() throws ServletException, IOException {
        // Arrange
        String email = "giannisereni@unisa.it";
        String password = "3201";
        String hashedPassword = CredentialSecurity.hashPassword(password);
        Accademico accademico = new Accademico();
        accademico.setEmail(email);
        accademico.setPassword(hashedPassword);
        accademico.setAttivato(true);


        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(academicDao.trovaEmailUniClass(email)).thenReturn(accademico);
        when(personaleTAService.trovaEmailPass(email, hashedPassword)).thenReturn(null);
        when(request.getSession(true)).thenReturn(session);
        when(request.getContextPath()).thenReturn("/Home");
        // DEBUG: Stampa valori prima di eseguire la servlet
        System.out.println("Email fornita: " + request.getParameter("email"));
        System.out.println("Password fornita: " + request.getParameter("password"));
        System.out.println("Utente trovato nel DB mockato: " + academicDao.trovaEmailUniClass(email));


        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("currentSessionUser", accademico);
        verify(response).sendRedirect("/Home");
    }

    // ... other test methods ...
}