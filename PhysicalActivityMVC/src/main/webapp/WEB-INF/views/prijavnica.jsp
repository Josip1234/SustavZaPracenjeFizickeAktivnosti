<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %><%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html>
<head>
<link rel="stylesheet"
type="text/css"
href="<c:url value="/resources/pad.css" />">
<title>Prijava</title></head>
<body>
<form action="prijavnica" method="post">
<label>Email:</label>
<input type="email" name="email"/>
<label>Šifra:</label>
<input type="password" name="sifra"/>
<input type="submit" value="Prijava" />
</form>
<a href="<c:url value="/index" />">Izbornik</a> 
</body>