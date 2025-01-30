<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="it.unisa.uniclass.utenti.model.Utente, it.unisa.uniclass.utenti.model.Tipo" %>


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
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>UniClass ChatBot</title>
    <script src="scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css" />
    <link type="text/css" rel="stylesheet" href="styles/chatbot.css"/>
	<link type="text/css" rel="stylesheet" href="styles/footerstyle.css">
	<link rel="icon" href="images/logois.png" sizes="32x32" type="image/png">
	<script src="scripts/chatBotJS.js"></script>

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

	<div id="chatContainer">
		<h1>Chat Bot</h1>
		<div id="messages"></div>
		<input type="text" id="userMessage" placeholder="Scrivi un messaggio..." />
		<button onclick="sendMessage()">Invia</button>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>
