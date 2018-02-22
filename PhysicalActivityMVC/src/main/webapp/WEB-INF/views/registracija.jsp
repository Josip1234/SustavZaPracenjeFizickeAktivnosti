<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html>
<html>
<head>
<title>Registracija korisnika</title>
<link rel="stylesheet"type="text/css" href="<c:url value="/resources/pad.css" />">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<div id="con">	
<header>
	<h1>
	Registriraj se 
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
<h2>Registracija korisnika:</h2>

<sf:form method="POST" modelAttribute="registration">
<sf:errors path="*" element="div" cssClass="errors" />
<sf:label path="OIB"
cssErrorClass="error">OIB</sf:label>:
<sf:input path="OIB"/><br>
<sf:label path="ime"
cssErrorClass="error">Ime</sf:label>:
<sf:input path="ime"/><br>
<sf:label path="prezime"
cssErrorClass="error">Prezime</sf:label>:
<sf:input path="prezime"/><br>
<sf:label path="spol"
cssErrorClass="error">Spol</sf:label>:
<sf:select path="spol"><sf:option value="m">Muško</sf:option><sf:option value="f">Žensko</sf:option></sf:select>

<br>
<sf:label path="datumr"
cssErrorClass="error">Datum rođenja</sf:label>:
<sf:input path="datumr" type="date" />
<br>
<sf:label path="email"
cssErrorClass="error">E-mail adresa</sf:label>:
<sf:input path="email" /><br>
<sf:label path="sifra"
cssErrorClass="error">Šifra</sf:label>:
<sf:password path="sifra"/>
<input type="submit" value="Registracija"/>
</sf:form>
<a href="<c:url value="/home" />">Početna stranica</a> 
</section>
</div>
</body>
</html>