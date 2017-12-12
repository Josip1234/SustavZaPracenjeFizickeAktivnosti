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
<sf:input path="email"  /><br>
<sf:label path="sifra"
cssErrorClass="error">Šifra</sf:label>:
<sf:password path="sifra"/>
<input type="submit" value="Registracija"/>
</sf:form>

</body>
</html>