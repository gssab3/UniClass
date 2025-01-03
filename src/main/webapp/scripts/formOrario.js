// Funzione per aggiornare il resto e l'anno in base alla selezione del corso di laurea
function aggiornaResto() {
    var corsoLaurea = document.getElementById("corsoLaurea").value;

    if (corsoLaurea) {
        var xhr = new XMLHttpRequest();

        xhr.open("GET", "getRestoAnno?corsoLaurea=" + corsoLaurea, true);
        xhr.onload = function() {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);

                // Aggiorna le opzioni per il "resto"
                var restoSelect = document.getElementById("resto");
                restoSelect.innerHTML = '<option value="">-- Seleziona un resto --</option>';
                response.resti.forEach(function(resto) {
                    restoSelect.innerHTML += `<option value="${resto.id}">${resto.nome}</option>`;
                });

                // Aggiorna le opzioni per l'"anno"
                var annoSelect = document.getElementById("anno");
                annoSelect.innerHTML = '<option value="">-- Seleziona un anno --</option>';
                response.anni.forEach(function(anno) {
                    annoSelect.innerHTML += `<option value="${anno.id}">${anno.nome}</option>`;
                });
            }
        };
        xhr.send();
    }
}
