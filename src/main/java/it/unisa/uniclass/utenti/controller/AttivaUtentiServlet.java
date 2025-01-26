package it.unisa.uniclass.utenti.controller;

import it.unisa.uniclass.common.security.CredentialSecurity;
import it.unisa.uniclass.common.security.PasswordGenerator;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Tipo;
import it.unisa.uniclass.utenti.service.AccademicoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

        if(param.equals("add")){

            String email = (String) req.getAttribute("email");
            String matricola = (String) req.getAttribute("matricola");
            String tiporeq = (String) req.getAttribute("tipo");
            Path relativepath = Paths.get("src","main", "resources", "password.txt");
            File file = relativepath.toFile();
            AccademicoService accademicoService = new AccademicoService();
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
            if(accademicoEmail.equals(accademicoMatricola)){
                if(accademicoEmail.getTipo().equals(tipo)) {
                    accademico = accademicoEmail;
                    String password = PasswordGenerator.generatePassword(8);
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                        writer.write(accademico.getMatricola() + ":" + password);
                        writer.newLine();
                    } catch (IOException e) {
                        System.out.println(e.toString());
                    }
                    accademico.setAttivato(true);
                    accademico.setPassword(CredentialSecurity.hashPassword(password));

                    //Dopo averlo attivato e settato password hashata, facciamo il merge con la funzione del DAO
                    accademicoService.aggiungiAccademico(accademico);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/AttivaUtenti.jsp?action=error");
                }
            }else{
                resp.sendRedirect(req.getContextPath() + "/AttivaUtenti.jsp?action=error");
            }
            resp.sendRedirect(req.getContextPath() + "AttivaUtenti.jsp");

        } else if(param.equals("remove")){

            String email = (String) req.getAttribute("email-remove");

            AccademicoService accademicoService = new AccademicoService();
            Accademico accademico = accademicoService.trovaAccademicoUniClass(email);
            accademico.setAttivato(false);
            resp.sendRedirect(req.getContextPath() + "AttivaUtenti.jsp");
        }


        /*

       */
    }
}
