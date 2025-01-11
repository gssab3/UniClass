async function sendMessage() {
    const userMessage = document.getElementById("userMessage").value;
    if (!userMessage) {
        alert("Inserisci un messaggio!");
        return;
    }

    const messagesDiv = document.getElementById("messages");
    const userDiv = document.createElement("div");
    userDiv.classList.add("message", "user");
    userDiv.textContent = `Tu: ${userMessage}`;
    messagesDiv.appendChild(userDiv);

    try {
        // Invia la richiesta al server Node.js
        const response = await fetch('http://localhost:3000/chatbot', {  // Porta corretta
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ message: userMessage })  // Messaggio in formato JSON
        });

        if (!response.ok) {
            throw new Error(`Errore HTTP: ${response.status}`);
        }

        const data = await response.json();
        console.log("Risposta JSON:", data);

        // Mostra la risposta del bot
        const botDiv = document.createElement("div");
        botDiv.classList.add("message", "bot");
        botDiv.textContent = `Bot: ${data.response}`;  // Modificato per usare il campo giusto nella risposta
        messagesDiv.appendChild(botDiv);

        messagesDiv.scrollTop = messagesDiv.scrollHeight;
    } catch (error) {
        console.error("Errore:", error.message);
        const errorDiv = document.createElement("div");
        errorDiv.classList.add("message", "error");
        errorDiv.textContent = `Errore: ${error.message}`;
        messagesDiv.appendChild(errorDiv);
    }

    // Resetta il campo input
    document.getElementById("userMessage").value = "";
}
