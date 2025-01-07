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
    public void init() throws ServletException {
        super.init();
        characterAIClient = new CharacterAIClient();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Imposta il tipo di risposta come JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Ottieni il messaggio dell'utente
        String userMessage = request.getParameter("message");

        // Controllo se il messaggio è valido
        if (userMessage == null || userMessage.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Messaggio non valido\"}");
            return;
        }

        try {
            // Log per il messaggio dell'utente
            System.out.println("User message: " + userMessage);

            // Ottieni la risposta dal bot
            String botResponse = characterAIClient.getBotResponse(userMessage);

            // Costruisci la risposta JSON
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("content", botResponse);  // 'content' matches what the JS client expects
            jsonResponse.put("userMessage", userMessage);  // Optionally include the user's message

            // Log per la risposta del bot
            System.out.println("Bot response: " + botResponse);

            // Invia la risposta al client
            PrintWriter out = response.getWriter();
            out.print(jsonResponse.toString());
            out.flush();
        } catch (Exception e) {
            // Gestisci errori in caso di problemi con il bot o il client
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            // Log error
            e.printStackTrace();

            // Risposta di errore in formato JSON
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Errore durante la comunicazione con il bot");
            response.getWriter().write(errorResponse.toString());
        }
    }
}
