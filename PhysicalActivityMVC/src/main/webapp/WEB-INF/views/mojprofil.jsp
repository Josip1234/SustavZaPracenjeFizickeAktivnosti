<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" isELIgnored="false"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<title>Korisnikovi podaci</title>
</head>
<body>
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
<form action="index" method="post">
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


<div id="odjava">
<form action="<c:url value="/logout"/>" method="post"><input type="hidden" name="_csrf" value="${_csrf.token}" /><button>Odjava</button><form>
</div>
<a href="<c:url value="/index"/>">Povratak u izbornik</a>

</body>
</html>