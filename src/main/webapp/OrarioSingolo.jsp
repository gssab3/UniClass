<%@ page import="it.unisa.uniclass.utenti.model.Utente" %>
<%@ page import="it.unisa.uniclass.utenti.model.Tipo" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.uniclass.orari.model.*" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.util.stream.Collectors" %><%--
  Created by IntelliJ IDEA.
  User: davan
  Date: 08/01/2025
  Time: 00:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  /* Sessione HTTP */
  HttpSession sessione = request.getSession(true);
  Utente user = (Utente) sessione.getAttribute("currentSessionUser");
  if(user != null){
    session.setAttribute("utenteEmail", user.getEmail());
  }



  /* controllo tipo utente*/

  Tipo tipoUtente;
  if(user != null)
    tipoUtente = (Tipo) user.getTipo();
  else
    tipoUtente = null;

  CorsoLaurea corsoLaurea = (CorsoLaurea) request.getAttribute("corsoLaurea");
  Resto resto = (Resto) request.getAttribute("resto");
  AnnoDidattico annoDidattico = (AnnoDidattico) request.getAttribute("anno");

  List<Lezione> lezioni = (List<Lezione>) request.getAttribute("lezioni");


%>
<html>
<head>
  <title>Orario UniClass</title>
  <script src="scripts/sidebar.js" type="text/javascript"></script>
  <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
  <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
  <link type="text/css" rel="stylesheet" href="styles/formcss.css"/>
  <link type="text/css" rel="stylesheet" href="styles/tableStyle.css">
  <link type="text/css" rel="stylesheet" href="styles/footerstyle.css">
  <link rel="icon" href="images/logois.png" sizes="32x32" type="image/png">
</head>
<body>

<% if(tipoUtente == null) { %>

<div class="barraNavigazione" id="barraNavigazione">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><img src="images/icons/menuOpenIcon.png" alt="closebtn"></a>
  <p>Menu<p>
  <ul id="menu">
    <li id="aule"><a href="aula.jsp">Aule</a>
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

<jsp:include page="header.jsp"/>



<br>
<h1>ORARIO: <%= corsoLaurea.getNome()%> <%=resto.getNome()%> <%=annoDidattico.getAnno()%></h1>
<br>
<div class="table-container">
  <table class="schedule-table">
    <tr>
      <th>Giorno</th>
      <th>9:00-9:30</th>
      <th>9:30-10:00</th>
      <th>10:00-10:30</th>
      <th>10:30-11:00</th>
      <th>11:00-11:30</th>
      <th>11:30-12:00</th>
      <th>12:00-12:30</th>
      <th>12:30-13:00</th>
      <th>13:00-13:30</th>
      <th>13:30-14:00</th>
      <th>14:00-14:30</th>
      <th>14:30-15:00</th>
      <th>15:00-15:30</th>
      <th>15:30-16:00</th>
      <th>16:00-16:30</th>
      <th>16:30-17:00</th>
      <th>17:00-17:30</th>
      <th>17:30-18:00</th>
    </tr>
    <%
      for (Giorno giorno : Giorno.values()) {
    %>
    <tr>
      <td class="highlight"><b><%= giorno.toString() %></b></td>
      <%
        int currentHour = 9 * 2; // Iniziamo da 9:00, considerando che 1 unità = 30 minuti (9 * 2)

        for (Lezione lezione : lezioni) {
          if (lezione.getGiorno().equals(giorno)) {
            int oraInizio = lezione.getOraInizio().toLocalTime().getHour() * 2 + lezione.getOraInizio().toLocalTime().getMinute() / 30;
            int oraFine = lezione.getOraFine().toLocalTime().getHour() * 2 + lezione.getOraFine().toLocalTime().getMinute() / 30;
            int durataOre = oraFine - oraInizio;

            // Aggiungi celle vuote fino all'ora di inizio della lezione
            while (currentHour < oraInizio) {
      %>
      <td></td>
      <%
          currentHour++;
        }
      %>
      <td colspan="<%= durataOre %>" class="subject-<%= lezione.getCorso().getNome().toLowerCase().replaceAll("\\s+", "-") %>">
        <%= lezione.getCorso().getNome() %><br><%= lezione.getDocenti().stream()
              .map(docente -> docente.getNome() + " " + docente.getCognome())
              .collect(Collectors.joining(", ")) %>
      </td>
      <%
            currentHour += durataOre;
          }
        }
        while (currentHour < 18 * 2) { // Raggiungi le 18:00 (considerando 1 unità = 30 min)
      %>
      <td></td>
      <%
          currentHour++;
        }
      %>
    </tr>
    <% } %>
  </table>
</div>
<br>
<br>
<br>
<%@include file = "footer.jsp" %>
</body>
</html>
