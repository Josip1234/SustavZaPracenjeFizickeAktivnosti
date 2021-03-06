<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rezultati hodanja</title>	<link rel="stylesheet" href="resources/pad.css">
 <script src="resources/js.js" type="text/javascript"></script>
</head>
<body onLoad="sakrijHodanje()">
<div id="con">	
<header>
	<h1>
	Lista rezultata hodanja  
</h1>
</header>
<div id="meni">

<a href="<c:url value="/BMICalc" />">Indeks tjelesne mase</a>

<a href="<c:url value="/registracija"/>">Registracija</a>
<a href="<c:url value="/sum"/>">Ukupno</a>

<a href="<c:url value="/mojprofil"/>">Profil</a>

</div>
<div class="pregledHodanja">
<p><input type="button" value="Pokaži" onClick="pokaziHodanje()"></p>
<section id="hodanje">
<h2>Pregled rezultata hodanja:</h2>
<table>
<thead>
<tr><td>
<b>Udaljenost:</b>

</td><td>
Vrijeme aktivnosti:

</td>
<td>
Koraci:
</td>
<td>
Brzina u kilometrima:
</td>
<td>
Korisnik
</td>
<td>
Datum i vrijeme:
</td>
</tr>
</thead>
<c:forEach items="${hod}" var="li">
<tr>
<td> ${li.udaljenost}</td>

<td>${li.vrijemeAktivnosti}</td>

<td>${li.koraci}</td>

<td>${li.brzinaUkm } </td>
<td>${li.korisnik }</td> 
<td>${li.datumIvrijeme}</td>
</tr>
</c:forEach>
</table>
<p><input type="button" value="Sakrij" onClick="sakrijHodanje()"></p>
</section>
</div>
<a href="<c:url value="/home" />">Početna stranica</a> 
<div id="odjava">
<form action="<c:url value="/logout"/>" method="post"><input type="hidden" name="_csrf" value="${_csrf.token}" /><button>Odjava</button></form>
</div>
</div>
</body>
</html>