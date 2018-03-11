<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rezultati biciklističkih aktivnosti</title>
	<link rel="stylesheet" href="resources/pad.css">
    <script src="resources/js.js" type="text/javascript"></script>
</head>
<body onLoad="sakrijRezultateBicikliranja()">

<div id="con">	
<header>
	<h1>
	Rezultati biciklističkih aktivnosti 
</h1>
</header>
<div id="meni">

<a href="<c:url value="/BMICalc" />">Indeks tjelesne mase</a>
<a href="<c:url value="/bikingactivity" />">Bicikliranje</a>
<a href="<c:url value="/runningactivity" />">Trčanje</a>
<a href="<c:url value="/registracija"/>">Registracija</a>
<a href="<c:url value="/sum"/>">Ukupno</a>
<a href="<c:url value="/walking"/>">Hodanje</a>
<a href="<c:url value="/mojprofil"/>">Profil</a>

</div>
<section>
<h2>Rezultati:</h2>
<input type="button" id="gumb" value="pokaziRezultate" onClick="pokaziRezultate()"><br>
<div class="pregledBicikliranja" id="rezultati">
<c:forEach items="${bike}" var="li">
<p> ${li.vrijemeAktivnosti}
<br>
${li.brzinaUkm}
<br>
${li.korisnik }<br>
${li.udaljenost }
</p>
</c:forEach>
<input type="button" id="gumb2" value="sakrijRezultate" onClick="sakrijRezultateBicikliranja()">
</div><br>
<a href="<c:url value="/home" />">Početna stranica</a> 
<div id="odjava">
<form action="<c:url value="/logout"/>" method="post"><input type="hidden" name="_csrf" value="${_csrf.token}" /><button>Odjava</button></form>
</div>
</section>
</div>
</body>
</html>