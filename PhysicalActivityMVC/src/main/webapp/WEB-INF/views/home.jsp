<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Početna stranica</title>
</head>
<body>
<h1>
	Dobrodošli  
</h1>

<p> Dobrodošli na stranicu preko koje će se ubacivati podaci prikupljeni preko mobilne aplikacije u realnom vremenu. Kako koristiti aplikaciju? </p>
<p>Prvo, korisnik se mora prijaviti preko web aplikacije. Zatim se mora logirati. Potrebno se je logirati i preko weba, za korištenje web aplikacije,
ali i preko mobitela za korištenje mobilne aplikacije. Kada se korisnik prijavljuje preko mobitela, njegovi podaci o prijavi se 
šalju na server. Server zatim vraća autorizaciju u obliku dali se taj korisnik nalazi u bazi podataka registriranih korisnika. Ako se korisnik nalazi
u bazi registriranih korisnika, vratiti će se true, ako ne vratiti će se false i nekakva poruka da se korisnik treba registrirati.</p>
<a href="<c:url value="/index" />">Izbornik</a> 

</body>
</html>