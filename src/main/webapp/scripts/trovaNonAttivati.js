function aggiornaListaUtenti() {
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "GetNonAttivati", true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            console.log(response);

            var listContainer = document.querySelector('.list');
            listContainer.innerHTML = '';

            response.forEach(function (utente) {
                var listItem = document.createElement('p');
                listItem.textContent = 'Email: ' + utente.email + ' - Matricola: ' + utente.matricola + ' - Tipo: ' + utente.tipo;

                listContainer.appendChild(listItem);
            });
        } else {
            console.error("Errore nella richiesta AJAX: " + xhr.status);
        }
    };

    xhr.send();
}

window.onload = aggiornaListaUtenti;
