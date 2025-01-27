<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="it.unisa.uniclass.utenti.model.Utente, it.unisa.uniclass.utenti.model.Tipo" %>
<%@ page import="it.unisa.uniclass.orari.model.CorsoLaurea" %>
<%@ page import="it.unisa.uniclass.orari.model.Lezione" %>
<%@ page import="it.unisa.uniclass.orari.service.AulaService" %>
<%@ page import="it.unisa.uniclass.orari.model.Aula" %>
<%@ page import="it.unisa.uniclass.orari.service.LezioneService" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.TextStyle" %>
<%@ page import="java.time.LocalTime" %>

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

    List<CorsoLaurea> corsiLaurea = (List<CorsoLaurea>) request.getAttribute("corsi");
    List<Aula> aule = (List<Aula>) request.getAttribute("aule");
    String edificio = (String) request.getAttribute("ed");

%>
<html>
<head>
    <title>Edificio</title>
    <script src="scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/formcss.css"/>
    <link type="text/css" rel="stylesheet" href="styles/footerstyle.css">
    <link type="text/css" rel="stylesheet" href="styles/edificiostyle.css">
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
<br>

<h1> Edificio <%= edificio%> </h1>





<ul class="buildings">
    <%
        LocalTime oraCorrente = LocalTime.now();
        LocalDate oggi = LocalDate.now();
        String giornoCorrente = oggi.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALY);
        String giornoCorrenteMaiuscolo = giornoCorrente.replace("è", "e").replace("ì", "i").toUpperCase(Locale.ITALY);

        for (Aula aula : aule){
            boolean occ = false; // Reset for each classroom

            LezioneService lezioneService = new LezioneService();
            List<Lezione> lezioni = lezioneService.trovaLezioniAule(aula.getNome());
            lezioni.sort(Comparator.comparing(Lezione::getGiorno).thenComparing(Lezione::getOraInizio));

            // Check if the aula is occupied at the current time of day
            for(Lezione lezione : lezioni){
                LocalTime oraInizioLezione = lezione.getOraInizio().toLocalTime();
                LocalTime oraFineLezione = lezione.getOraFine().toLocalTime();

                if(giornoCorrenteMaiuscolo.equals(lezione.getGiorno().toString())){
                    if (oraCorrente.isAfter(oraInizioLezione) && oraCorrente.isBefore(oraFineLezione)) {

                        occ = true;
                        break; // Exit the loop if the room is occupied at the current time
                    }else{
                        break;
                    }
                }
            }

            // Output for each classroom
    %>
    <li class="building">
        <% if (occ) { %>
        <img class="imgOcc" src="images/icons/aulaOccupata.png">
        <% } else { %>
        <img class="imgOcc" src="images/icons/aulaLibera.png">
        <% } %>
        <%= aula.getNome() %>

        <ul class="classes">
            <%
                // Displaying the lessons for the classroom
                for (Lezione lezione : lezioni) {
                    if(lezione.getAula().getNome().equals(aula.getNome())){
            %>
            <li class="occupata">
                <%= lezione.getGiorno() %>
                <%= lezione.getOraInizio() %>
                <%= lezione.getOraFine() %>
                <%= lezione.getCorso().getNome() %>
                <%= lezione.getCorso().getAnnoDidattico().getAnno() %>
                <%= lezione.getResto().getNome() %>
            </li>
            <%
                    }
                }
            %>
        </ul>
    </li>
    <%
        } // End for each aula
    %>
</ul>




<script>
    document.querySelectorAll('.building').forEach(building => {
        building.addEventListener('click', () => {
            const classes = building.querySelector('.classes');
            classes.style.display = classes.style.display === 'none' ? 'block' : 'none';
        });
    });

    document.querySelectorAll('.class').forEach(classItem => {
        classItem.addEventListener('click', (event) => {
            event.stopPropagation();
            const timetables = classItem.querySelector('.timetables');
            timetables.style.display = timetables.style.display === 'none' ? 'block' : 'none';
        });
    });
</script>

<br>
<br>
<br>
<%@include file = "footer.jsp" %>
</body>
</html>

