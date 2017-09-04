<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Korisnikovi podaci</title>
</head>
<body>
<ul>
<li>OIB:<b>${lista0}</b></li>
<li>Ime:<b>${lista1}</b></li>
<li>Prezime:<b>${lista2}</b></li>
<li>Spol:<b>${lista3}</b></li>
<li>Datum rođenja:<b>${lista4}</b></li>
<li>Email:<b>${lista5}</b></li>
<li>Šifra:<b>${lista6}</b></li>
</ul>
<a href="index">Povratak u izbornik</a>

</body>
</html>