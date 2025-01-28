// Funzione per aggiornare la lista delle email
function aggiornaEmail() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "GetEmailServlet", true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            console.log(response);

            var emailUtenteCorrente = "<%= accademicoSelf.getEmail() %>";

            // Selezione del campo email
            var emailSelect = document.getElementById("email");
            emailSelect.innerHTML = '<option value="" disabled selected>Seleziona un\'email</option>';

            // Aggiunta delle email al dropdown
            response.forEach(function (email) {
                if (email !== emailUtenteCorrente) {
                    emailSelect.innerHTML += `<option value="${email}">${email}</option>`;
                }
            });
        } else {
            console.error("Errore nella richiesta AJAX: " + xhr.status);
        }
    };
    xhr.send();
}

// Carica le email al caricamento della pagina
window.onload = aggiornaEmail;
