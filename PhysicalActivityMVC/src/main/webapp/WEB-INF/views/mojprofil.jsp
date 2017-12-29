<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Korisnikovi podaci</title>
</head>
<body>

<c:out value="${korisnik.OIB}" /><br>
<c:out value="${korisnik.ime}" /><br>
<c:out value="${korisnik.prezime}" /><br>
<c:out value="${korisnik.spol}" /><br>
<c:out value="${korisnik.datumr}" /><br>
<c:out value="${korisnik.email}" /><br>
<c:out value="${korisnik.sifra}" /><br>

<div id="odjava">
<form action="<c:url value="/logout"/>" method="post"><input type="hidden" name="_csrf" value="${_csrf.token}" /><button>Odjava</button><form>
</div>
<a href="index">Povratak u izbornik</a>

</body>
</html>