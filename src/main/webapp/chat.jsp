<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="it.unisa.uniclass.utenti.model.Utente, it.unisa.uniclass.utenti.model.Tipo" %>
<%@ page import="it.unisa.uniclass.orari.model.CorsoLaurea" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.uniclass.conversazioni.model.Messaggio" %>
<%@ page import="it.unisa.uniclass.conversazioni.service.ConversazioneService" %>
<%@ page import="it.unisa.uniclass.conversazioni.model.Conversazione" %>
<%@ page import="it.unisa.uniclass.utenti.model.Accademico" %>
<%@ page import="it.unisa.uniclass.utenti.service.AccademicoService" %>

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

    Long id = (Long) request.getAttribute("id");
    String email = (String) request.getAttribute("email");

    ConversazioneService conversazioneService = new ConversazioneService();

    Conversazione conversazione = conversazioneService.trovaConversazione(id);

    AccademicoService accademicoService = new AccademicoService();

    Accademico accademico = accademicoService.trovaEmailUniClass(email);
    Accademico accademicoSelf = conversazioneService.trovaAltroConversazione(conversazione,accademico);

    List<Messaggio> messaggi = List.of();

    if (tipoUtente == Tipo.Docente || tipoUtente == Tipo.Studente || tipoUtente == Tipo.Coordinatore){
         messaggi = (List<Messaggio>) request.getAttribute("messaggi");
    }

%>


<!DOCTYPE html>
<html>

<head>
    <title>UniClass</title>
    <script src="scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/chatCss.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

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
        <li id="orari"> <a href="servelt">Orari</a>
        </li>
        <li id="aule"><a href="servelt">Aule</a>
        </li>
        <li id="appelli"><a href="servelt">Appelli</a>
        </li>
        <li id="gutenti"><a href="servlet">Gestione Utenti</a>
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


<div class="chat-container">
    <div class="chat-header">
        <h2></h2>
    </div>

    <div class="chat-box">
        <%
            for (Messaggio messaggio : messaggi) {
                if (messaggio.getTopic() != null) {
        %>
        <div class="message red-text">
            <span class="message-text"><%= messaggio.getTopic() %></span>
        </div>
        <div class="message self">
            <span class="message-text"><%= messaggio.getBody() %></span>
        </div>
        <%
        } else if (messaggio.getAutore().equals(accademicoSelf)) {
        %>
        <div class="message self">
            <span class="message-text"><%= messaggio.getBody() %></span>
        </div>
        <%
        } else if (messaggio.getAutore().equals(accademico)) {
        %>
        <div class="message author">
            <span class="message-text"><%= messaggio.getBody() %></span>
        </div>
        <%
                }
            }
        %>
    </div>

    <form action="invioMessaggioServlet" method="POST" class="chat-input-container">
        <input type="text" name="messaggio" class="chat-input" placeholder="Scrivi un messaggio..." required>
        <button type="submit" class="send-button">Invia</button>
    </form>
</div>

<script src="scripts/aggiornaChat.js"></script>


<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>