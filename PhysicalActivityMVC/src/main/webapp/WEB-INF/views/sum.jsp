<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ukupno</title>
</head>
<body>
<div class="pregledUkupnog">
<c:forEach items="${sum}" var="li">
<p> ${li.korisnik}
<br>
${li.ukupanBrojKoraka}
<br>
${li.ukupnoVrijeme}<br>
${li.prijedjeniKilometri }<br>
${li.prosjecnaBrzina } <br>

</p>
</c:forEach>
</div>
<a href="<c:url value="/index" />">Izbornik</a> 
<div id="odjava">
<form action="<c:url value="/logout"/>" method="post"><input type="hidden" name="_csrf" value="${_csrf.token}" /><button>Odjava</button><form>
</div>
</body>
</html>