<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" isELIgnored="false"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
	<link rel="stylesheet" href="resources/pad.css">

<title>Korisnikovi podaci</title>
</head>
<body>
<div id="con">	
<header>
	<h1>
	Korisnički profil  
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
<h2>Podaci korisnika:</h2>
<div id="userCredentials">

<h2>Korisnički podaci</h2>
<c:out value="${korisnik.OIB}"  />

<br>
<c:out value="${korisnik.ime}" />

<br>
<c:out value="${korisnik.prezime}" />

<br>
<c:out value="${korisnik.spol}" /><br>
<c:out value="${korisnik.datumr}" /><br>
<c:out value="${korisnik.email}" />

<br>
<c:out value="${korisnik.sifra}" />



</div>
<div id="forma">
<form action="mojprofil" method="post">
<h2>Ažuriranje profila</h2>
<label>Oib:</label>
<input type="text" name="OIB">
<br>
<label>Ime:</label>
<input type="text" name="ime">
<br>
<label>Prezime:</label>
<input type="text" name="prezime">
<br>
<label>Email:</label>
<input type="text" name="email">
<br>
<label>Sifra:</label>
<input type="password" name="sifra"><br>
<input type="hidden" name="_csrf" value="${_csrf.token}" />
<input type="submit" value="Ažuriraj">
</form>
</div>
<div id="forma">
<form action="home" method="post">
<h2>Obriši profil</h2>


<input type="hidden" name="_csrf" value="${_csrf.token}" />
<input type="submit" value="Obriši">
</form>
</div>


<div id="odjava">
<form action="<c:url value="/logout"/>" method="post"><input type="hidden" name="_csrf" value="${_csrf.token}" /><button>Odjava</button></form>
</div>
<a href="<c:url value="/home"/>">Početna stranica</a>
</section>
</div>
</body>
</html>