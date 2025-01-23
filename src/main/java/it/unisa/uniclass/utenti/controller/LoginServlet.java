package it.unisa.uniclass.utenti.controller;

import it.unisa.uniclass.common.security.CredentialSecurity;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Utente;
import it.unisa.uniclass.utenti.service.AccademicoService;
import it.unisa.uniclass.utenti.service.PersonaleTAService;
import it.unisa.uniclass.utenti.service.UtenteService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "loginServlet", value = "/Login")
public class LoginServlet extends HttpServlet{



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            String email = request.getParameter("email");
            //String password = CredentialSecurity.hashPassword(request.getParameter("password"));
            String password = request.getParameter("password");

            AccademicoService accademicoService = new AccademicoService();
            PersonaleTAService personaleTAService = new PersonaleTAService();
            List<Accademico> attivati = accademicoService.trovaAttivati(true);
            Utente user1 = accademicoService.trovaEmailPassUniclass(email, password);
            Utente user2 = personaleTAService.trovaEmailPass(email,password);
            Utente user = null;

            if(user1 == null){
                if(user2 == null){
                    response.sendRedirect(request.getContextPath() + "/Login.jsp?action=error");
                } else {
                    user = user2;
                }
            }else if(attivati.contains(user1)){
                user = user1;
            }
            else {
                response.sendRedirect(request.getContextPath() + "/Login.jsp?action=error");
            }

            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", user);
                response.sendRedirect(request.getContextPath() + "/Home");
            } else {
                response.sendRedirect(request.getContextPath() + "/Login.jsp?action=error");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
