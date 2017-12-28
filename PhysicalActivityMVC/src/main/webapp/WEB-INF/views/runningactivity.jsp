<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<head>

<title>Trčanje</title>
</head>
<body>
<div class="pregledBicikliranja">
<c:forEach items="${run}" var="li">
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