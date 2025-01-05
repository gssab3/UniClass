package it.unisa.uniclass.conversazioni.controller;

import it.unisa.uniclass.common.ai.CharacterAIClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "chatBotServlet", value = "/chatbot")
public class ChatBotServlet extends HttpServlet {

    private CharacterAIClient characterAIClient;

    @Override
    public void init() throws ServletException{
        super.init();
        characterAIClient = new CharacterAIClient();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Leggi il messaggio dell'utente
        String userMessage = request.getParameter("message");

        // Ottieni la risposta dal bot
        String botResponse = characterAIClient.getBotResponse(userMessage);

        // Costruisci la risposta JSON
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("userMessage", userMessage);
        jsonResponse.put("botResponse", botResponse);

        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

}
