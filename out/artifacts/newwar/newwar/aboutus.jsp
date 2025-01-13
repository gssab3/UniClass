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
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - UniClass</title>
    <script src="scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
	<link type="text/css" rel="stylesheet" href="styles/aboutinfo.css">

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



        <section>
			<h1>Chi siamo</h1>
            <h2>La nostra missione</h2>
            <p>UniClass è un'applicazione digitale open-source pensata per migliorare l’esperienza universitaria di studenti e docenti. La nostra piattaforma rende facilmente accessibili tutte le informazioni quotidiane di cui hai bisogno, come gli orari delle lezioni, la disponibilità delle aule e la calendarizzazione dei corsi.</p>

            <h2>La nostra missione</h2>
            <p>UniClass non è solo un sistema per visualizzare informazioni, ma un ambiente che semplifica la comunicazione tra studenti e docenti. Vogliamo ridurre il bisogno di usare molteplici applicazioni e piattaforme, centralizzando tutte le informazioni in un unico strumento.</p>

            <h2>Perché scegliere UniClass?</h2>
            <ul>
                <li><strong>Gestione centralizzata:</strong> Trovi tutte le informazioni su orari, aule e corsi in un unico posto.</li>
                <li><strong>Comunicazione semplificata:</strong> Interagisci facilmente con i tuoi docenti e colleghi.</li>
                <li><strong>Migliore produttività:</strong> Risparmia tempo e riduci lo stress grazie a una piattaforma facile da usare.</li>
            </ul>

            <h2>La nostra visione</h2>
            <p>Vogliamo migliorare l'efficienza e la produttività degli studenti e del personale docente, rendendo la vita universitaria più organizzata e serena. Con UniClass, ogni attività è semplificata, per un'esperienza universitaria senza stress.</p>
        </section>

</body>
</html>
