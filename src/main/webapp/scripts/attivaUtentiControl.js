
const emailRegex = /^[a-zA-Z0-9._%+-]+@(studenti\.unisa\.it|unisa\.it)$/;

const matRegex = /^\d{10}$/;

function validateActivation(){
    const matricola = document.getElementById("matricola").value;
    const email = document.getElementById("email").value;
    let errorMessage ='';

    if(!emailRegex.test(email)){
        errorMessage += 'Formato email non valido. Deve terminare con "studenti.unisa.it" o "unisa.it".\n';
    }

    if(!matRegex.test(matricola)){
        errorMessage += 'Formato matricola non valido. Deve essere formata da soli numeri';
    }

    if(matricola.length < 10){
        errorMessage += 'La matricola deve essere di 10 caratteri';
    } else if(matricola.length > 10){
        errorMessage += 'La matricola deve essere di 10 caratteri';
    }

    if(errorMessage !== ''){
        sessionStorage.setItem("activationError",errorMessage)
        window.location.href = window.location.pathname + "?action=errorFormat";

        return false;
    }
    return true;
}