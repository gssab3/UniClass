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
    if(user != null){
        session.setAttribute("utenteEmail", user.getEmail());
    }


    StudenteService studenteService = new StudenteService();

    CoordinatoreService coordinatoreService = new CoordinatoreService();

    DocenteService docenteService = new DocenteService();

    PersonaleTAService personaleTAService = new PersonaleTAService();

    Studente studente = null;
    Docente docente = null;
    Coordinatore coordinatore = null;
    PersonaleTA personaleTA = null;

    /* Controllo tipo utente */
    Tipo tipoUtente = null;
    if (user != null)
        tipoUtente = (Tipo) user.getTipo();
    else
        response.sendRedirect("Login.jsp");

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
    <title>UniClass Account</title>
    <script src="scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="styles/headerStyle.css" />
    <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/informazioniStyle.css">
    <link rel="icon" href="images/logois.png" sizes="32x32" type="image/png">
</head>

<body>

<% if(tipoUtente.equals(Tipo.Studente)) { %>

<div class="barraNavigazione" id="barraNavigazione">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><img src="images/icons/menuOpenIcon.png" alt="closebtn"></a>
    <p>Menu<p>
    <ul id="menu">
        <li id="aule"><a href="aula.jsp">Aule</a>
        </li>
        <li id="agenda"><a href="servelt">Agenda</a>
        </li>
        <li id="appelli"><a href="servelt">Appelli</a>
        </li>
        <li id="conversazioni"><a href="Conversazioni">Conversazioni</a>
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
    <li id="aule"><a href="aula.jsp">Aule</a>
    </li>
    <li id="appelli"><a href="servelt">Appelli</a>
    </li>
    <li id="conversazioni"><a href="Conversazioni">Conversazioni</a>
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
        <li id="gutenti"><a href="PersonaleTA/AttivaUtenti.jsp">Gestione Utenti</a>
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

<jsp:include page="header.jsp" />

<% if (tipoUtente.equals(Tipo.Studente)) { %>
    <div class="listaInfo" id="listaInfo">
        <h2>Informazioni</h2>
        <ul id="infolist">
            <% if (user.getTipo().equals(Tipo.Studente)) { %>
            <img src="images/icons/iconstudent.png" alt="immagineutente">
            <%} %>
            <li id="nome"><%= studente.getNome() %></li>
            <li id="cognome"><%= studente.getCognome() %></li>
            <li id="dataNascita"><%= studente.getDataNascita() %></li>
            <li id="matricola"><%= studente.getMatricola() %></li>
            <li id="email"><%= studente.getEmail() %></li>
            <li id="corsoLaurea"><%= studente.getCorsoLaurea().getNome()%></li>
            <li id="dataIscrizione"><%= studente.getIscrizione() %></li>
        </ul>
        <form action="LogoutServlet" method="post">
            <button type="submit" class="logout-button">Logout</button>
        </form>
    </div>
<% } else if (tipoUtente.equals(Tipo.Docente)) { %>
    <div class="listaInfo" id="listaInfo">
        <h2>Informazioni</h2>
        <ul id="infolist">
            <% if (user.getTipo().equals(Tipo.Docente)) { %>
            <img src="images/icons/iconprof.png" alt="immagineutente">
            <%}%>
            <li id="nome"><%= docente.getNome() %></li>
            <li id="cognome"><%= docente.getCognome() %></li>
            <li id="dataNascita"><%= docente.getDataNascita() %></li>
            <li id="matricola"><%= docente.getMatricola() %></li>
            <li id="email"><%= docente.getEmail() %></li>
            <li id="corsoLaurea"><%= docente.getCorsoLaurea().getNome() %></li>
            <li id="dataIscrizione"><%= docente.getIscrizione() %></li>
            <li id="corsiInsegnati:"><%= "Corsi Insegnati: " + docente.getCorsi() %></li>
        </ul>
        <form action="LogoutServlet" method="post">
            <button type="submit" class="logout-button">Logout</button>
        </form>
    </div>
<% } else if (tipoUtente.equals(Tipo.Coordinatore)) { %>
    <div class="listaInfo" id="listaInfo">
        <h2>Informazioni</h2>
        <ul id="infolist">
            <% if (user.getTipo().equals(Tipo.Coordinatore)) { %>
            <img src="images/icons/iconprof.png" alt="immagineutente">
            <%}%>
            <li id="nome"><%= coordinatore.getNome() %></li>
            <li id="cognome"><%= coordinatore.getCognome() %></li>
            <li id="dataNascita"><%= coordinatore.getDataNascita() %></li>
            <li id="matricola"><%= coordinatore.getMatricola() %></li>
            <li id="email"><%= coordinatore.getEmail() %></li>
            <li id="corsoLaurea"><%= coordinatore.getCorsoLaurea().getNome()     %></li>
            <li id="dataIscrizione"><%= coordinatore.getIscrizione() %></li>
            <li id="corsiInsegnati:"><%= "Corsi Insegnati: " + docente.getCorsi() %></li>
        </ul>
        <form action="LogoutServlet" method="post">
            <button type="submit" class="logout-button">Logout</button>
        </form>
    </div>
<% } else if (tipoUtente.equals(Tipo.PersonaleTA)) { %>
    <div class="listaInfo" id="listaInfo">
        <h2>Informazioni</h2>
        <ul id="infolist">
            <% if (user.getTipo().equals(Tipo.PersonaleTA)) { %>
            <img src="images/icons/iconpersonaleTA.png" alt="immagineutente">
            <%}%>
            <li id="nome"><%= personaleTA.getNome() %></li>
            <li id="cognome"><%= personaleTA.getCognome() %></li>
            <li id="dataNascita"><%= personaleTA.getDataNascita() %></li>
            <li id="id"><%= personaleTA.getId() %></li>
            <li id="email"><%= personaleTA.getEmail() %></li>
            <li id="telefono"><%= personaleTA.getTelefono() %></li>
        </ul>
        <form action="LogoutServlet" method="post">
            <button type="submit" class="logout-button">Logout</button>
        </form>
    </div>
<% } %>

    <!-- Inserire pulsante Logout -->


</body>
</html>