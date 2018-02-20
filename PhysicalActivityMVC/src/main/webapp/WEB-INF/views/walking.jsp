<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="pregledHodanja">
<c:forEach items="${hod}" var="li">
<p> ${li.udaljenost}
<br>
${li.vrijemeAktivnosti}
<br>
${li.koraci}<br>
${li.adresa }<br>
${li.longitude } <br>
${li.latitude } <br>
${li.brzinaUkm } <br>
${li.korisnik } <br>
</p>
</c:forEach>
</div>
<a href="<c:url value="/home" />">Početna stranica</a> 
<div id="odjava">
<form action="<c:url value="/logout"/>" method="post"><input type="hidden" name="_csrf" value="${_csrf.token}" /><button>Odjava</button><form>
</div>
</body>
</html>