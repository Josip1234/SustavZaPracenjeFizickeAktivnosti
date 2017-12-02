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
<table>

<tr>

<td>Vrijeme</td>
<td>Brzina</td>
<td>Adresa</td>
<td>Longituda</td>
<td>Latituda</td>
<td>Korisnik</td>
<td>Udaljenost</td>

</tr>

<c:forEach items="${bike}" var="li"> 
  <tr>
    
    <td><c:out value="${li.vrijemeAktivnosti}" /></td>
    <td><c:out value="${li.brzinaUkm}" /></td>
    <td><c:out value="${li.lokacija}" /></td>
    <td><c:out value="${li.longitude}" /></td>
    <td><c:out value="${li.latitude}" /></td>
    <td><c:out value="${li.korisnik}" /></td>
    <td><c:out value="${li.udaljenost}" /></td>
  </tr>
</c:forEach>

</table>
</body>
</html>