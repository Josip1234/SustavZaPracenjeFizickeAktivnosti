<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Register:</h1>
<form action="registracija" method="POST">
OIB:<input type="text" name="OIB"/><br/>
Ime:<input type="text" name="ime"/><br/>
Prezime:<input type="text" name="prezime"/><br/>
Spol:<select name="spol"><option value="m">Muško</option><option value="f">Žensko</option></select>
<br/>
Datum rođenja:<input type="date" name="datumr"/><br/>
Email:<input type="email" name="email"/><br/>
Šifra:<input type="password" name="sifra"/><br/>

<input type="submit" value="Registracija"/>
<p>${OIB}</p>
</form>
</body>
</html>