<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="it.unisa.uniclass.utenti.service.StudenteService" %>
<%@ page import="it.unisa.uniclass.utenti.service.CoordinatoreService" %>
<%@ page import="it.unisa.uniclass.utenti.service.PersonaleTAService" %>
<%@ page import="it.unisa.uniclass.utenti.service.DocenteService" %>
<%@ page import="it.unisa.uniclass.utenti.model.*" %>

<%
    /* Sessione HTTP */
    HttpSession sessione = request.getSession(true);
    Utente user = (Utente) sessione.getAttribute("currentSessionUser");

    StudenteService studenteService = new StudenteService();

    CoordinatoreService coordinatoreService = new CoordinatoreService();

    DocenteService docenteService = new DocenteService();

    PersonaleTAService personaleTAService = new PersonaleTAService();

    Studente studente = null;
    Docente docente = null;
    Coordinatore coordinatore = null;
    PersonaleTA personaleTA = null;

    /* Controllo tipo utente */
    Tipo tipoUtente;
    if (user != null)
        tipoUtente = (Tipo) user.getTipo();
    else
        tipoUtente = null;

    /* Prendere l'utente */
    if(tipoUtente != null) {
        if (tipoUtente.equals(Tipo.Studente)) {
            studente = studenteService.trovaStudenteEmailUniClass(user.getEmail());
        }
        else if (tipoUtente.equals(Tipo.Docente)) {
            docente = docenteService.trovaEmailUniClass(user.getEmail());
        }
        else if (tipoUtente.equals(Tipo.Coordinatore)) {
            coordinatore = coordinatoreService.trovaCoordinatoreEmailUniclass(user.getEmail());
        }
        else if (tipoUtente.equals(Tipo.PersonaleTA)) {
            personaleTA = personaleTAService.trovaEmail(user.getEmail());
        }
    }

%>

<!DOCTYPE html>
<html>

<head>
    <title>JSP - Hello World</title>
    <script src="scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="styles/headerStyle.css" />
    <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css" />
</head>

<body>

<% if (tipoUtente.equals(Tipo.Studente)) { %>
    <div class="barraNavigazione" id="barraNavigazione">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><img src="images/icons/menuOpenIcon.png" alt="closebtn"></a>
        <p>Menu<p>
        <ul id="menu">
            <li id="orari"><a href="servelt">Orari</a></li>
            <li id="aule"><a href="servelt">Aule</a></li>
            <li id="agenda"><a href="servelt">Agenda</a></li>
            <li id="appelli"><a href="servelt">Appelli</a></li>
            <li id="conversazioni"><a href="servelt">Conversazioni</a></li>
            <li id="mappa"><a href="mappa.jsp">Mappa</a></li>
            <li id="infoapp"><a href="info">Info App</a></li>
            <li id="aboutus"><a href="aboutus.jsp">Chi Siamo</a></li>
        </ul>
    </div>
<% } else if (tipoUtente.equals(Tipo.Docente) || tipoUtente.equals(Tipo.Coordinatore)) { %>
    <div class="barraNavigazione" id="barraNavigazione">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><img src="images/icons/menuOpenIcon.png" alt="closebtn"></a>
        <p>Menu<p>
        <ul id="menu">
            <li id="orari"><a href="servelt">Orari</a></li>
            <li id="aule"><a href="servelt">Aule</a></li>
            <li id="agenda"><a href="servelt">Agenda</a></li>
            <li id="appelli"><a href="servelt">Appelli</a></li>
            <li id="conversazioni"><a href="servelt">Conversazioni</a></li>
            <li id="mappa"><a href="mappa.jsp">Mappa</a></li>
            <li id="infoapp"><a href="info">Info App</a></li>
            <li id="aboutus"><a href="aboutus.jsp">Chi Siamo</a></li>
        </ul>
    </div>
<% } else if (tipoUtente.equals(Tipo.PersonaleTA)) { %>
    <div class="barraNavigazione" id="barraNavigazione">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><img src="images/icons/menuOpenIcon.png" alt="closebtn"></a>
        <p>Menu<p>
        <ul id="menu">
            <li id="orari"><a href="servelt">Orari</a></li>
            <li id="aule"><a href="servelt">Aule</a></li>
            <li id="appelli"><a href="servelt">Appelli</a></li>
            <li id="gutenti"><a href="servlet">Gestione Utenti</a></li>
            <li id="mappa"><a href="mappa.jsp">Mappa</a></li>
            <li id="infoapp"><a href="info">Info App</a></li>
            <li id="aboutus"><a href="aboutus.jsp">Chi Siamo</a></li>
        </ul>
    </div>
<% } %>

<jsp:include page="header.jsp" />

<% if (tipoUtente.equals(Tipo.Studente)) { %>
    <div class="listaInfo" id="listaInfo">
        <h2>Informazioni</h2>
        <ul id="infolist">
            <li id="nome"><% studente.getNome(); %></li>
            <li id="cognome"><% studente.getCognome(); %></li>
            <li id="dataNascita"><% studente.getDataNascita(); %></li>
            <li id="matricola"><% studente.getMatricola(); %></li>
            <li id="email"><% studente.getEmail(); %></li>
            <li id="corsoLaurea"><% studente.getCorsoLaurea(); %></li>
            <li id="dataIscrizione"><% studente.getIscrizione(); %></li>
        </ul>
    </div>
<% } else if (tipoUtente.equals(Tipo.Docente)) { %>
    <div class="listaInfo" id="listaInfo">
        <h2>Informazioni</h2>
        <ul id="infolist">
            <li id="nome"><% docente.getNome(); %></li>
            <li id="cognome"><% docente.getCognome(); %></li>
            <li id="dataNascita"><% docente.getDataNascita(); %></li>
            <li id="matricola"><% docente.getMatricola(); %></li>
            <li id="email"><% docente.getEmail(); %></li>
            <li id="corsoLaurea"><% docente.getCorsoLaurea(); %></li>
            <li id="dataIscrizione"><% docente.getIscrizione(); %></li>
            <li id="Corsi Insegnati:"><% docente.getCorsi(); %></li>
        </ul>
    </div>
<% } else if (tipoUtente.equals(Tipo.Coordinatore)) { %>
    <div class="listaInfo" id="listaInfo">
        <h2>Informazioni</h2>
        <ul id="infolist">
            <li id="nome"><% coordinatore.getNome(); %></li>
            <li id="cognome"><% coordinatore.getCognome(); %></li>
            <li id="dataNascita"><% coordinatore.getDataNascita(); %></li>
            <li id="matricola"><% coordinatore.getMatricola(); %></li>
            <li id="email"><% coordinatore.getEmail(); %></li>
            <li id="corsoLaurea"><% coordinatore.getCorsoLaurea(); %></li>
            <li id="dataIscrizione"><% coordinatore.getIscrizione(); %></li>
            <li id="Corsi Insegnati:"><% coordinatore.getCorsi(); %></li>
        </ul>
    </div>
<% } else if (tipoUtente.equals(Tipo.PersonaleTA)) { %>
    <div class="listaInfo" id="listaInfo">
        <h2>Informazioni</h2>
        <ul id="infolist">
            <li id="nome"><% personaleTA.getNome(); %></li>
            <li id="cognome"><% personaleTA.getCognome(); %></li>
            <li id="dataNascita"><% personaleTA.getDataNascita(); %></li>
            <li id="id"><% personaleTA.getId(); %></li>
            <li id="email"><% personaleTA.getEmail(); %></li>
            <li id="telefono"><% personaleTA.getTelefono(); %></li>
        </ul>
    </div>
<% } %>

    <!-- Inserire pulsante Logout -->

<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>