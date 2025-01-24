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
    <title>Edificio</title>
    <script src="scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/formcss.css"/>
    <link type="text/css" rel="stylesheet" href="styles/footerstyle.css">

    <style>
        h1
        {
            text-align: center;
        }
        .buildings {
            list-style-type: none;
            padding-bottom: 20px;
            padding-top: 20px;
            padding-left: 250px;
            padding-right: 250px;
        }
        .building {
            background-color: #3498db;
            color: white;
            margin-bottom: 10px;
            padding: 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .building:hover {
            background-color: #2980b9;
        }
        .classes {
            display: none;
            list-style-type: none;
            padding-left: 20px;
        }
        .class {
            background-color: #2ecc71;
            color: white;
            margin-top: 5px;
            padding: 8px;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .class:hover {
            background-color: #27ae60;
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
        <li id="aule"><a href="servelt">Aule</a>
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

<h1> Edificio F </h1>
<ul class="buildings">
    <li class="building"> F1
        <ul class="classes">
            <li class="class">Monday 9:00 AM - 11:00 AM</li>
            <li class="class">Wednesday 2:00 PM - 4:00 PM</li>
        </ul>
    </li>
    <li class="building"> F2
        <ul class="classes">
            <li class="class">Monday 9:00 AM - 11:00 AM</li>
            <li class="class">Wednesday 2:00 PM - 4:00 PM</li>
        </ul>
    </li>
    <li class="building"> F3
        <ul class="classes">
            <li class="class">Monday 9:00 AM - 11:00 AM</li>
            <li class="class">Wednesday 2:00 PM - 4:00 PM</li>
        </ul>
    </li>
    <li class="building"> F4
        <ul class="classes">
            <li class="class">Monday 9:00 AM - 11:00 AM</li>
            <li class="class">Wednesday 2:00 PM - 4:00 PM</li>
        </ul>
    </li>
    <li class="building"> F5
        <ul class="classes">
            <li class="class">Monday 9:00 AM - 11:00 AM</li>
            <li class="class">Wednesday 2:00 PM - 4:00 PM</li>
        </ul>
    </li>
</ul>

<script>
    document.querySelectorAll('.building').forEach(building => {
        building.addEventListener('click', () => {
            const classes = building.querySelector('.classes');
            classes.style.display = classes.style.display === 'none' ? 'block' : 'none';
        });
    });

    document.querySelectorAll('.class').forEach(classItem => {
        classItem.addEventListener('click', (event) => {
            event.stopPropagation();
            const timetables = classItem.querySelector('.timetables');
            timetables.style.display = timetables.style.display === 'none' ? 'block' : 'none';
        });
    });
</script>

<br>
<br>
<br>
<%@include file = "footer.jsp" %>
</body>
</html>

