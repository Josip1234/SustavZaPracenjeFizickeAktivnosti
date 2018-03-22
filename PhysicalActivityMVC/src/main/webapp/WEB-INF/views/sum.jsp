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

<a href="<c:url value="/registracija"/>">Registracija</a>
<a href="<c:url value="/sum"/>">Ukupno</a>

<a href="<c:url value="/mojprofil"/>">Profil</a>

</div>
<section>
<h2>Ukupno:</h2>




<div class="pregledUkupnog">



<table>
<thead>
<tr><td>
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


<td>${li.ukupanBrojKoraka}</td>

<td>${li.ukupnoVrijeme}</td>

<td>${li.prijedjeniKilometri } </td>
<td>${li.prosjecnaBrzina }</td> 
<td>${li.datum}</td>
</tr>
</c:forEach>
</table>


<section id="ukupnoBicikliranja">
<h2>Ukupno po bicikliranju</h2>
<table>
<thead>
<tr><td>
<b>Ukupna udaljenost:</b>

</td><td>
Ukupno vrijeme aktivnosti:

</td>
<td>
Prosjecna brzina
</td>

<td>
Datum i vrijeme
</td>
</tr>
</thead>
<c:forEach items="${ukupnoBicikliranja}" var="li">
<tr>
<td> ${li.ukupnaUdaljenost}</td>

<td>${li.ukupnoVrijemeAktivnosti}</td>

<td>${li.prosjecnaBrzinaUkm}</td>

<td>${li.period } </td>

</tr>
</c:forEach>
</table>




</section>

<section id="ukupnoTrcanje">
<h2>Ukupno trcanja</h2>
<table>
<thead>
<tr><td>
<b>Ukupna udaljenost:</b>

</td><td>
Ukupno vrijeme aktivnosti:

</td>
<td>
Prosjecna brzina
</td>

<td>
Datum i vrijeme
</td>
</tr>
</thead>
<c:forEach items="${ukupnoTrcanje}" var="li">
<tr>
<td> ${li.ukupnaUdaljenost}</td>

<td>${li.ukupnoVrijemeAktivnosti}</td>

<td>${li.prosjecnaBrzinaUkm}</td>

<td>${li.period } </td>

</tr>
</c:forEach>
</table>




</section>
<section id="ukupnoHodanja">
<h2>Ukupno hodanja:</h2>

<table>
<thead>
<tr><td>
<b>Ukupna udaljenost:</b>

</td><td>
Ukupno vrijeme aktivnosti:

</td>
<td>
Prosjecna brzina
</td>

<td>
Datum i vrijeme
</td>
<td>
Ukupan broj koraka
</td>
</tr>
</thead>
<c:forEach items="${ukupnoHodanja}" var="li">
<tr>
<td> ${li.ukupnaUdaljenost}</td>

<td>${li.ukupnoVrijemeAktivnosti}</td>

<td>${li.prosjecnaBrzinaUkm}</td>

<td>${li.period } </td>
<td>${li.ukupanBrojKoraka } </td>
</tr>
</c:forEach>
</table>





</section>
<section id="grafovi">
<section id="grafoviUkupnih">
<h2>Graf za prikaz ukupne udaljenosti</h2>
<p><a href="<c:url value='resources/LineChart.jpeg'/>">Aktivnost tijekom mjeseca</a></p>
<p>Graf na lijevoj strani sadrži prijeđeno vrijeme u minutama. Na x koordinati su prikazani dani. 
Graf prikazuje koliko je vremena korisnik bio aktivan tijekom pojedinog dana u mjesecu.</p>
<h2>Graf koji prikazuje ukupnu prijeđenu udaljenost </h2>
<p> <a href="<c:url value='resources/LineChart2.jpeg'/>">Prijeđena udaljenost po danu</a></p>
<p>Graf prikazuje broj prijeđenih kilometara po danu.</p>
<h2>Hostogram ukupne aktivnosti</h2>
<p> <a href="<c:url value='resources/Histogram.jpeg'/>">Ukupna aktivnost korisnika tijekom mjeseca i vremena 
trajanja aktivnosti</a></p>

</section>
</section>
<section id="filtriranje">
<h2>Filtiranje ukupnih rezultata po datumu</h2>
<form action="sum" method="post">

<label>Unesi prvi datum:</label>
<br>
<input type="date" name="datum1" />
<br>
<label>Unesi drugi datum:</label>
<br>
<input type="date" name="datum2" />
<br>

<input type="submit" value="Filtriraj">
</form>
</section>
</div>
<footer>

<a href="<c:url value="/home" />">Početna stranica</a> 
</footer>
<div id="odjava">
<form action="<c:url value="/logout"/>" method="post"><input type="hidden" name="_csrf" value="${_csrf.token}" /><button>Odjava</button></form>
</div>
</section>
</div>
</body>
</html>