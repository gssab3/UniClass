package it.unisa.uniclass.common.ai;


import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class CharacterAIClient {

    private static final String ACCESS_TOKEN = "0df96170b58381d7d1b4197705d040369a6a4d22";
    private static final String CHARACTER_ID = "7XlLZV-XjfWXaMfNU7Ck57yWFGVCD_2zDojN8hf6vmQ";

    public String getBotResponse(String userMessage) {
        try {
            URL url = new URL("https://beta.character.ai/chat/"); // URL fittizio per Character.AI
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);
            connection.setDoOutput(true);

            // Corpo della richiesta
            JSONObject requestBody = new JSONObject();
            requestBody.put("character_id", CHARACTER_ID);
            requestBody.put("message", userMessage);

            // Scrivi il corpo della richiesta
            OutputStream os = connection.getOutputStream();
            os.write(requestBody.toString().getBytes());
            os.flush();
            os.close();

            // Leggi la risposta
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Estrai il messaggio di risposta dal JSON
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getString("response");
        } catch (Exception e) {
            e.printStackTrace();
            return "Errore durante la comunicazione con il bot.";
        }
    }
}
