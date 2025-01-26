<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="it.unisa.uniclass.utenti.model.Utente, it.unisa.uniclass.utenti.model.Tipo" %>
<%@ page import="it.unisa.uniclass.orari.model.CorsoLaurea" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.uniclass.orari.service.dao.CorsoLaureaDAO" %>

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
<!DOCTYPE html>
<html>

<head>
    <title>JSP - Hello World</title>
    <script src="scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/mappa.css"/>
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
		<li id="aule"><a href="aula.jsp">Aule</a>
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

    <iframe
        class="map"
        id= "border"
        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d6043.0962216837215!2d14.786334038892628!3d40.77196272409155!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x133bc5c7456b88bd%3A0x80bab96149d2993d!2sUniversit%C3%A0%20degli%20Studi%20di%20Salerno!5e0!3m2!1sit!2sit!4v1735658813332!5m2!1sit!2sit"
        width="1000"
        height="700"
        allowfullscreen=""
        loading="lazy"
        referrerpolicy="no-referrer-when-downgrade">
    </iframe>

<%@include file = "footer.jsp" %>
</body>
</html>