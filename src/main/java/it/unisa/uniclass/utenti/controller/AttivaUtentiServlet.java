package it.unisa.uniclass.utenti.controller;

import it.unisa.uniclass.common.security.CredentialSecurity;
import it.unisa.uniclass.common.security.PasswordGenerator;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Tipo;
import it.unisa.uniclass.utenti.service.AccademicoService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(name = "AttivaUtentiServlet", value = "/AttivaUtentiServlet")
public class AttivaUtentiServlet extends HttpServlet {

    private AccademicoService accademicoService;

    // This method is added for testing purposes
    public void setAccademicoService(AccademicoService accademicoService) {
        this.accademicoService = accademicoService;
    }

    public AttivaUtentiServlet() {
        accademicoService = new AccademicoService();
    }

    public AttivaUtentiServlet(AccademicoService acc) {
        accademicoService = acc;
    }

    // This method is added for testing purposes
    public void doPostPublic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("param");

        if(param.equals("add")){
            String email = (String) req.getParameter("email");
            String matricola = (String) req.getParameter("matricola");
            String tiporeq = (String) req.getParameter("tipo");

            Accademico accademicoEmail = accademicoService.trovaEmailUniClass(email);
            Accademico accademicoMatricola = accademicoService.trovaAccademicoUniClass(matricola);
            Accademico accademico = null;
            Tipo tipo = null;
            if(tiporeq.equals("Studente")) {
                tipo = Tipo.Studente;
            }
            else if(tiporeq.equals("Docente")) {
                tipo = Tipo.Docente;
            }
            else if(tiporeq.equals("Coordinatore")) {
                tipo = Tipo.Coordinatore;
            }

            if(accademicoEmail != null && accademicoMatricola != null &&
                    accademicoEmail.getEmail().equals(accademicoMatricola.getEmail()) &&
                    accademicoEmail.getMatricola().equals(accademicoMatricola.getMatricola())){
                if(accademicoEmail.getTipo().equals(tipo)) {
                    accademico = accademicoEmail;
                    String password = PasswordGenerator.generatePassword(8);

                    accademico.setAttivato(true);
                    accademico.setPassword(CredentialSecurity.hashPassword(password));

                    accademicoService.aggiungiAccademico(accademico);
                    resp.sendRedirect(req.getContextPath() + "/PersonaleTA/AttivaUtenti.jsp");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/PersonaleTA/AttivaUtenti.jsp?action=error");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/PersonaleTA/AttivaUtenti.jsp?action=error");
            }
        } else if(param.equals("remove")){
            String email = (String) req.getParameter("email-remove");

            Accademico accademico = accademicoService.trovaEmailUniClass(email);
            if (accademico != null) {
                accademicoService.cambiaAttivazione(accademico, false);
            }
            resp.sendRedirect(req.getContextPath() + "/PersonaleTA/AttivaUtenti.jsp");
        }
    }
}
