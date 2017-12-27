<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %><%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet"
type="text/css"
href="<c:url value="/resources/pad.css" />">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>PhysicalActivity homepage</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/pad.css"/>">
</head>
<body>
<h1>Dobrodošli na početnu stranicu</h1>
<a href="<c:url value="/home" />">Početna stranica</a> |
<a href="<c:url value="/BMICalc" />">Indeks tjelesne mase</a>
<a href="<c:url value="/bikingactivity" />">Bicikliranje</a>
<a href="<c:url value="/mojprofil" />">Profil korisnika</a>
<a href="<c:url value="/runningactivity" />">Trčanje</a>
<a href="<c:url value="/registracija"/>">Registracija</a>
<a href="<c:url value="/sum"/>">Ukupno</a>
</body>
</html>