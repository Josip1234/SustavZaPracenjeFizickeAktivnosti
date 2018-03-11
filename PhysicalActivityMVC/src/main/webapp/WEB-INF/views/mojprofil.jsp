<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" isELIgnored="false"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
	<link rel="stylesheet" href="resources/pad.css">
	    <script src="resources/js.js" type="text/javascript"></script>
	

<title>Korisnikovi podaci</title>
</head>
<body onLoad="sakrijKorisnickePodatke(),sakrijUpdateProfila()">
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
<p><input type="button" id="pokazi" value="Pokaži" onClick="pokaziKorisnickePodatke()"></p>
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


<p><input type="button" id="sakrij" value="Sakrij" onClick="sakrijKorisnickePodatke()"></p>

</div>
<p><input type="button" id="update" value="Ažuriraj profil" onClick="pokaziUpdateProfila()"></p>
<div id="forma">
<form action="mojprofil" method="post">
<h2>Ažuriranje profila</h2>
<label>Oib:</label>
<br>
<input type="text" name="OIB" value="<c:out value="${korisnik.OIB}"  />">
<br>
<label>Ime:</label>
<br>
<input type="text" name="ime" value="<c:out value="${korisnik.ime}" />">
<br>
<label>Prezime:</label>
<br>
<input type="text" name="prezime" value="<c:out value="${korisnik.prezime}" />">
<br>
<label>Email:</label>
<br>
<input type="text" name="email" value="<c:out value="${korisnik.email}" />">
<br>
<label>Sifra:</label>
<br>
<input type="password" name="sifra" value="<c:out value="${korisnik.sifra}" />"><br>
<input type="hidden" name="_csrf" value="${_csrf.token}" />
<input type="submit" value="Ažuriraj">
</form>
<p><input type="button" id="SakrijUp" value="Sakrij ažuriranje" onClick="sakrijUpdateProfila()"></p>
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