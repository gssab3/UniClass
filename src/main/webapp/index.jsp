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


<!DOCTYPE html>
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


	<br> <br>
	<div id="contieniForm">
		<form id="cercaOrarioForm" action="cercaOrario" method="POST">
        <!-- Selezione corso di laurea -->
            <label for="corsoLaurea">Corso di Laurea:</label>
            <select id="corsoLaurea" name="corsoLaurea" onchange="aggiornaResto()" required>
                <option value="">-- Seleziona un corso --</option>
                <%
                        for (CorsoLaurea cors : corsiLaurea) {
							String corso = cors.getNome();
                %>
                <option value="<%= corso %>"><%= corso %></option>
                <%
                        }
                %>
        </select>

        <!-- Selezione resto (aggiornato via AJAX) -->
        <label for="resto">Resto:</label>
        <select id="resto" name="resto" required>
            <option value="">-- Seleziona un resto --</option>
            <!-- Le opzioni dei resti cambieranno dinamicamente in base alla scelta del corso -->
        </select>

        <!-- Selezione anno (aggiornato via AJAX) -->
        <label for="anno">Anno:</label>
        <select id="anno" name="anno" required>
            <option value="">-- Seleziona un anno --</option>
            <!-- Le opzioni degli anni cambieranno dinamicamente in base alla scelta del corso -->
        </select>

        <button type="submit">Cerca Orario</button>
    </form>
	</div>

<script src="scripts/formOrario.js"></script>


<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>