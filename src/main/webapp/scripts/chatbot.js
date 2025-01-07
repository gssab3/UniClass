async function sendMessage() {
    const userMessage = document.getElementById("userMessage").value;
    if (!userMessage) {
        alert("Inserisci un messaggio!");
        return;
    }

    // Mostra il messaggio dell'utente nella chat
    const messagesDiv = document.getElementById("messages");
    const userDiv = document.createElement("div");
    userDiv.classList.add("message", "user");
    userDiv.textContent = `Tu: ${userMessage}`;
    messagesDiv.appendChild(userDiv);

    try {
        // Invia il messaggio al servlet
        const response = await fetch('chatbot', {  // Modifica URL per il servlet
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `message=${encodeURIComponent(userMessage)}`
        });

        if (response.ok) {
            const data = await response.json();
            const botDiv = document.createElement("div");
            botDiv.classList.add("message", "bot");
            botDiv.textContent = `Bot: ${data.content}`;
            messagesDiv.appendChild(botDiv);

            // Scrolla verso il basso automaticamente
            messagesDiv.scrollTop = messagesDiv.scrollHeight;
        } else {
            throw new Error("Errore nella comunicazione con il server.");
        }
    } catch (error) {
        const errorDiv = document.createElement("div");
        errorDiv.classList.add("message", "error");
        errorDiv.textContent = `Errore: ${error.message}`;
        messagesDiv.appendChild(errorDiv);
    }

    // Resetta il campo input
    document.getElementById("userMessage").value = "";
}
