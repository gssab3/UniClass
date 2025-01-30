/*
document.getElementById("chatForm").addEventListener("submit", function(event) {
    event.preventDefault();


    var xhr = new XMLHttpRequest();
    xhr.open("POST", this.action, true);


    xhr.onload = function () {
        if (xhr.status === 200) {

            var response = JSON.parse(xhr.responseText);
            console.log("Messaggio ricevuto:", response);


            var chatBox = document.getElementById("chat-box");

            var newMessageDiv = document.createElement("div");
            newMessageDiv.classList.add("message","self");

            var messageText = document.createElement("span");
            messageText.classList.add("message-text");
            messageText.textContent = response.messaggio;

            newMessageDiv.appendChild(messageText);

            chatBox.appendChild(newMessageDiv);
            chatBox.scrollTop = chatBox.scrollHeight;

            document.getElementById("testoMessaggio").value = "";
        } else {
            console.error("Errore nella richiesta AJAX: " + xhr.status);
        }
    };


    xhr.send();
});
*/
