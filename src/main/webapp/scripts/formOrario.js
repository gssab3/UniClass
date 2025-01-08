// Funzione per aggiornare il resto e l'anno in base alla selezione del corso di laurea
function aggiornaResto() {
    var corsoLaurea = document.getElementById("corsoLaurea").value;

    if (corsoLaurea) {
        var xhr = new XMLHttpRequest();
        var xhrr = new XMLHttpRequest();

        // Richiesta per il resto
        xhr.open("GET", "getResto?corsoLaurea=" + corsoLaurea, true);
        xhr.onload = function() {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);

                console.log(response);

                // Aggiorna le opzioni per il "resto"
                var restoSelect = document.getElementById("resto");
                restoSelect.innerHTML = '<option value="">-- Seleziona un resto --</option>';
                response.forEach(function(resto) {
                    // Aggiungi ogni "resto" come opzione nel select
                    console.log(resto);
                    restoSelect.innerHTML += `<option value="${resto.id}">${resto["nome"]}</option>`;
                });
            }
        };

        // Richiesta per l'anno didattico
        xhrr.open("GET", "getAnno?corsoLaurea=" + corsoLaurea, true);
        xhrr.onload = function() {
            if (xhrr.status === 200) {  // Corretto: controlla lo stato di xhrr, non xhr
                var response = JSON.parse(xhrr.responseText);

                console.log(response);

                // Aggiorna le opzioni per l'"anno"
                var annoSelect = document.getElementById("anno");
                annoSelect.innerHTML = '<option value="">-- Seleziona un anno --</option>';
                response.forEach(function(anno) {
                    console.log(anno);
                    annoSelect.innerHTML += `<option value="${anno.id}">${anno["nome"]}</option>`;
                });
            }
        };

        // Invia le richieste
        xhrr.send();
        xhr.send()

    }
}
