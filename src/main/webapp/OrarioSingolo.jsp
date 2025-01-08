<%@ page import="it.unisa.uniclass.utenti.model.Utente" %>
<%@ page import="it.unisa.uniclass.utenti.model.Tipo" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.uniclass.orari.model.*" %>
<%@ page import="java.sql.Time" %><%--
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
  <title>UniClass</title>
  <script src="scripts/sidebar.js" type="text/javascript"></script>
  <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
  <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
  <link type="text/css" rel="stylesheet" href="styles/formcss.css"/>

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
    <li id="orari"> <a href="servelt">Orari</a>
    </li>
    <li id="aule"><a href="servelt">Aule</a>
    </li>
    <li id="agenda"><a href="servelt">Agenda</a>
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




<h1>ORARIO: <%= corsoLaurea.getNome()%> <%=resto.getNome()%> <%=annoDidattico.getAnno()%></h1>
<table>
  <tr>
    <th>Giorno</th>
    <th>I<br>9:00-10:00</th>
    <th>II<br>10:00-11:00</th>
    <th>III<br>11:00-12:00</th>
    <th>12:00-13:00</th>
    <th>IV<br>13:00-14:00</th>
    <th>V<br>14:00-15:00</th>
    <th>VI<br>15:00-16:00</th>
    <th>VII<br>16:00-17:00</th>
    <th>VIII<br>17:00-18:00</th>
  </tr>
  <tr>
    <% for (Giorno giorno : Giorno.values()) { %>

    <td class="highlight"><b><%= giorno.toString() %></b></td>

    <% for (Lezione lezione : lezioni) {
      if (lezione.getGiorno().equals(giorno)) {
        for (int i = 9; i <= 17; i++) {
          if (lezione.getOraInizio().equals(Time.valueOf(i + ":00:00"))) {
            long durata = lezione.getOraFine().getTime() - lezione.getOraInizio().getTime();
            long durataSecondi = durata / 1000;
            long durataMinuti = durataSecondi / 60;
            long durataOre = durataMinuti / 60;
    %>
    <td colspan="<%= durataOre %>"><%= lezione.getCorso().getNome() %></td>
    <%
            }
          }
        }
      } %>

    <% } %>
  </tr>
</table>


</body>
</html>
