<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="BMICalc" method="post">
<p>Visina osobe:</p><br>
<input type="text" name="visina_osobe_u_metrima">
<br>
<p>Masa u kg:</p>
<input type="text" name="masa_u_kg">
<br>
<input type="hidden" name="_csrf" value="${_csrf.token}" />
<input type="submit" value="Izrčunaj">
</form><br>
<p>Rezultat: ${rezultat}</p>
<br>
<a href="<c:url value="/index" />">Izbornik</a> 
</body>
</html>