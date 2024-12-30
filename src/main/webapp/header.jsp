<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<script src="scripts/sidebar.js" type="text/javascript"></script>
</head>
<body>

	<header>
		<div class="TastoMenu" >
        	<span style="font-size:30px;cursor:pointer" onclick="openNav()">
        		<img src="${pageContext.request.contextPath}/images/icons/menuClosedIcon.png" alt="open">
        	</span>
        </div>

        <div class="ContentHeader">
        		<a href="${pageContext.request.contextPath}/index.jsp" style="cursor: pointer"><img alt="logo UniClass" src="${pageContext.request.contextPath}/images/logois.png"></a>
        </div>

	</header>

</body>
</html>