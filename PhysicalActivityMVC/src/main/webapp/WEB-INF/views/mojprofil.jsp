<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Korisnikovi podaci</title>
</head>
<body>
<form action="mojprofil" method="POST">
<label>OIB korisnika:</label><br>
<input type="text" name="OIB" value="#{OIB}"><br>
<label>Ime korisnika:</label><br>
<input type="text" name="ime" value="#{ime}"><br>
<label>Prezime korisnika:</label><br>
<input type="text" name="prezime" value="#{prezime }"><br>
<label>Spol:</label><br>
<input type="text" name="spol" value="#{spol }" readonly="readonly"><br>
<label>Datum rođenja korisnika:</label>
<input type="text" name="datumr" value="#{datumr }" readonly="readonly"><br>   
<label>Email korisnika:</label><br>
<input type="email" name="email" value="#{email }" > <br>
<input type="password" name="sifra" value="#{sifra}"><br>
<input type="hidden" name="_csrf" value="${_csrf.token}" />
<input type="submit" value="Ažuriraj">
</form>
<div id="odjava">
<form action="<c:url value="/logout"/>" method="post"><input type="hidden" name="_csrf" value="${_csrf.token}" /><button>Odjava</button><form>
</div>
<a href="index">Povratak u izbornik</a>

</body>
</html>