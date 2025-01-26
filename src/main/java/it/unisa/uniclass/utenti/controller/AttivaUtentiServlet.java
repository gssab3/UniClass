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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String param = req.getParameter("param");
        AccademicoService accademicoService = new AccademicoService();

        if(param.equals("add")){
            String email = (String) req.getParameter("email");
            String matricola = (String) req.getParameter("matricola");
            String tiporeq = (String) req.getParameter("tipo");

            Accademico accademicoEmail = accademicoService.trovaEmailUniClass(email);
            Accademico accademicoMatricola = accademicoService.trovaAccademicoUniClass(matricola);
            Accademico accademico = null; //L'accademico reale. Se è null, i parametri inseriti sono sbagliati o inesistenti.
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

            if(accademicoEmail.getEmail().equals(accademicoMatricola.getEmail()) && accademicoEmail.getMatricola().equals(accademicoMatricola.getMatricola())){
                System.out.println("\n\n\nCASO A\n\n\n");
                if(accademicoEmail.getTipo().equals(tipo)) {
                    System.out.println("\n\n\nCASO B\n\n\n");
                    accademico = accademicoEmail;
                    String password = PasswordGenerator.generatePassword(8);
                    System.out.println("\n\n" + password + "\n\n");


                    accademico.setAttivato(true);
                    accademico.setPassword(CredentialSecurity.hashPassword(password));

                    //Dopo averlo attivato e settato password hashata, facciamo il merge con la funzione del DAO
                    accademicoService.aggiungiAccademico(accademico);
                    //A questo punto, si ritorna in AttivaUtenti.jsp
                    resp.sendRedirect(req.getContextPath() + "/PersonaleTA/AttivaUtenti.jsp");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/PersonaleTA/AttivaUtenti.jsp?action=error");
                }
            }else{
                resp.sendRedirect(req.getContextPath() + "/PersonaleTA/AttivaUtenti.jsp?action=error");
            }
        } else if(param.equals("remove")){

            String email = (String) req.getParameter("email-remove");

            Accademico accademico = accademicoService.trovaEmailUniClass(email);
            accademicoService.cambiaAttivazione(accademico, false);
            resp.sendRedirect(req.getContextPath() + "/PersonaleTA/AttivaUtenti.jsp");
        }


        /*

       */
    }
}
