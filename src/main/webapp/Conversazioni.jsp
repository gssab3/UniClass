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

  Tipo tipoUtente = null; // Inizializzazione predefinita
  if (user != null) {
    tipoUtente = user.getTipo();

    if (tipoUtente == Tipo.PersonaleTA) {
      response.sendRedirect("ErroreAccesso.jsp");
      return; // Interrompi l'esecuzione della pagina JSP
    }
  } else {
    response.sendRedirect("Login.jsp");
    return; // Interrompi l'esecuzione della pagina JSP
  }

  Accademico accademicoSelf = (Accademico) request.getAttribute("accademicoSelf");
  List<Accademico> accademici = (List<Accademico>) request.getAttribute("accademici");

  if (tipoUtente == Tipo.Docente || tipoUtente == Tipo.Studente || tipoUtente == Tipo.Coordinatore) {
    List<Conversazione> conversazioni = (List<Conversazione>) request.getAttribute("conversazioni");
  }
%>


<!DOCTYPE html>
<html>

<head>
  <title>UniClass Chat</title>
  <script src="scripts/sidebar.js" type="text/javascript"></script>
  <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
  <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
  <link type="text/css" rel="stylesheet" href="styles/conversazioniStyle.css">
  <link type="text/css" rel="stylesheet" href="styles/newChat.css">

</head>
<body id="uniClassChat">

  <% if(tipoUtente.equals(Tipo.Studente)) { %>

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
      <li id="aule"><a href="servelt">Aule</a>
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

  <% } %>

<jsp:include page="header.jsp"/>

//fare retrieve delle conversazioni dell'utente'







  <div class="mega-container">
    <h1>Conversazioni</h1>
      <div class="conversations-container">
        <% for(Accademico accademico: accademici){ %>
            <a href="chatServlet?accademico=<%=accademico.getEmail()%>&accademicoSelf=<%=accademicoSelf.getEmail()%>" class="conversation">
            <%    if(accademico.getTipo().equals(Tipo.Studente)){ %>
                <div class="profile-picture">
                  <img src="images/icons/iconstudent.png" alt="Foto profilo">
                </div>
            <%   } else if (accademico.getTipo().equals(Tipo.Docente) || accademico.getTipo().equals(Tipo.Coordinatore)) { %>
                <div class="profile-picture">
                  <img src="images/icons/iconprof.png" alt="Foto profilo">
                </div>
            <%
            }
            %>
            <div class="username"><%=accademico.getNome()%> <%=accademico.getCognome()%></div>
            </a>
        <% } %>
      </div>
  </div>


  <h1>Crea una nuova Chat</h1>

  <div class="form-container">
    <form id="myForm" method="post" class="chat-form">
      <label for="email" class="form-label">Seleziona un'email:</label>
      <select id="email" name="email" class="form-select">
        <!-- Le opzioni delle email verranno caricate tramite AJAX -->
      </select>

      <br><br>

      <label for="testo" class="form-label">Testo del messaggio:</label>
      <textarea id="testo" name="testo" class="form-textarea" rows="5" cols="40"></textarea>

      <br><br>

      <button type="submit" class="form-button">Invia</button>
    </form>
  </div>

  <script src="scripts/formChat.js" defer></script>

</body>
</html>