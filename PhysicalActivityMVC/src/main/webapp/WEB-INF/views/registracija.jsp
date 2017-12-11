<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html>
<head>
<title>Registracija korisnika</title>
<link rel="stylesheet"type="text/css" href="<c:url value="/resources/pad.css" />">
</head>
<body>
<h1>Register:</h1>
<sf:form method="POST" modelAttribute="registration">

OIB:<sf:input path="OIB"/><br>
Ime:<sf:input path="ime"/><br>
Prezime:<sf:input path="prezime"/><br>
Spol:<sf:select path="spol"><sf:option value="m">Muško</sf:option><sf:option value="f">Žensko</sf:option></sf:select>
<br/>
Datum rođenja:<sf:input path="datumr" type="date" /><br>
Email:<sf:input path="email" type="email" /><br>
Šifra:<sf:input path="sifra"/>
<input type="submit" value="Registracija"/>
</sf:form>

</body>
</html>