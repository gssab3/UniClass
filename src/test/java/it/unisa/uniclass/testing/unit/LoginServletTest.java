/*
Test Case 1          (Key = 1.1.1.1.)
   Formato  :  EMAILCorretto
   Database :  EMAILPresente
   Formato  :  PASSWORDCorretto
   Database :  PASSWORDPresente


Test Case 2          (Key = 1.1.1.2.)
   Formato  :  EMAILCorretto
   Database :  EMAILPresente
   Formato  :  PASSWORDCorretto
   Database :  PASSWORDNon presente


Test Case 3          (Key = 1.1.2.2.)
   Formato  :  EMAILCorretto
   Database :  EMAILPresente
   Formato  :  PASSWORDNon corretto
   Database :  PASSWORDNon presente


Test Case 4          (Key = 1.2.1.2.)
   Formato  :  EMAILCorretto
   Database :  EMAILNon presente
   Formato  :  PASSWORDCorretto
   Database :  PASSWORDNon presente


Test Case 5          (Key = 1.2.2.2.)
   Formato  :  EMAILCorretto
   Database :  EMAILNon presente
   Formato  :  PASSWORDNon corretto
   Database :  PASSWORDNon presente


Test Case 6          (Key = 2.2.1.2.)
   Formato  :  EMAILNon corretto
   Database :  EMAILNon presente
   Formato  :  PASSWORDCorretto
   Database :  PASSWORDNon presente


Test Case 7          (Key = 2.2.2.2.)
   Formato  :  EMAILNon corretto
   Database :  EMAILNon presente
   Formato  :  PASSWORDNon corretto
   Database :  PASSWORDNon presente
 */


package it.unisa.uniclass.testing.unit;

import it.unisa.uniclass.common.security.CredentialSecurity;
import it.unisa.uniclass.utenti.controller.LoginServlet;
import it.unisa.uniclass.utenti.service.AccademicoService;
import it.unisa.uniclass.utenti.service.PersonaleTAService;
import it.unisa.uniclass.utenti.service.dao.AccademicoRemote;
import it.unisa.uniclass.utenti.model.Docente;
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
    void TC1_ValidLogin() throws ServletException, IOException {
        System.out.println("\nTest Case 1: Login con credenziali corrette");

        // Arrange
        String email = "giacomoporetti@unisa.it";
        String password = "2222WxY$";
        String hashedPassword = CredentialSecurity.hashPassword(password);

        Docente docente = new Docente();
        docente.setEmail(email);
        docente.setPassword(hashedPassword);
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        // Mock delle risposte della servlet
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(academicService.trovaEmailUniClass(email)).thenReturn(docente);
        when(academicService.trovaEmailPassUniclass(email, hashedPassword)).thenReturn(docente);
        when(personaleTAService.trovaEmailPass(email, hashedPassword)).thenReturn(null);
        when(request.getSession(true)).thenReturn(session);
        when(request.getContextPath()).thenReturn("/UniClass");

        // DEBUG: Stampa i valori prima della chiamata alla servlet
        System.out.println("Email inserita: " + request.getParameter("email"));
        System.out.println("Password inserita: " + request.getParameter("password"));
        if(academicService.trovaEmailUniClass(email) == null) {
            System.out.println("Email trovata nel mock DB: Non trovata");
        }
        else {
            System.out.println("Email trovata nel mock DB: " + academicService.trovaEmailUniClass(email).getEmail());
        }
        if(academicService.trovaEmailPassUniclass(email, hashedPassword) == null) {
            System.out.println("Utente trovato nel mock DB: Non presente");
        }
        else {
            System.out.println("Utente trovato nel mock DB: " + academicService.trovaEmailPassUniclass(email, hashedPassword).getEmail() + " " + docente.getPassword());
        }

        // Act
        loginServlet.doPost(request, response);


        // Assert
        verify(session).setAttribute("currentSessionUser", docente);
        verify(response).sendRedirect("/UniClass/Home");
        System.out.println("URL: /UniClass/Home");
    }

    @Test
    void TC2_NotPresentPassword() throws ServletException, IOException {
        System.out.println("\nTest Case 2: Login con password formattata bene ma non nel database");

        // Arrange
        String email = "giacomoporetti@unisa.it";
        String password = "2222WxY$p"; //password sbagliata
        String hashedPassword = CredentialSecurity.hashPassword(password);

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail(email);
        docente.setPassword(CredentialSecurity.hashPassword("2222WxY$")); //Qui la password è leggermente diversa e vera
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        // Mock delle risposte della servlet
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(academicService.trovaEmailUniClass(email)).thenReturn(docente);
        when(academicService.trovaEmailPassUniclass(email, hashedPassword)).thenReturn(docente);
        when(personaleTAService.trovaEmailPass(email, hashedPassword)).thenReturn(null);
        when(request.getSession(true)).thenReturn(session);
        when(request.getContextPath()).thenReturn("/UniClass");

        // DEBUG: Stampa i valori prima della chiamata alla servlet
        System.out.println("Email inserita: " + request.getParameter("email"));
        System.out.println("Password inserita: " + request.getParameter("password"));
        if(academicService.trovaEmailUniClass(email) == null) {
            System.out.println("Email trovata nel mock DB: Non trovata");
        }
        else {
            System.out.println("Email trovata nel mock DB: " + academicService.trovaEmailUniClass(email).getEmail());
        }
        if(academicService.trovaEmailPassUniclass(email, hashedPassword) == null) {
            System.out.println("Utente trovato nel mock DB: Non presente");
        }
        else {
            System.out.println("Utente trovato nel mock DB: " + academicService.trovaEmailPassUniclass(email, hashedPassword).getEmail() + " " + docente.getPassword());
        }

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("/UniClass/Login.jsp?action=error");
        System.out.println("URL: /UniClass/Login.jsp?action=error");
    }

    @Test
    void TC3_NotValidPassword() throws ServletException, IOException {
        System.out.println("\nTest Case 3: Login con password formattata male quindi non nel database");

        // Digitazione
        String email = "giacomoporetti@unisa.it";
        String password = "2222WxYS"; //password sbagliata
        String hashedPassword = CredentialSecurity.hashPassword(password);

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail(email);
        docente.setPassword(CredentialSecurity.hashPassword("2222WxY$")); //Qui la password è leggermente diversa e vera
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(academicService.trovaEmailUniClass(email)).thenReturn(docente);
        when(academicService.trovaEmailPassUniclass(email, hashedPassword)).thenReturn(null);
        when(personaleTAService.trovaEmailPass(email, hashedPassword)).thenReturn(null);
        when(request.getSession(true)).thenReturn(session);
        when(request.getContextPath()).thenReturn("/UniClass");


        System.out.println("Email inserita: " + request.getParameter("email"));
        System.out.println("Password inserita: " + request.getParameter("password"));
        if(academicService.trovaEmailUniClass(email) == null) {
            System.out.println("Email trovata nel mock DB: Non trovata");
        }
        else {
            System.out.println("Email trovata nel mock DB: " + academicService.trovaEmailUniClass(email).getEmail());
        }
        if(academicService.trovaEmailPassUniclass(email, hashedPassword) == null) {
            System.out.println("Utente trovato nel mock DB: Non presente");
        }
        else {
            System.out.println("Utente trovato nel mock DB: " + academicService.trovaEmailPassUniclass(email, hashedPassword).getEmail() + " " + docente.getPassword());
        }

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("/UniClass/Login.jsp?action=error");
        System.out.println("URL: /UniClass/Login.jsp?action=errorFormat");
    }

    @Test
    void TC4_NotPresentEmail() throws ServletException, IOException {
        System.out.println("\nTest Case 4: Login con email non presente nel database");

        // Digitazione
        String email = "giacomporetti@unisa.it";
        String password = "2222WxY$"; //password sbagliata
        String hashedPassword = CredentialSecurity.hashPassword(password);

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail("giacomoporetti@unisa.it");
        docente.setPassword(CredentialSecurity.hashPassword("2222WxY$")); //Qui la password è leggermente diversa e vera
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(academicService.trovaEmailUniClass(email)).thenReturn(null);
        when(academicService.trovaEmailPassUniclass(email, hashedPassword)).thenReturn(null);
        when(personaleTAService.trovaEmailPass(email, hashedPassword)).thenReturn(null);
        when(request.getSession(true)).thenReturn(session);
        when(request.getContextPath()).thenReturn("/UniClass");

        System.out.println("Email inserita: " + request.getParameter("email"));
        System.out.println("Password inserita: " + request.getParameter("password"));
        if(academicService.trovaEmailUniClass(email) == null) {
            System.out.println("Email trovata nel mock DB: Non trovata");
        }
        else {
            System.out.println("Email trovata nel mock DB: " + academicService.trovaEmailUniClass(email).getEmail());
        }
        if(academicService.trovaEmailPassUniclass(email, hashedPassword) == null) {
            System.out.println("Utente trovato nel mock DB: Non presente");
        }
        else {
            System.out.println("Utente trovato nel mock DB: " + academicService.trovaEmailPassUniclass(email, hashedPassword).getEmail() + " " + docente.getPassword());
        }

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("/UniClass/Login.jsp?action=error");
        System.out.println("URL: /UniClass/Login.jsp?action=errorFormat");
    }


    @Test
    void TC5_formatEmailOnlyValid() throws ServletException, IOException {
        System.out.println("\nTest Case 5: Login con solo il formato dell'email valido");

        // Digitazione
        String email = "giacomo@unisa.it";
        String password = "2212"; //password sbagliata
        String hashedPassword = CredentialSecurity.hashPassword(password);

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail("giacomoporetti@unisa.it");
        docente.setPassword(CredentialSecurity.hashPassword("2222WxY$")); //Qui la password è leggermente diversa e vera
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(academicService.trovaEmailUniClass(email)).thenReturn(null);
        when(academicService.trovaEmailPassUniclass(email, hashedPassword)).thenReturn(null);
        when(personaleTAService.trovaEmailPass(email, hashedPassword)).thenReturn(null);
        when(request.getSession(true)).thenReturn(session);
        when(request.getContextPath()).thenReturn("/UniClass");

        System.out.println("Email inserita: " + request.getParameter("email"));
        System.out.println("Password inserita: " + request.getParameter("password"));
        if(academicService.trovaEmailUniClass(email) == null) {
            System.out.println("Email trovata nel mock DB: Non trovata");
        }
        else {
            System.out.println("Email trovata nel mock DB: " + academicService.trovaEmailUniClass(email).getEmail());
        }
        if(academicService.trovaEmailPassUniclass(email, hashedPassword) == null) {
            System.out.println("Utente trovato nel mock DB: Non presente");
        }
        else {
            System.out.println("Utente trovato nel mock DB: " + academicService.trovaEmailPassUniclass(email, hashedPassword).getEmail() + " " + docente.getPassword());
        }

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("/UniClass/Login.jsp?action=error");
        System.out.println("URL: /UniClass/Login.jsp?action=errorFormat");
    }

    @Test
    void TC6_formatPasswordOnlyValid() throws ServletException, IOException {
        System.out.println("\nTest Case 6: Login con solo il formato della password valido");

        //Digitazione
        String email = "giacunsa.it";
        String password = "2212XyA$"; //password sbagliata
        String hashedPassword = CredentialSecurity.hashPassword(password);

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail("giacomoporetti@unisa.it");
        docente.setPassword(CredentialSecurity.hashPassword("2222WxY$")); //Qui la password è leggermente diversa e vera
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(academicService.trovaEmailUniClass(email)).thenReturn(null);
        when(academicService.trovaEmailPassUniclass(email, hashedPassword)).thenReturn(null);
        when(personaleTAService.trovaEmailPass(email, hashedPassword)).thenReturn(null);
        when(request.getSession(true)).thenReturn(session);
        when(request.getContextPath()).thenReturn("/UniClass");

        System.out.println("Email inserita: " + request.getParameter("email"));
        System.out.println("Password inserita: " + request.getParameter("password"));
        if(academicService.trovaEmailUniClass(email) == null) {
            System.out.println("Email trovata nel mock DB: Non trovata");
        }
        else {
            System.out.println("Email trovata nel mock DB: " + academicService.trovaEmailUniClass(email).getEmail());
        }
        if(academicService.trovaEmailPassUniclass(email, hashedPassword) == null) {
            System.out.println("Utente trovato nel mock DB: Non presente");
        }
        else {
            System.out.println("Utente trovato nel mock DB: " + academicService.trovaEmailPassUniclass(email, hashedPassword).getEmail() + " " + docente.getPassword());
        }

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("/UniClass/Login.jsp?action=error");
        System.out.println("URL: /UniClass/Login.jsp?action=errorFormat");
    }

    @Test
    void TC7_NothingValid() throws ServletException, IOException {
        System.out.println("\nTest Case 7: Login con niente di valido");

        //Digitazione
        String email = "giacunsa.it";
        String password = "221"; //password sbagliata
        String hashedPassword = CredentialSecurity.hashPassword(password);

        //Docente reale
        Docente docente = new Docente();
        docente.setEmail("giacomoporetti@unisa.it");
        docente.setPassword(CredentialSecurity.hashPassword("2222WxY$")); //Qui la password è leggermente diversa e vera
        docente.setAttivato(true); // Simuliamo che il docente sia attivo

        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(academicService.trovaEmailUniClass(email)).thenReturn(null);
        when(academicService.trovaEmailPassUniclass(email, hashedPassword)).thenReturn(null);
        when(personaleTAService.trovaEmailPass(email, hashedPassword)).thenReturn(null);
        when(request.getSession(true)).thenReturn(session);
        when(request.getContextPath()).thenReturn("/UniClass");

        System.out.println("Email inserita: " + request.getParameter("email"));
        System.out.println("Password inserita: " + request.getParameter("password"));
        if(academicService.trovaEmailUniClass(email) == null) {
            System.out.println("Email trovata nel mock DB: Non trovata");
        }
        else {
            System.out.println("Email trovata nel mock DB: " + academicService.trovaEmailUniClass(email).getEmail());
        }

        if(academicService.trovaEmailPassUniclass(email, hashedPassword) == null) {
            System.out.println("Utente trovato nel mock DB: Non presente");
        }
        else {
            System.out.println("Utente trovato nel mock DB: " + academicService.trovaEmailPassUniclass(email, hashedPassword).getEmail() + " " + docente.getPassword());
        }

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("/UniClass/Login.jsp?action=error");
        System.out.println("URL: /UniClass/Login.jsp?action=errorFormat");
    }


}