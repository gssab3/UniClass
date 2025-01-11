// Importa i moduli necessari
const express = require('express');
const { CharacterAI } = require("node_characterai");
const dotenv = require('dotenv');
const cors = require('cors');

// Carica le variabili d'ambiente
dotenv.config();

// Inizializza Express
const app = express();
const PORT = 3000;

// Abilita CORS per tutte le richieste
app.use(cors());

// Middleware per gestire il body della richiesta come JSON
app.use(express.json());

// Configura Character.AI
const characterAI = new CharacterAI();
const ACCESS_TOKEN = "0df96170b58381d7d1b4197705d040369a6a4d22"; // Usa il token da una variabile d'ambiente
const CHARACTER_ID = "7XlLZV-XjfWXaMfNU7Ck57yWFGVCD_2zDojN8hf6vmQ"; // Usa l'ID del personaggio da una variabile d'ambiente

let character; // Variabile per il personaggio

// Funzione per autenticare e ottenere il personaggio
async function initCharacterAI() {
    try {
        await characterAI.authenticate(ACCESS_TOKEN);
        console.log("Logged into Character.AI");

        // Prendi il personaggio specificato
        character = await characterAI.fetchCharacter(CHARACTER_ID);
        console.log(`Caricato personaggio: ${character.name}`);

    } catch (error) {
        console.error('Errore durante l\'autenticazione o il recupero del personaggio:', error);
        process.exit(1); // Esci in caso di errore
    }
}

// Endpoint per il chatbot
app.post('/chatbot', async (req, res) => {
    const userMessage = req.body.message; // Recupera il messaggio dell'utente
    if (!userMessage) {
        return res.status(400).json({ response: "Messaggio non fornito." });
    }

    console.log(`[Messaggio ricevuto] ${userMessage}`);

    try {
        // Avvia una conversazione con il personaggio
        const dm = await character.DM();
        const response = await dm.sendMessage(userMessage); // Invia il messaggio e ottieni la risposta

        // Rispondi al client con la risposta del bot
        res.json({ response: response.content });

    } catch (error) {
        console.error('Errore nella comunicazione con il bot:', error);
        res.status(500).json({ response: "Errore nella comunicazione con il bot." });
    }
});

// Avvio del server
app.listen(PORT, async () => {
    console.log(`Server Node.js in ascolto sulla porta ${PORT}`);
    await initCharacterAI(); // Inizializza Character.AI
});
