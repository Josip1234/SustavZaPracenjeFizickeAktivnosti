<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ukupno</title>
	<link rel="stylesheet" href="resources/pad.css">

</head>
<body>
<div id="con">	
<header>
	<h1>
	Ukupni rezultati  
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
<h2>Ukupno:</h2>
<div class="pregledUkupnog">



<table>
<thead>
<tr><td>
<b>Korisnik:</b>

</td><td>
Ukupan broj koraka::

</td>
<td>
Ukupno vrijeme:
</td>
<td>
Prijedjeni kilometri:
</td>
<td>
Prosjecna brzina:
</td>
<td>
Datum i vrijeme:
</td>
</tr>
</thead>
<c:forEach items="${sum}" var="li">
<tr>
<td> ${li.korisnik}</td>

<td>${li.ukupanBrojKoraka}</td>

<td>${li.ukupnoVrijeme}</td>

<td>${li.prijedjeniKilometri } </td>
<td>${li.prosjecnaBrzina }</td> 
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