<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="it.unisa.uniclass.utenti.model.Utente, it.unisa.uniclass.utenti.model.Tipo" %>
<%@ page import="it.unisa.uniclass.orari.model.CorsoLaurea" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.uniclass.orari.service.dao.CorsoLaureaDAO" %>
<%@ page import="it.unisa.uniclass.conversazioni.model.Conversazione" %>
<%@ page import="it.unisa.uniclass.utenti.model.Accademico" %>
<%@ page import="it.unisa.uniclass.conversazioni.service.ConversazioneService" %>
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

  List<Conversazione> conversazioni = List.of();
  ConversazioneService conversazioneService = new ConversazioneService();

  AccademicoService accademicoService = new AccademicoService();

  Accademico accademicoSelf = accademicoService.trovaEmailUniClass(user.getEmail());

  if (tipoUtente == Tipo.Docente || tipoUtente == Tipo.Studente || tipoUtente == Tipo.Coordinatore){
    conversazioni = (List<Conversazione>) request.getAttribute("conversazioni");
  }
%>


<!DOCTYPE html>
<html>

<head>
  <title>UniClass Chat</title>
  <script src="scripts/sidebar.js" type="text/javascript"></script>
  <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
  <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css" />

</head>
<body id="uniClassChat">

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

//fare retrieve delle conversazioni dell'utente'

<body id="uniClassChat">
<div class="mega-container">
  <h1>Conversazioni</h1>

  <% for (Conversazione conversazione :  conversazioni) {
        Accademico accademico = conversazioneService.trovaAltroConversazione(conversazione, accademicoSelf);
  %>
  <!-- Singolo blocco per ogni conversazione -->
  <a href="/chatServlet?conversazione?=<%=conversazione.getId()%>&accademico?=<%=accademico.getEmail()%>>" style="text-decoration: none; color: inherit;">
  <div class="conversation-item">
    <div class="conversation-img">
      <% if (accademico.getTipo().equals(Tipo.Studente)) { %>
      <img src="images/icons/iconstudent.png" alt="immagineutente">
      <%} else if (accademico.getTipo().equals(Tipo.Docente) || accademico.getTipo().equals(Tipo.Coordinatore)) { %>
      <img src="images/icons/iconprof.png" alt="immagineutente">
      <%}%>
    </div>
    <div class="conversation-info">
      <h3><%= accademico.getNome() %> <%= accademico.getCognome() %></h3>
    </div>
  </div>
  </a>
  <% } %>
</div>




</body>
</html>