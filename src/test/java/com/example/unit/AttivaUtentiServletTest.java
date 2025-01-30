/*
Test Case 1          (Key = 1.1.1.1.1.1.)
   Formato      :  MATCorretto
   Database     :  MATPresente
   Formato      :  EMAILCorretto
   Database     :  EMAILPresente
   Formato      :  TipoStudente
   Associazione :  Corretta


Test Case 2          (Key = 1.1.1.1.1.2.)
   Formato      :  MATCorretto
   Database     :  MATPresente
   Formato      :  EMAILCorretto
   Database     :  EMAILPresente
   Formato      :  TipoStudente
   Associazione :  Errata


Test Case 3          (Key = 1.1.1.2.1.2.)
   Formato      :  MATCorretto
   Database     :  MATPresente
   Formato      :  EMAILCorretto
   Database     :  EMAILNon presente
   Formato      :  TipoStudente
   Associazione :  Errata


Test Case 4          (Key = 1.1.2.2.1.2.)
   Formato      :  MATCorretto
   Database     :  MATPresente
   Formato      :  EMAILNon corretto
   Database     :  EMAILNon presente
   Formato      :  TipoStudente
   Associazione :  Errata


Test Case 5          (Key = 1.2.1.2.1.2.)
   Formato      :  MATCorretto
   Database     :  MATNon presente
   Formato      :  EMAILCorretto
   Database     :  EMAILNon presente
   Formato      :  TipoStudente
   Associazione :  Errata


Test Case 6          (Key = 1.2.2.2.1.2.)
   Formato      :  MATCorretto
   Database     :  MATNon presente
   Formato      :  EMAILNon corretto
   Database     :  EMAILNon presente
   Formato      :  TipoStudente
   Associazione :  Errata


Test Case 7          (Key = 2.2.1.2.1.2.)
   Formato      :  MATNon corretto
   Database     :  MATNon presente
   Formato      :  EMAILCorretto
   Database     :  EMAILNon presente
   Formato      :  TipoStudente
   Associazione :  Errata


Test Case 8          (Key = 2.2.2.2.1.2.)
   Formato      :  MATNon corretto
   Database     :  MATNon presente
   Formato      :  EMAILNon corretto
   Database     :  EMAILNon presente
   Formato      :  TipoStudente
   Associazione :  Errata
 */

package com.example.unit;

import it.unisa.uniclass.utenti.model.Docente;
import it.unisa.uniclass.utenti.model.Studente;
import it.unisa.uniclass.utenti.model.Tipo;
import it.unisa.uniclass.utenti.service.AccademicoService;
import it.unisa.uniclass.utenti.controller.AttivaUtentiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AttivaUtentiServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AccademicoService accademicoService;

    @Mock
    private HttpSession session;

    private AttivaUtentiServlet servlet;

    @BeforeEach
    void setUp() throws ServletException {
        MockitoAnnotations.openMocks(this);
        servlet = new AttivaUtentiServlet(accademicoService);
    }

    @Test
    void TC1_AllValid() throws ServletException, IOException {
        System.out.println("\nTest 1: Tutto valido");
        boolean approvato;
        // Arrange
        when(request.getParameter("param")).thenReturn("add");
        when(request.getParameter("email")).thenReturn("s.davanzo5@studenti.unisa.it");
        when(request.getParameter("matricola")).thenReturn("0512118330");
        when(request.getParameter("tipo")).thenReturn("Studente");
        when(request.getContextPath()).thenReturn("/UniClass");

        //Studente reale
        Studente studente = new Studente();
        studente.setNome("Saverio");
        studente.setCognome("D'Avanzo");
        studente.setEmail("s.davanzo5@studenti.unisa.it");
        studente.setIscrizione(LocalDate.now());
        studente.setTipo(Tipo.Studente);
        studente.setMatricola("0512118330");

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail("giacomoporetti@unisa.it");
        docente.setMatricola("0512111101");
        docente.setTipo(Tipo.Docente);
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        //Cosa restituiscono le retrieve
        when(accademicoService.trovaEmailUniClass(request.getParameter("email"))).thenReturn(studente);
        when(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola"))).thenReturn(studente);

        //Ciò che è digitato
        System.out.println("Email: " + (String) request.getParameter("email"));
        System.out.println("Matricola: " + (String) request.getParameter("matricola"));
        System.out.println("Tipo: " + (String) request.getParameter("tipo"));

        if(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) != null) {
            System.out.println("Utente trovato con la matricola: " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getMatricola() + " " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail());
        }
        else {
            System.out.println("Utente trovato con la matricola: Nessuno");
        }

        if(accademicoService.trovaEmailUniClass(request.getParameter("email")) != null) {
            System.out.println("Utente trovato con l'email: " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getMatricola() + " " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail());
        }
        else {
            System.out.println("Utente trovato con l'email: Nessuno");
        }

        if((accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail()).equals(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail()) && (accademicoService.trovaEmailUniClass(request.getParameter("email")).getTipo().equals(Tipo.Studente)) && (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getTipo().equals(Tipo.Studente))) {
            approvato = true;
        }
        else {
            approvato = false;
        }

        if(approvato) {
            System.out.println("Associazione: Corretta");
        }
        else {
            System.out.println("Associazione: Errata");
        }

        // Act
        servlet.doPostPublic(request, response);

        // Assert
        assertTrue(studente.isAttivato(), "Lo studente dovrebbe essere attivato!");

        verify(accademicoService).aggiungiAccademico(argThat(acc ->
                acc.getEmail().equals("s.davanzo5@studenti.unisa.it") &&
                        acc.getMatricola().equals("0512118330") &&
                        acc.getTipo() == Tipo.Studente &&
                        acc.isAttivato() // Controlla che attivato sia true
        ));

        verify(response).sendRedirect("/UniClass/PersonaleTA/AttivaUtenti.jsp");
        System.out.println("URL : /UniClass/PersonaleTA/AttivaUtenti.jsp");
    }

    @Test
    void TC2_associazioneErrata() throws ServletException, IOException {
        System.out.println("\nTest 2: L'associazione è errata");
        boolean approvato;
        // Arrange
        when(request.getParameter("param")).thenReturn("add");
        when(request.getParameter("email")).thenReturn("s.davanzo5@studenti.unisa.it");
        when(request.getParameter("matricola")).thenReturn("0512118330");
        when(request.getParameter("tipo")).thenReturn("Studente");
        when(request.getContextPath()).thenReturn("/UniClass");

        //Studente reale
        Studente studente = new Studente();
        studente.setNome("Saverio");
        studente.setCognome("D'Avanzo");
        studente.setEmail("s.davanzo5@studenti.unisa.it");
        studente.setIscrizione(LocalDate.now());
        studente.setTipo(Tipo.Studente);
        studente.setMatricola("0512118330");

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail("giacomoporetti@unisa.it");
        docente.setMatricola("0512111101");
        docente.setTipo(Tipo.Docente);
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        //Cosa restituiscono le retrieve
        when(accademicoService.trovaEmailUniClass(request.getParameter("email"))).thenReturn(studente);
        when(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola"))).thenReturn(docente);

        //Ciò che è digitato
        System.out.println("Email: " + (String) request.getParameter("email"));
        System.out.println("Matricola: " + (String) request.getParameter("matricola"));
        System.out.println("Tipo: " + (String) request.getParameter("tipo"));

        if(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) != null) {
            System.out.println("Utente trovato con la matricola: " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getMatricola() + " " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail());
        }
        else {
            System.out.println("Utente trovato con la matricola: Nessuno");
        }

        if(accademicoService.trovaEmailUniClass(request.getParameter("email")) != null) {
            System.out.println("Utente trovato con l'email: " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getMatricola() + " " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail());
        }
        else {
            System.out.println("Utente trovato con l'email: Nessuno");
        }

        if((accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail()).equals(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail()) && (accademicoService.trovaEmailUniClass(request.getParameter("email")).getTipo().equals(Tipo.Studente)) && (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getTipo().equals(Tipo.Studente))) {
            approvato = true;
        }
        else {
            approvato = false;
        }

        if(approvato) {
            System.out.println("Associazione: Corretta");
        }
        else {
            System.out.println("Associazione: Errata");
        }

        // Act
        servlet.doPostPublic(request, response);

        // Assert
        assertFalse(studente.isAttivato(), "Lo studente NON dovrebbe essere attivato!");

        // Verifica che NON venga chiamato `aggiungiAccademico` (perché l'attivazione non deve avvenire)
        verify(accademicoService, never()).aggiungiAccademico(any());

        verify(response).sendRedirect("/UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
        System.out.println("URL : /UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
    }

    @Test
    void TC3_mailNotPresentDB() throws ServletException, IOException {
        System.out.println("\nTest 3: Mail non presente nel DB");
        boolean approvato;
        // Arrange
        when(request.getParameter("param")).thenReturn("add");
        when(request.getParameter("email")).thenReturn("s.davanzo@studenti.unisa.it");
        when(request.getParameter("matricola")).thenReturn("0512118330");
        when(request.getParameter("tipo")).thenReturn("Studente");
        when(request.getContextPath()).thenReturn("/UniClass");

        //Studente reale
        Studente studente = new Studente();
        studente.setNome("Saverio");
        studente.setCognome("D'Avanzo");
        studente.setEmail("s.davanzo5@studenti.unisa.it");
        studente.setIscrizione(LocalDate.now());
        studente.setTipo(Tipo.Studente);
        studente.setMatricola("0512118330");

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail("giacomoporetti@unisa.it");
        docente.setMatricola("0512111101");
        docente.setTipo(Tipo.Docente);
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        //Cosa restituiscono le retrieve
        when(accademicoService.trovaEmailUniClass(request.getParameter("email"))).thenReturn(null);
        when(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola"))).thenReturn(docente);

        //Ciò che è digitato
        System.out.println("Email: " + (String) request.getParameter("email"));
        System.out.println("Matricola: " + (String) request.getParameter("matricola"));
        System.out.println("Tipo: " + (String) request.getParameter("tipo"));

        if(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) != null) {
            System.out.println("Utente trovato con la matricola: " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getMatricola() + " " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail());
        }
        else {
            System.out.println("Utente trovato con la matricola: Nessuno");
        }

        if(accademicoService.trovaEmailUniClass(request.getParameter("email")) != null) {
            System.out.println("Utente trovato con l'email: " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getMatricola() + " " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail());
        }
        else {
            System.out.println("Utente trovato con l'email: Nessuno");
        }

        if((accademicoService.trovaEmailUniClass(request.getParameter("email")) == null) || (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) == null)) {
            approvato = false;
        }
        else if((accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail()).equals(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail()) && (accademicoService.trovaEmailUniClass(request.getParameter("email")).getTipo().equals(Tipo.Studente)) && (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getTipo().equals(Tipo.Studente))) {
            approvato = true;
        }
        else {
            approvato = false;
        }

        if(approvato) {
            System.out.println("Associazione: Corretta");
        }
        else {
            System.out.println("Associazione: Errata");
        }

        // Act
        servlet.doPostPublic(request, response);

        // Assert
        assertFalse(studente.isAttivato(), "Lo studente NON dovrebbe essere attivato!");

        // Verifica che NON venga chiamato `aggiungiAccademico` (perché l'attivazione non deve avvenire)
        verify(accademicoService, never()).aggiungiAccademico(any());

        verify(response).sendRedirect("/UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
        System.out.println("URL : /UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
    }

    @Test
    void TC4_emailNotFormatted() throws ServletException, IOException {
        System.out.println("\nTest 4: Mail non formattata correttamente, quindi non presente nel DB");
        boolean approvato;
        // Arrange
        when(request.getParameter("param")).thenReturn("add");
        when(request.getParameter("email")).thenReturn("s.davanzostudenti.unisa.it");
        when(request.getParameter("matricola")).thenReturn("0512118330");
        when(request.getParameter("tipo")).thenReturn("Studente");
        when(request.getContextPath()).thenReturn("/UniClass");

        //Studente reale
        Studente studente = new Studente();
        studente.setNome("Saverio");
        studente.setCognome("D'Avanzo");
        studente.setEmail("s.davanzo5@studenti.unisa.it");
        studente.setIscrizione(LocalDate.now());
        studente.setTipo(Tipo.Studente);
        studente.setMatricola("0512118330");

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail("giacomoporetti@unisa.it");
        docente.setMatricola("0512111101");
        docente.setTipo(Tipo.Docente);
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        //Cosa restituiscono le retrieve
        when(accademicoService.trovaEmailUniClass(request.getParameter("email"))).thenReturn(null);
        when(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola"))).thenReturn(docente);

        //Ciò che è digitato
        System.out.println("Email: " + (String) request.getParameter("email"));
        System.out.println("Matricola: " + (String) request.getParameter("matricola"));
        System.out.println("Tipo: " + (String) request.getParameter("tipo"));

        if(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) != null) {
            System.out.println("Utente trovato con la matricola: " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getMatricola() + " " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail());
        }
        else {
            System.out.println("Utente trovato con la matricola: Nessuno");
        }

        if(accademicoService.trovaEmailUniClass(request.getParameter("email")) != null) {
            System.out.println("Utente trovato con l'email: " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getMatricola() + " " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail());
        }
        else {
            System.out.println("Utente trovato con l'email: Nessuno");
        }

        if((accademicoService.trovaEmailUniClass(request.getParameter("email")) == null) || (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) == null)) {
            approvato = false;
        }
        else if((accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail()).equals(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail()) && (accademicoService.trovaEmailUniClass(request.getParameter("email")).getTipo().equals(Tipo.Studente)) && (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getTipo().equals(Tipo.Studente))) {
            approvato = true;
        }
        else {
            approvato = false;
        }

        if(approvato) {
            System.out.println("Associazione: Corretta");
        }
        else {
            System.out.println("Associazione: Errata");
        }

        // Act
        servlet.doPostPublic(request, response);

        // Assert
        assertFalse(studente.isAttivato(), "Lo studente NON dovrebbe essere attivato!");

        // Verifica che NON venga chiamato `aggiungiAccademico` (perché l'attivazione non deve avvenire)
        verify(accademicoService, never()).aggiungiAccademico(any());

        verify(response).sendRedirect("/UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
        System.out.println("URL : /UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
    }

    @Test
    void TC5_notPresentBothInfo() throws ServletException, IOException {
        System.out.println("\nTest 5: Mail e Matricola formattati correttamente ma non presenti");
        boolean approvato;

        // Arrange
        when(request.getParameter("param")).thenReturn("add");
        when(request.getParameter("email")).thenReturn("s.davanzo@studenti.unisa.it");
        when(request.getParameter("matricola")).thenReturn("0512117895");
        when(request.getParameter("tipo")).thenReturn("Studente");
        when(request.getContextPath()).thenReturn("/UniClass");

        //Studente reale
        Studente studente = new Studente();
        studente.setNome("Saverio");
        studente.setCognome("D'Avanzo");
        studente.setEmail("s.davanzo5@studenti.unisa.it");
        studente.setIscrizione(LocalDate.now());
        studente.setTipo(Tipo.Studente);
        studente.setMatricola("0512118330");

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail("giacomoporetti@unisa.it");
        docente.setMatricola("0512111101");
        docente.setTipo(Tipo.Docente);
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        //Cosa restituiscono le retrieve
        when(accademicoService.trovaEmailUniClass(request.getParameter("email"))).thenReturn(null);
        when(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola"))).thenReturn(null);

        //Ciò che è digitato
        System.out.println("Email: " + (String) request.getParameter("email"));
        System.out.println("Matricola: " + (String) request.getParameter("matricola"));
        System.out.println("Tipo: " + (String) request.getParameter("tipo"));

        if(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) != null) {
            System.out.println("Utente trovato con la matricola: " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getMatricola() + " " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail());
        }
        else {
            System.out.println("Utente trovato con la matricola: Nessuno");
        }

        if(accademicoService.trovaEmailUniClass(request.getParameter("email")) != null) {
            System.out.println("Utente trovato con l'email: " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getMatricola() + " " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail());
        }
        else {
            System.out.println("Utente trovato con l'email: Nessuno");
        }

        if((accademicoService.trovaEmailUniClass(request.getParameter("email")) == null) || (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) == null)) {
            approvato = false;
        }
        else if((accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail()).equals(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail()) && (accademicoService.trovaEmailUniClass(request.getParameter("email")).getTipo().equals(Tipo.Studente)) && (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getTipo().equals(Tipo.Studente))) {
            approvato = true;
        }
        else {
            approvato = false;
        }

        if(approvato) {
            System.out.println("Associazione: Corretta");
        }
        else {
            System.out.println("Associazione: Errata");
        }

        // Act
        servlet.doPostPublic(request, response);

        // Assert
        assertFalse(studente.isAttivato(), "Lo studente NON dovrebbe essere attivato!");

        // Verifica che NON venga chiamato `aggiungiAccademico` (perché l'attivazione non deve avvenire)
        verify(accademicoService, never()).aggiungiAccademico(any());

        verify(response).sendRedirect("/UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
        System.out.println("URL : /UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
    }

    @Test
    void TC6_notPresentBothAndNotFormattedEmail() throws ServletException, IOException {
        System.out.println("\nTest 6: Matricola formattata correttamente, email no ed entrambi non presenti");

        boolean approvato;

        // Arrange
        when(request.getParameter("param")).thenReturn("add");
        when(request.getParameter("email")).thenReturn("s.davanzostudenti.unisa.it");
        when(request.getParameter("matricola")).thenReturn("0512117895");
        when(request.getParameter("tipo")).thenReturn("Studente");
        when(request.getContextPath()).thenReturn("/UniClass");

        //Studente reale
        Studente studente = new Studente();
        studente.setNome("Saverio");
        studente.setCognome("D'Avanzo");
        studente.setEmail("s.davanzo5@studenti.unisa.it");
        studente.setIscrizione(LocalDate.now());
        studente.setTipo(Tipo.Studente);
        studente.setMatricola("0512118330");

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail("giacomoporetti@unisa.it");
        docente.setMatricola("0512111101");
        docente.setTipo(Tipo.Docente);
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        //Cosa restituiscono le retrieve
        when(accademicoService.trovaEmailUniClass(request.getParameter("email"))).thenReturn(null);
        when(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola"))).thenReturn(null);

        //Ciò che è digitato
        System.out.println("Email: " + (String) request.getParameter("email"));
        System.out.println("Matricola: " + (String) request.getParameter("matricola"));
        System.out.println("Tipo: " + (String) request.getParameter("tipo"));

        if(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) != null) {
            System.out.println("Utente trovato con la matricola: " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getMatricola() + " " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail());
        }
        else {
            System.out.println("Utente trovato con la matricola: Nessuno");
        }

        if(accademicoService.trovaEmailUniClass(request.getParameter("email")) != null) {
            System.out.println("Utente trovato con l'email: " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getMatricola() + " " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail());
        }
        else {
            System.out.println("Utente trovato con l'email: Nessuno");
        }

        if((accademicoService.trovaEmailUniClass(request.getParameter("email")) == null) || (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) == null)) {
            approvato = false;
        }
        else if((accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail()).equals(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail()) && (accademicoService.trovaEmailUniClass(request.getParameter("email")).getTipo().equals(Tipo.Studente)) && (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getTipo().equals(Tipo.Studente))) {
            approvato = true;
        }
        else {
            approvato = false;
        }

        if(approvato) {
            System.out.println("Associazione: Corretta");
        }
        else {
            System.out.println("Associazione: Errata");
        }

        // Act
        servlet.doPostPublic(request, response);

        // Assert
        assertFalse(studente.isAttivato(), "Lo studente NON dovrebbe essere attivato!");

        // Verifica che NON venga chiamato `aggiungiAccademico` (perché l'attivazione non deve avvenire)
        verify(accademicoService, never()).aggiungiAccademico(any());

        verify(response).sendRedirect("/UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
        System.out.println("URL : /UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
    }

    @Test
    void TC7_notPresentBothNotFormattedMatricola() throws ServletException, IOException {
        System.out.println("\nTest 7: Matricola non formattata correttamente, email si ed entrambi non presenti");

        boolean approvato;

        // Arrange
        when(request.getParameter("param")).thenReturn("add");
        when(request.getParameter("email")).thenReturn("s.davanzo@studenti.unisa.it");
        when(request.getParameter("matricola")).thenReturn("aaaaa?");
        when(request.getParameter("tipo")).thenReturn("Studente");
        when(request.getContextPath()).thenReturn("/UniClass");

        //Studente reale
        Studente studente = new Studente();
        studente.setNome("Saverio");
        studente.setCognome("D'Avanzo");
        studente.setEmail("s.davanzo5@studenti.unisa.it");
        studente.setIscrizione(LocalDate.now());
        studente.setTipo(Tipo.Studente);
        studente.setMatricola("0512118330");

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail("giacomoporetti@unisa.it");
        docente.setMatricola("0512111101");
        docente.setTipo(Tipo.Docente);
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        //Cosa restituiscono le retrieve
        when(accademicoService.trovaEmailUniClass(request.getParameter("email"))).thenReturn(null);
        when(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola"))).thenReturn(null);

        //Ciò che è digitato
        System.out.println("Email: " + (String) request.getParameter("email"));
        System.out.println("Matricola: " + (String) request.getParameter("matricola"));
        System.out.println("Tipo: " + (String) request.getParameter("tipo"));

        if(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) != null) {
            System.out.println("Utente trovato con la matricola: " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getMatricola() + " " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail());
        }
        else {
            System.out.println("Utente trovato con la matricola: Nessuno");
        }

        if(accademicoService.trovaEmailUniClass(request.getParameter("email")) != null) {
            System.out.println("Utente trovato con l'email: " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getMatricola() + " " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail());
        }
        else {
            System.out.println("Utente trovato con l'email: Nessuno");
        }

        if((accademicoService.trovaEmailUniClass(request.getParameter("email")) == null) || (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) == null)) {
            approvato = false;
        }
        else if((accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail()).equals(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail()) && (accademicoService.trovaEmailUniClass(request.getParameter("email")).getTipo().equals(Tipo.Studente)) && (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getTipo().equals(Tipo.Studente))) {
            approvato = true;
        }
        else {
            approvato = false;
        }

        if(approvato) {
            System.out.println("Associazione: Corretta");
        }
        else {
            System.out.println("Associazione: Errata");
        }

        // Act
        servlet.doPostPublic(request, response);

        // Assert
        assertFalse(studente.isAttivato(), "Lo studente NON dovrebbe essere attivato!");

        // Verifica che NON venga chiamato `aggiungiAccademico` (perché l'attivazione non deve avvenire)
        verify(accademicoService, never()).aggiungiAccademico(any());

        verify(response).sendRedirect("/UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
        System.out.println("URL : /UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
    }

    @Test
    void TC8_nothingGood() throws ServletException, IOException {
        System.out.println("\nTest 7: Matricola ed email non formattati correttamente, non presenti di conseguenza");

        boolean approvato;

        // Arrange
        when(request.getParameter("param")).thenReturn("add");
        when(request.getParameter("email")).thenReturn("s.davanzostudenti.unisa.it");
        when(request.getParameter("matricola")).thenReturn("aaaaa");
        when(request.getParameter("tipo")).thenReturn("Studente");
        when(request.getContextPath()).thenReturn("/UniClass");

        //Studente reale
        Studente studente = new Studente();
        studente.setNome("Saverio");
        studente.setCognome("D'Avanzo");
        studente.setEmail("s.davanzo5@studenti.unisa.it");
        studente.setIscrizione(LocalDate.now());
        studente.setTipo(Tipo.Studente);
        studente.setMatricola("0512118330");

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail("giacomoporetti@unisa.it");
        docente.setMatricola("0512111101");
        docente.setTipo(Tipo.Docente);
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        //Cosa restituiscono le retrieve
        when(accademicoService.trovaEmailUniClass(request.getParameter("email"))).thenReturn(null);
        when(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola"))).thenReturn(null);

        //Ciò che è digitato
        System.out.println("Email: " + (String) request.getParameter("email"));
        System.out.println("Matricola: " + (String) request.getParameter("matricola"));
        System.out.println("Tipo: " + (String) request.getParameter("tipo"));

        if(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) != null) {
            System.out.println("Utente trovato con la matricola: " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getMatricola() + " " + accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail());
        }
        else {
            System.out.println("Utente trovato con la matricola: Nessuno");
        }

        if(accademicoService.trovaEmailUniClass(request.getParameter("email")) != null) {
            System.out.println("Utente trovato con l'email: " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getMatricola() + " " + accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail());
        }
        else {
            System.out.println("Utente trovato con l'email: Nessuno");
        }

        if((accademicoService.trovaEmailUniClass(request.getParameter("email")) == null) || (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")) == null)) {
            approvato = false;
        }
        else if((accademicoService.trovaEmailUniClass(request.getParameter("email")).getEmail()).equals(accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getEmail()) && (accademicoService.trovaEmailUniClass(request.getParameter("email")).getTipo().equals(Tipo.Studente)) && (accademicoService.trovaAccademicoUniClass(request.getParameter("matricola")).getTipo().equals(Tipo.Studente))) {
            approvato = true;
        }
        else {
            approvato = false;
        }

        if(approvato) {
            System.out.println("Associazione: Corretta");
        }
        else {
            System.out.println("Associazione: Errata");
        }

        // Act
        servlet.doPostPublic(request, response);

        // Assert
        assertFalse(studente.isAttivato(), "Lo studente NON dovrebbe essere attivato!");

        // Verifica che NON venga chiamato `aggiungiAccademico` (perché l'attivazione non deve avvenire)
        verify(accademicoService, never()).aggiungiAccademico(any());

        verify(response).sendRedirect("/UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
        System.out.println("URL : /UniClass/PersonaleTA/AttivaUtenti.jsp?action=error");
    }
}