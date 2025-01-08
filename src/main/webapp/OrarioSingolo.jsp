<%@ page import="it.unisa.uniclass.utenti.model.Utente" %>
<%@ page import="it.unisa.uniclass.utenti.model.Tipo" %>
<%@ page import="it.unisa.uniclass.orari.model.CorsoLaurea" %>
<%@ page import="java.util.List" %><%--
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




<h1>ORARIO //stampare il corso e il resto scelti</h1>
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
    <td class="highlight"><b>Lunedì</b></td>
      <%for ()%>
    <td>Eng</td>
    <td>Mat</td>
    <td>Che</td>
    <td rowspan="6" class="special"><b>LUNCH</b></td>
    <td colspan="3" class="special">LAB</td>
    <td>Phy</td>
  </tr>
  <tr>
    <td class="highlight"><b>Martedì</b></td>
    <td colspan="3" class="special">LAB</td>
    <td>Eng</td>
    <td>Che</td>
    <td>Mat</td>
    <td class="special">SPORTS</td>
  </tr>
  <tr>
    <td class="highlight"><b>Mercoledì</b></td>
    <td>Mat</td>
    <td>Phy</td>
    <td>Eng</td>
    <td>Che</td>
    <td colspan="3">LIBRARY</td>
  </tr>
  <tr>
    <td class="highlight"><b>Giovedì</b></td>
    <td>Phy</td>
    <td>Eng</td>
    <td>Che</td>
    <td colspan="3" class="special">LAB</td>
    <td>Mat</td>
  </tr>
  <tr>
    <td class="highlight"><b>Venerdì</b></td>
    <td colspan="3" class="special">LAB</td>
    <td>Mat</td>
    <td>Che</td>
    <td>Eng</td>
    <td>Phy</td>
  </tr>

</table>


</body>
</html>
