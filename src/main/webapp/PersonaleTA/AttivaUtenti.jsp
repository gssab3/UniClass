<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="it.unisa.uniclass.utenti.model.Utente, it.unisa.uniclass.utenti.model.Tipo" %>
<%@ page import="it.unisa.uniclass.orari.model.CorsoLaurea" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.uniclass.utenti.model.Accademico" %>

<%
    /* Sessione HTTP */
    HttpSession sessione = request.getSession(true);
    Utente user = (Utente) sessione.getAttribute("currentSessionUser");

    /* controllo tipo utente*/

    Tipo tipoUtente;
    if(user != null) {
        tipoUtente = (Tipo) user.getTipo();
        if(!tipoUtente.equals(Tipo.PersonaleTA)) {
            response.sendRedirect("ErroreAccesso.jsp");
        }
    }
    else
        response.sendRedirect(request.getContextPath() + "/Login.jsp");

    List<Accademico> accademiciNonAttivati = (List<Accademico>) request.getAttribute("accademiciNonAttivati");

%>
<!DOCTYPE html>
<html>

<head>
    <title>UniClass</title>
    <script src="/UniClass/scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="../styles/headerStyle.css"/>
    <link type="text/css" rel="stylesheet" href="../styles/barraNavigazioneStyle.css" />
    <link type="text/css" rel="stylesheet" href="../styles/uniClassAdd.css" />
    <link rel="icon" href="images/logois.png" sizes="32x32" type="image/png">
    <script src="${pageContext.request.contextPath}/scripts/trovaNonAttivati.js" defer></script>
</head>
<body id="uniClassAdd">


<div class="barraNavigazione" id="barraNavigazione">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><img src="${pageContext.request.contextPath}/images/icons/menuOpenIcon.png" alt="closebtn"></a>
    <p>Menu<p>
    <ul id="menu">
        <li id="aule"><a href="${pageContext.request.contextPath}/aula.jsp">Aule</a>
        </li>
        <li id="appelli"><a href="servelt">Appelli</a>
        </li>
        <li id="gutenti"><a href="AttivaUtenti.jsp">Gestione Utenti</a>
        </li>
        <li id="mappa"><a href="${pageContext.request.contextPath}/mappa.jsp">Mappa</a>
        </li>
        <li id="ChatBot"><a href="${pageContext.request.contextPath}/ChatBot.jsp">ChatBot</a>
        </li>
        <li id="infoapp"><a href="${pageContext.request.contextPath}/infoapp.jsp">Info App</a>
        </li>
        <li id="aboutus"><a href="${pageContext.request.contextPath}/aboutus.jsp">Chi Siamo</a>
        </li>
    </ul>
</div>





    <jsp:include page="../header.jsp"/>

<div class="container">
    <% if(request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("error") ){ %>
        <div class="tableRow">
            <p class="error">email o password errati!</p>
        </div>
    <% } %>

    <div class="left-block">
        <h2>Lista Utenti non Attivati</h2>
        <div class="list">

        </div>
    </div>

    <div class="center-block">
        <h2>Attivazione Utente</h2>
        <br>
        <form action="${pageContext.request.contextPath}/AttivaUtentiServlet?param=add" method="POST">
            <label for="matricola">Matricola:</label>
            <input type="text" id="matricola" name="matricola" required><br><br>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br><br>

            <label for="tipo">Tipo:</label>
            <select id="tipo" name="tipo" required>
                <option value="Studente">Studente</option>
                <option value="Docente">Docente</option>
                <option value="Coordinatore">Coordinatore</option>
                <!-- <option value="PersonaleTA">PersonaleTA</option> -->
            </select><br><br>

            <input type="submit" class="submit-btn" value="Invia">
        </form>
    </div>

    <div class ="right-block">
        <h2>Disattivazione Utente</h2>
        <br>
        <form action="${pageContext.request.contextPath}/AttivaUtentiServlet?param=remove" method="POST">

            <label for="email-remove">Email:</label>
            <select id="email-remove" name="email-remove" required>
                <option value="">-- Seleziona un'email --</option>

            </select><br><br>

            <input type="submit" class="cancel-btn" value="Invia">
        </form>
    </div>

</div>




</body>
</html>