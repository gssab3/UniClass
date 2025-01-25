<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="it.unisa.uniclass.utenti.model.Utente, it.unisa.uniclass.utenti.model.Tipo" %>
<%@ page import="it.unisa.uniclass.orari.model.CorsoLaurea" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.uniclass.utenti.model.Accademico" %>

<%
    /* Sessione HTTP */
    HttpSession sessione = request.getSession(true);
    Utente user = (Utente) sessione.getAttribute("currentSessionUser");


    /* controllo tipo utente*/

    Tipo tipoUtente;
    if(user != null) {
        tipoUtente = (Tipo) user.getTipo();
        if(!tipoUtente.equals(Tipo.PersonaleTA)) {
            response.sendRedirect("ErroreAccesso.jsp");
        }
    }
    else
        response.sendRedirect("Login.jsp");

    List<Accademico> accademiciNonAttivati = (List<Accademico>) request.getAttribute("accademiciNonAttivati");

%>
<!DOCTYPE html>
<html>

<head>
    <title>UniClass</title>
    <script src="../scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="../styles/headerStyle.css"/>
    <link type="text/css" rel="stylesheet" href="../styles/barraNavigazioneStyle.css" />
    <link type="text/css" rel="stylesheet" href="../styles/uniClassAdd.css" />

</head>
<body id="uniClassAdd">


<div class="barraNavigazione" id="barraNavigazione">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><img src="images/icons/menuOpenIcon.png" alt="closebtn"></a>
    <p>Menu<p>
    <ul id="menu">
        <li id="aule"><a href="aula.jsp">Aule</a>
        </li>
        <li id="appelli"><a href="servelt">Appelli</a>
        </li>
        <li id="gutenti"><a href="/GestioneUtenti">Gestione Utenti</a>
        </li>
        <li id="mappa"><a href="mappa.jsp">Mappa</a>
        </li>
        <li id="ChatBot"><a href="ChatBot.jsp">ChatBot</a>
        </li>
        <li id="infoapp"><a href="infoapp.jsp">Info App</a>
        </li>
        <li id="aboutus"><a href="aboutus.jsp">Chi Siamo</a>
        </li>
    </ul>
</div>





    <jsp:include page="../header.jsp"/>

<div class="container">
    <div class="left-block">
        <h2>Lista Utenti</h2>
        <div class="list">
            <p>Elemento 1</p>
            <p>Elemento 2</p>
            <p>Elemento 3</p>
        </div>
    </div>

    <div class="right-block">
        <h2>Aggiunta Utente</h2>
        <form action="/AttivaUtentiServlet" method="POST">
            <label for="matricola">Matricola:</label>
            <input type="text" id="matricola" name="matricola" required><br><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br><br>

            <label for="tipo">Tipo:</label>
            <select id="tipo" name="tipo" required>
                <option value="Studente">Studente</option>
                <option value="Docente">Docente</option>
                <option value="Coordinatore">Coordinatore</option>
                <!-- <option value="PersonaleTA">PersonaleTA</option> -->
            </select><br><br>

            <input type="submit" value="Invia">
        </form>
    </div>
</div>


    </body>
    </html>