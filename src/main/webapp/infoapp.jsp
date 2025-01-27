<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="it.unisa.uniclass.utenti.model.Utente, it.unisa.uniclass.utenti.model.Tipo" %>
<%@ page import="it.unisa.uniclass.orari.model.CorsoLaurea" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.uniclass.orari.service.dao.CorsoLaureaDAO" %>

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
<html lang="it">
<head>

     <title>Info App - UniClass</title>
    <script src="scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/aboutinfo.css"/>
	<link type="text/css" rel="stylesheet" href="styles/footerinfostyle.css">
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



        <section>
			<h1>Scopri UniClass: l'app che semplifica la tua vita universitaria</h1>
			<br>
            <p>UniClass è l’app perfetta per studenti e docenti che vogliono ottimizzare la gestione della propria giornata accademica. Grazie a questa piattaforma intuitiva, puoi accedere a tutte le informazioni necessarie in tempo reale, senza bisogno di cercarle su più applicazioni.</p>

            <h2>Funzionalità principali:</h2>
            <ul>
                <li><strong>Disponibilità aule in tempo reale:</strong> Visualizza le aule libere direttamente dalla piattaforma stessa.</li>
                <li><strong>Orari delle lezioni:</strong> Consulta facilmente gli orari delle tue lezioni e ottimizza il tuo piano di studio.</li>
                <li><strong>Calendario corsi:</strong> Organizza la tua settimana accademica con un semplice clic.</li>
                <li><strong>Comunicazione facile:</strong> Contatta i tuoi docenti e interagisci senza difficoltà.</li>
            </ul>

            <h2>Perché scegliere UniClass?</h2>
            <ul>
                <li><strong>Tutto in un'unica app:</strong> Non dovrai più usare applicazioni differenti per trovare le informazioni che ti servono.</li>
                <li><strong>Semplicità e velocità:</strong> Trova rapidamente aule, orari e aggiornamenti senza stress.</li>
                <li><strong>Interazione diretta:</strong> Comunica senza problemi con docenti e colleghi.</li>
            </ul>

            <h2>Un'esperienza universitaria semplificata</h2>
			<br>
            <p>Con UniClass, la tua vita universitaria diventa più fluida, meno caotica e decisamente più efficiente. Provala oggi stesso e scopri come semplificare ogni aspetto della tua giornata accademica!</p>
        </section>
<%@include file = "footer.jsp" %>
</body>
</html>
