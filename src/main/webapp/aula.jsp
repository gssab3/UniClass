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
    <title>Aula</title>
        <script src="scripts/sidebar.js" type="text/javascript"></script>
        <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
        <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
        <link type="text/css" rel="stylesheet" href="styles/formcss.css"/>
        <link type="text/css" rel="stylesheet" href="styles/footerstyle.css">

    <style>
        a {
            text-decoration: none;
            color: white;
        }
        h1
        {
            text-align: center;
        }
        .container-wrapper {
            display: flex;
            justify-content: space-between;
            align-items: center;
            max-width: 1000px;
            height: 255px;
            margin: 0 auto;
        }
        .container {
            align-items: center;
            flex: 0 1 30%;
            padding: 20px;
            border-radius: 8px;
            cursor: pointer;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            text-align: center;
            color: white;
            background-color: #3498db;
        }
        .container:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .container h2 {
            margin-top: 0;
        }
    </style>
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

<h1> Edifici </h1>
<br>
<div class="container-wrapper">
    <div class="container">
        <h2> <a href="/EdificioServlet?ed=F">Edificio F</a></h2>
    </div>
    <div class="container">
        <h2><a href="/EdificioServlet?ed=F2">Edificio F2</a> </h2>
    </div>
    <div class="container">
        <h2><a href="/EdificioServlet?ed=F3">Edificio F3</a> </h2>

    </div>
</div>

<br>
<br>
<br>
<%@include file = "footer.jsp" %>
</body>
</html>
