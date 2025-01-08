// Funzione per aggiornare i resti e gli anni in base al corso selezionato
function aggiornaResto() {
    var corsoLaurea = document.getElementById("corsoLaurea").value;

    // Verifica se è stato selezionato un corso di laurea
    if (corsoLaurea) {
        aggiornaResti(corsoLaurea);
        aggiornaAnni(corsoLaurea);
    } else {
        resettaResto();
        resettaAnno();
    }
}

// Funzione per aggiornare i resti in base al corso di laurea selezionato
function aggiornaResti(corsoLaurea) {
    // Chiamata AJAX per ottenere i resti per il corso di laurea selezionato
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "restiServlet?corsoLaurea=" + encodeURIComponent(corsoLaurea), true);
    xhr.onload = function() {
        if (xhr.status === 200) {
            var resti = JSON.parse(xhr.responseText);
            var restoSelect = document.getElementById("resto");

            // Resetta le opzioni di resto
            restoSelect.innerHTML = '<option value="">-- Seleziona un resto --</option>';

            // Aggiunge le nuove opzioni per il resto
            resti.forEach(function(resto) {
                var option = document.createElement("option");
                option.value = resto.id;
                option.textContent = resto.nome;
                restoSelect.appendChild(option);
            });
        }
    };
    xhr.send();
}

// Funzione per aggiornare gli anni didattici in base al corso di laurea selezionato
function aggiornaAnni(corsoLaurea) {
    // Chiamata AJAX per ottenere gli anni didattici per il corso di laurea selezionato
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "anniDidatticiServlet?corsoLaurea=" + encodeURIComponent(corsoLaurea), true);
    xhr.onload = function() {
        if (xhr.status === 200) {
            var anni = JSON.parse(xhr.responseText);
            var annoSelect = document.getElementById("anno");

            // Resetta le opzioni degli anni
            annoSelect.innerHTML = '<option value="">-- Seleziona un anno --</option>';

            // Aggiunge le nuove opzioni per gli anni
            anni.forEach(function(anno) {
                var option = document.createElement("option");
                option.value = anno.id;
                option.textContent = anno.nome;
                annoSelect.appendChild(option);
            });
        }
    };
    xhr.send();
}

// Funzione per resettare il campo "resto"
function resettaResto() {
    var restoSelect = document.getElementById("resto");
    restoSelect.innerHTML = '<option value="">-- Seleziona un resto --</option>';
}

// Funzione per resettare il campo "anno"
function resettaAnno() {
    var annoSelect = document.getElementById("anno");
    annoSelect.innerHTML = '<option value="">-- Seleziona un anno --</option>';
}
