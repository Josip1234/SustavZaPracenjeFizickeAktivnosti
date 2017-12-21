<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %><%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<head><title>Prijava</title></head>
<body>

<ol>

<c:forEach var="li" items="${sum} "> 
  <tr>
    
    <td><c:out value="${li}" /></td>

  </tr>
</c:forEach>

</ol>
</body>