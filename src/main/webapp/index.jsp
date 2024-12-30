<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <title>JSP - Hello World</title>
    <script src="scripts/sidebar.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="styles/headerStyle.css"/>
    <link type="text/css" rel="stylesheet" href="styles/barraNavigazioneStyle.css"/>
</head>
<body>



<div class="barraNavigazione" id="barraNavigazione">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><img src="images/icons/menuOpenIcon.png" alt="closebtn"></a>
		<p>Menu<p>
		<ul id="menu">
			<li id="orari"> <a href="servelt">Orari</a>
			</li>
			<li id="aule"><a href="servelt">Aule</a>
			</li>
			<li id="mappa"><a href="servlet">Mappa</a>
			</li>
			<li id="infoapp"><a href="info">Info App</a>
            </li>
			<li id="aboutus"><a href="aboutus.jsp">Chi Siamo</a>
			</li>
		</ul>
	</div>

  <jsp:include page="header.jsp"/>

<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>