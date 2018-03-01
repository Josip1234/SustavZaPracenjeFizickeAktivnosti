<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BMI kalkulator</title>
	<link rel="stylesheet" href="resources/pad.css">

</head>
<body>
<div id="con">	
<header>
	<h1>
	Kalkulator za unos tjelesnog indeksa  
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
<h2>Unos u kalkulator:</h2>
<form action="BMICalc" method="post">
<p>Visina osobe:</p><br>

<input type="text" name="visina_osobe_u_metrima" required="required">
<br>
<p>Masa u kg:</p><br>

<input type="text" name="masa_u_kg" required="required">
<br>
<input type="hidden" name="_csrf" value="${_csrf.token}" />
<br>
<input type="submit" value="Izračunaj">
</form><br>
<p>Rezultat: ${rezultat}</p>
<br>
<a href="<c:url value="/home" />">Početna stranica</a> 
</section>
</div>
</body>
</html>