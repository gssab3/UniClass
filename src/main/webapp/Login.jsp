<%@ page import="it.unisa.uniclass.utenti.model.Utente" %>
<%@ page import="it.unisa.uniclass.utenti.model.Tipo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%
    HttpSession sessione = request.getSession(true);
    Utente user = (Utente) sessione.getAttribute("currentSessionUser");


    /* controllo tipo utente*/
    Tipo tipoUtente;
    if(user != null)
        response.sendRedirect("/Home");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta name="viewport"  content="initial-scale=1, width=device-width">
<title>UniClass</title>
    <link type="text/css" rel="stylesheet" href="styles/login.css">
    <link rel="icon" href="images/logois.png" sizes="32x32" type="image/png">
    <script src="scripts/login.js" defer></script>
</head>
<body style="background-image: none;">

<div class="login-page">
    <div class="squarelogin">
        <div class="contenutologin">

            <form action="Login" class="loginform" method="POST" onsubmit="return validateForm()">

                <div id="error" class="error"></div>

                <% if(request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("error") ){ %>
                <div class="tableRow">
                    <p class="error">email o password errati!</p>
                </div>
                <% } else if(request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("notactivated") ) { %>
                <div class="tableRow">
                    <p class="error">Il tuo Account non è ancora stato attivato!</p>
                    <p class="error">Riceverai le credenziali di accesso quando il tuo account sarà attivo.</p>
                </div>
                <% }%>
                <div>
                    <h1 class="titolologin">
                        <span>Bentornato</span>
                    </h1>
                    <p class="descrizionelogin">
                        Effettua il Login per accedere alla tua area riservata
                    </p>
                </div>

                <div>
                    <div class="campilogin">
                        <div>
                            <label for="input-email" class="titoloemailpass">Email</label>
                            <input type="text" name="email" placeholder="Enter your email" required class="emailpass" id="email">
                        </div>

                        <div>
                            <label for="input-pass" class="titoloemailpass">Password</label>
                            <input type="password" name="password" placeholder="Enter your password" required class="emailpass" id="password">
                        </div>
                    </div>
                </div>

                <div>
                    <div class="pulsantilogin">
                        <input type="submit" value="Log In" class="logreg">
                    </div>
                </div>

                <a href="Home">Torna alla Home</a>
            </form>
        </div>
    </div>
</div>



</body>
</html>