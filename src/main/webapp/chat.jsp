<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="it.unisa.uniclass.utenti.model.Utente, it.unisa.uniclass.utenti.model.Tipo" %>
<%@ page import="it.unisa.uniclass.orari.model.CorsoLaurea" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.uniclass.conversazioni.model.Messaggio" %>
<%@ page import="it.unisa.uniclass.utenti.model.Accademico" %>
<%@ page import="it.unisa.uniclass.utenti.service.AccademicoService" %>
<%@ page import="java.util.ArrayList" %>

<%
    /* Sessione HTTP */
    HttpSession sessione = request.getSession(true);
    Utente user = (Utente) sessione.getAttribute("currentSessionUser");
    if(user != null){
        session.setAttribute("utenteEmail", user.getEmail());
    }



    /* controllo tipo utente*/

    Tipo tipoUtente = null;
    if(user != null && ((user.getTipo() != Tipo.Docente) || (user.getTipo() != Tipo.Coordinatore) || (user.getTipo()) != Tipo.Studente))
        tipoUtente = (Tipo) user.getTipo();
    else if (user != null && (user.getTipo() == Tipo.PersonaleTA))
        response.sendRedirect("ErroreAccesso.jsp");
    else
        response.sendRedirect("Login.jsp");

    Long id = (Long) request.getAttribute("id");
    String email = (String) request.getAttribute("email");

    //Accademico accademico = (Accademico) request.getAttribute("accademico");
    Accademico accademico = (Accademico) session.getAttribute("accademico");
    //Accademico accademicoSelf = (Accademico) request.getAttribute("accademicoSelf");
    Accademico accademicoSelf = (Accademico) session.getAttribute("accademicoSelf");



    List<Messaggio> messaggi = new ArrayList<Messaggio>();
    List<Messaggio> messaggiInviati;
    List<Messaggio> messaggiRicevuti;
    //List<Messaggio> messaggigi = (List<Messaggio>) request.getAttribute("messaggigi");
    List<Messaggio> messaggigi = (List<Messaggio>) session.getAttribute("messaggigi");

    if (tipoUtente == Tipo.Docente || tipoUtente == Tipo.Studente || tipoUtente == Tipo.Coordinatore){
        // messaggi = (List<Messaggio>) request.getAttribute("messaggi");
         messaggiInviati = (List<Messaggio>) request.getAttribute("messaggiInviati");
         messaggiRicevuti = (List<Messaggio>) request.getAttribute("messaggiRicevuti");
    }

%>


<!DOCTYPE html>
<html>

<head>
    <title>UniClass Chat</title>
    <script src="scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/chatCss.css">
    <link rel="icon" href="images/logois.png" sizes="32x32" type="image/png">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

</head>
<body>

<% if(tipoUtente.equals(Tipo.Studente)) { %>

<div class="barraNavigazione" id="barraNavigazione">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><img src="images/icons/menuOpenIcon.png" alt="closebtn"></a>
    <p>Menu<p>
    <ul id="menu">
        <li id="orari"> <a href="servelt">Orari</a>
        </li>
        <li id="aule"><a href="servelt">Aule</a>
        </li>
        <li id="agenda"><a href="servelt">Gestisci Agenda</a>
        </li>
        <li id="appelli"><a href="servelt">Appelli</a>
        </li>
        <li id="conversazioni"><a href="servelt">Conversazioni</a>
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
        <li id="aule"><a href="servelt">Aule</a>
        </li>
        <li id="appelli"><a href="servelt">Appelli</a>
        </li>
        <li id="conversazioni"><a href="servelt">Conversazioni</a>
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


<% //if(messaggi.isEmpty()){ %>
    si
<%// } %>

<div class="chat-container">
    <div class="chat-header">
        <h2></h2>
    </div>

    <div id="chat-box" class="chat-box">
        <%
            for (Messaggio messaggio : messaggigi) {
                if (!messaggio.getTopic().getNome().equals("VUOTO")) {
        %>
        <div class="message red-text">
            <span class="message-text">[<%= messaggio.getTopic().getNome()%>]</span>
        </div>
            <% if (messaggio.getAutore().getEmail().equals(accademicoSelf.getEmail())) {%>
            <div class="message self">
                <span class="message-text"><%= messaggio.getBody() %></span>
            </div>
            <%
            } else if (messaggio.getAutore().getEmail().equals(accademico.getEmail())) {
            %>
            <div class="message author">
                <span class="message-text"><%= messaggio.getBody() %></span>
            </div>
                <% } %>
        <%
        } else if (messaggio.getAutore().getEmail().equals(accademicoSelf.getEmail())) {
        %>
        <div class="message self">
            <span class="message-text"><%= messaggio.getBody() %></span>
        </div>
        <%
        } else if (messaggio.getAutore().getEmail().equals(accademico.getEmail())) {
        %>
        <div class="message author">
            <span class="message-text"><%= messaggio.getBody() %></span>
        </div>
        <%
                }
            }
        %>
    </div>

    <div class="chat-input-container">
        <label for="testo">Messaggio:</label>
        <input type="text" id="testo" name="testo" class="chat-input" placeholder="Scrivi un messaggio..." required>

        <input type="hidden" id="emailInvio" name="emailInvio" value="<%=accademico.getEmail()%>">

        <button type="button" class="send-button" onclick="window.location.href='inviaMessaggioChatServlet?testo=' + encodeURIComponent(document.getElementById('testo').value) + '&emailInvio=' + encodeURIComponent(document.getElementById('emailInvio').value)">Invia</button>
    </div>

</div>


</body>
</html>