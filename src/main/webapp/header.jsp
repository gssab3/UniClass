<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="it.unisa.uniclass.utenti.model.Utente, it.unisa.uniclass.utenti.model.Tipo" %>

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
<meta charset="UTF-8">
<title>Header</title>
<script src="scripts/sidebar.js" type="text/javascript"></script>
</head>
<body>

	<header>
		<div class="TastoMenu">
        	<span style="font-size:50px;cursor:pointer" onclick="openNav()">
        		<img src="${pageContext.request.contextPath}/images/icons/menuClosedIcon.png" alt="open">
        	</span>
        </div>

        <div class="ContentHeader">
        		<a href="${pageContext.request.contextPath}/Home" style="cursor: pointer"><img alt="logo UniClass" src="${pageContext.request.contextPath}/images/logois.png"></a>
        </div>

        <% if (tipoUtente == null){ %>

        <div class="TastoLogin" >
            <span style="font-size:30px;cursor:pointer">
                <a href="Login.jsp">
                    <img src="${pageContext.request.contextPath}/images/icons/usericonnolog.png" alt="open">
                </a>
            </span>
        </div>

        <% } else if(tipoUtente != null) { %>
        <div class="TastoLogin" >
            <span style="font-size:30px;cursor:pointer">
                <a href="Account.jsp">
                    <img src="${pageContext.request.contextPath}/images/icons/usericonlog.png" alt="open">
                </a>
            </span>
        </div>
        <% } %>


	</header>

</body>
</html>