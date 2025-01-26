<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="it.unisa.uniclass.utenti.model.Utente, it.unisa.uniclass.utenti.model.Tipo" %>
<%@ page import="it.unisa.uniclass.orari.model.CorsoLaurea" %>
<%@ page import="java.util.List" %>

<%
    /* Sessione HTTP */
    HttpSession sessione = request.getSession(true);
    Utente user = (Utente) sessione.getAttribute("currentSessionUser");


    /* controllo tipo utente*/

    Tipo tipoUtente;
    if(user != null)
        tipoUtente = (Tipo) user.getTipo();
    else
        tipoUtente = null;

    List<CorsoLaurea> corsiLaurea = (List<CorsoLaurea>) request.getAttribute("corsi");

%>

<html>
<head>
    <title> Appello Studenti </title>
    <script src="scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/formcss.css"/>
    <link type="text/css" rel="stylesheet" href="styles/footerstyle.css">
    <link type="text/css" rel="stylesheet" href="styles/appellistudentistyle.css">
    <link rel="icon" href="images/logois.png" sizes="32x32" type="image/png">

</head>

<body>
<% if(tipoUtente == null) { %>

<div class="barraNavigazione" id="barraNavigazione">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><img src="images/icons/menuOpenIcon.png" alt="closebtn"></a>
    <p>Menu<p>
    <ul id="menu">
        <li id="orari"> <a href="servelt">Orari</a>
        </li>
        <li id="aule"><a href="servelt">Aule</a>
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

<% } else if(tipoUtente.equals(Tipo.Studente)) { %>

<div class="barraNavigazione" id="barraNavigazione">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><img src="images/icons/menuOpenIcon.png" alt="closebtn"></a>
    <p>Menu<p>
    <ul id="menu">
        <li id="orari"> <a href="servelt">Orari</a>
        </li>
        <li id="aule"><a href="servelt">Aule</a>
        </li>
        <li id="agenda"><a href="servelt">Agenda</a>
        </li>
        <li id="appelli"><a href="servelt">Appelli</a>
        </li>
        <li id="conversazioni"><a href="/ConversazioniServlet?utenteEmail=<%=user.getEmail()%>">Conversazioni</a>
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
<% } else if(tipoUtente.equals(Tipo.Docente) || tipoUtente.equals(Tipo.Coordinatore)) { %>

<div class="barraNavigazione" id="barraNavigazione">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><img src="images/icons/menuOpenIcon.png" alt="closebtn"></a>
    <p>Menu<p>
    <ul id="menu">
        <li id="orari"> <a href="servelt">Orari</a>
        </li>
        <li id="aule"><a href="servelt">Aule</a>
        </li>
        <li id="agenda"><a href="servelt">Agenda</a>
        </li>
        <li id="appelli"><a href="servelt">Appelli</a>
        </li>
        <li id="conversazioni"><a href="/ConversazioniServlet?utenteEmail=<%=user.getEmail()%>">Conversazioni</a>
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

<% } else if(tipoUtente.equals(Tipo.PersonaleTA)) { %>

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
<% } %>

<jsp:include page="header.jsp"/>
<br>
<br>
<div class="container">
    <div class="header1">
        <h1>Appelli Disponibili</h1>
    </div>
    <br>
    <main class="roll-calls-list">
        <div class="roll-call-card">
            <h2 class="roll-call-title">Ingegneria del Software</h2>
            <div class="roll-call-details">
                <p class="roll-call-time">15/02/2025 - 10.00</p>
                <p class="roll-call-location">Aula: P4 Edificio F3</p>
            </div>
            <button class="book-button">Prenota</button>
        </div>

        <div class="roll-call-card">
            <h2 class="roll-call-title">Programmazione Distribuita</h2>
            <div class="roll-call-details">
                <p class="roll-call-time">11/02/2025 - 12.00 </p>
                <p class="roll-call-location">Aula: F8 Edificio F2</p>
            </div>
            <button class="book-button">Prenota</button>
        </div>

        <div class="roll-call-card">
            <h2 class="roll-call-title">Machine Learning</h2>
            <div class="roll-call-details">
                <p class="roll-call-time">21/01/2025 - 15.00</p>
                <p class="roll-call-location">Aula: P3 Edificio F3</p>
            </div>
            <button class="book-button">Prenota</button>
        </div>

        <div class="roll-call-card">
            <h2 class="roll-call-title">Fondamenti di Intelligenza Artificiale</h2>
            <div class="roll-call-details">
                <p class="roll-call-time">27/01/2025 - 09.00 </p>
                <p class="roll-call-location">Aula: F1 Edificio F2</p>
            </div>
            <button class="book-button">Prenota</button>
        </div>
    </main>
</div>
<br>
<br>
<br>
<%@include file = "footer.jsp" %>
</body>
</html>

