package it.unisa.uniclass.common.servlet;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.service.CorsoLaureaService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "indexServlet", value = "/")
public class IndexServlet extends HttpServlet {

    @EJB
    private CorsoLaureaService corsoLaureaService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CorsoLaurea> corsi = corsoLaureaService.trovaTutti();
        request.setAttribute("corsi", corsi);
    }
}
