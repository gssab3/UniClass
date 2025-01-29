package com.example.controller;

import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Tipo;
import it.unisa.uniclass.utenti.service.AccademicoService;
import it.unisa.uniclass.utenti.controller.AttivaUtentiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

class AttivaUtentiServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AccademicoService accademicoService;

    private AttivaUtentiServlet servlet;

    @BeforeEach
    void setUp() throws ServletException {
        MockitoAnnotations.openMocks(this);
        servlet = new AttivaUtentiServlet();
        servlet.init();
        servlet.setAccademicoService(accademicoService);
    }

    @Test
    void testDoPost_AddValidUser() throws ServletException, IOException {
        // Arrange
        when(request.getParameter("param")).thenReturn("add");
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("matricola")).thenReturn("12345");
        when(request.getParameter("tipo")).thenReturn("Studente");
        when(request.getContextPath()).thenReturn("/context");

        Accademico accademico = new Accademico();
        accademico.setEmail("test@example.com");
        accademico.setMatricola("12345");
        accademico.setTipo(Tipo.Studente);

        when(accademicoService.trovaEmailUniClass("test@example.com")).thenReturn(accademico);
        when(accademicoService.trovaAccademicoUniClass("12345")).thenReturn(accademico);

        // Act
        servlet.doPostPublic(request, response);

        // Assert
        verify(accademicoService).aggiungiAccademico(argThat(acc ->
                acc.getEmail().equals("test@example.com") &&
                        acc.getMatricola().equals("12345") &&
                        acc.getTipo() == Tipo.Studente
        ));
        verify(response).sendRedirect("/context/PersonaleTA/AttivaUtenti.jsp");
    }

    @Test
    void testDoPost_AddInvalidUser() throws ServletException, IOException {
        // Arrange
        when(request.getParameter("param")).thenReturn("add");
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("matricola")).thenReturn("12345");
        when(request.getParameter("tipo")).thenReturn("Studente");
        when(request.getContextPath()).thenReturn("/context");

        Accademico accademico1 = new Accademico();
        accademico1.setEmail("test@example.com");
        accademico1.setMatricola("12345");
        accademico1.setTipo(Tipo.Docente);

        Accademico accademico2 = new Accademico();
        accademico2.setEmail("test@example.com");
        accademico2.setMatricola("54321");
        accademico2.setTipo(Tipo.Studente);

        when(accademicoService.trovaEmailUniClass("test@example.com")).thenReturn(accademico1);
        when(accademicoService.trovaAccademicoUniClass("12345")).thenReturn(accademico2);

        // Act
        servlet.doPostPublic(request, response);

        // Assert
        verify(accademicoService, never()).aggiungiAccademico(any(Accademico.class));
        verify(response).sendRedirect("/context/PersonaleTA/AttivaUtenti.jsp?action=error");
    }

    @Test
    void testDoPost_RemoveUser() throws ServletException, IOException {
        // Arrange
        when(request.getParameter("param")).thenReturn("remove");
        when(request.getParameter("email-remove")).thenReturn("test@example.com");
        when(request.getContextPath()).thenReturn("/context");

        Accademico accademico = new Accademico();
        accademico.setEmail("test@example.com");

        when(accademicoService.trovaEmailUniClass("test@example.com")).thenReturn(accademico);

        // Act
        servlet.doPostPublic(request, response);

        // Assert
        verify(accademicoService).cambiaAttivazione(accademico, false);
        verify(response).sendRedirect("/context/PersonaleTA/AttivaUtenti.jsp");
    }
}