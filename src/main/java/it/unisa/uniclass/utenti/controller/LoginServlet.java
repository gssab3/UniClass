package it.unisa.uniclass.utenti.controller;

import it.unisa.uniclass.common.security.CredentialSecurity;
import it.unisa.uniclass.utenti.model.Utente;
import it.unisa.uniclass.utenti.service.AccademicoDAO;
import it.unisa.uniclass.utenti.service.PersonaleTADAO;
import it.unisa.uniclass.utenti.service.UtenteService;
import jakarta.ejb.EJB;
import jakarta.persistence.Entity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", value = "/Login")
public class LoginServlet extends HttpServlet{

    @EJB
    private UtenteService utenteService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            String email = request.getParameter("email");
            String password = CredentialSecurity.hashPassword(request.getParameter("password"));

            Utente user = utenteService.retrieveByUserAndPassword(email,password);

            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", user);

                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/Login.jsp?action=error");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
