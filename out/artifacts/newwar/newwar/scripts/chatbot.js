async function sendMessage() {
    const userMessage = document.getElementById('userMessage').value;

    // Invia il messaggio al backend
    const response = await fetch('/chatbot', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `message=${encodeURIComponent(userMessage)}`
    });

    const data = await response.json();

    // Aggiungi i messaggi alla chat
    const messages = document.getElementById('messages');
    messages.innerHTML += `<div class="message user">${data.userMessage}</div>`;
    messages.innerHTML += `<div class="message bot">${data.botResponse}</div>`;

    // Svuota il campo input
    document.getElementById('userMessage').value = '';
}
