
const emailRegex = /^[a-zA-Z0-9._%+-]+@(studenti\.unisa\.it|unisa\.it)$/;


const passwordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@#$€&%]).+$/;


function validateForm() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    let errorMessage = '';


    if (!emailRegex.test(email)) {
        errorMessage += 'Formato email non valido. Deve terminare con "studenti.unisa.it" o "unisa.it".\n';
    }


    if (!passwordRegex.test(password)) {
        errorMessage += 'La password deve contenere almeno: 1 maiuscola, 1 minuscola, 1 numero, 1 carattere speciale (@, #, $, €, &, %).\n';
    }


    if (password.length < 8) {
        errorMessage += 'La password deve essere lunga almeno 8 caratteri.\n';
    } else if (password.length > 20) {
        errorMessage += 'La password non può essere più lunga di 20 caratteri.\n';
    }


    if (errorMessage !== '') {
        sessionStorage.setItem("loginError", errorMessage);
        window.location.href = window.location.pathname + "?action=errorFormat";

        return false;
    }

    return true;
}
