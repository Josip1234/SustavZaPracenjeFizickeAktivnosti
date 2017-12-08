<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<head>

<title>Insert title here</title>
</head>
<body>
<h1>Register:</h1>

<form action="registracija" method="POST"  accept-charset="UTF-8" >
<input type="hidden"
name="${_csrf.parameterName}"
value="${_csrf.token}" />
OIB:<input type="text" name="OIB" min="11" maxlength="11" required="required"/><br/>
Ime:<input type="text" name="ime" required="required"/><br/>
Prezime:<input type="text" name="prezime" required="required"/><br/>
Spol:<select name="spol" required="required"><option value="m">Muško</option><option value="f">Žensko</option></select>
<br/>
Datum rođenja:<input type="date" name="datumr" required="required" /><br/>
Email:<input type="email" name="email" required="required"/><br/>
Šifra:<input type="password" name="sifra" required="required"/><br/>

<input type="submit" value="Registracija"/>
<p>${OIB}</p>
</form>
</body>
</html>