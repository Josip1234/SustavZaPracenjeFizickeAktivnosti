<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="pregledBicikliranja">
<c:forEach items="${bike}" var="li">
<p> ${li.vrijemeAktivnosti}
<br>
${li.brzinaUkm}
<br>
${li.lokacija}<br>
${li.longitude }<br>
${li.latitude } <br>
${li.korisnik }<br>
${li.udaljenost }
</p>
</c:forEach>
</div>


</body>
</html>