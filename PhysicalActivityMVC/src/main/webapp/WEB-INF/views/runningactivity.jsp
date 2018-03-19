<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Trčanje</title>
	<link rel="stylesheet" href="resources/pad.css">

</head>
<body>
<div id="con">	
<header>
	<h1>
	Rezultati trčanja:  
</h1>
</header>
<div id="meni">

<a href="<c:url value="/BMICalc" />">Indeks tjelesne mase</a>

<a href="<c:url value="/registracija"/>">Registracija</a>
<a href="<c:url value="/sum"/>">Ukupno</a>

<a href="<c:url value="/mojprofil"/>">Profil</a>

</div>
<section>
<h2>Lista rezultata::</h2>
<div class="pregledBicikliranja">
<h2>Pregled rezultata hodanja:</h2>
<table>
<thead>
<tr><td>
<b>Vrijeme aktivnosti::</b>

</td><td>
Brzina u kilometrima::

</td>
<td>
Korisnik:
</td>
<td>
Udaljenost:
</td>
<td>Datum:</td>
</tr>
</thead>
<c:forEach items="${run}" var="li">
<tr>


<td>${li.vrijemeAktivnosti}</td>

<td>${li.brzinaUkm}</td>

<td>${li.korisnik }</td> 
<td>${li.udaljenost}</td>
<td>${li.datum}</td>
</tr>
</c:forEach>
</table>

</div>
<a href="<c:url value="/home" />">Početna stranica</a> 
<div id="odjava">
<form action="<c:url value="/logout"/>" method="post"><input type="hidden" name="_csrf" value="${_csrf.token}" /><button>Odjava</button></form>
</div>
</section>
</div>
</body>
</html>