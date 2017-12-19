<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<head>

<title>Trčanje</title>
</head>
<body>
<ul>

<c:forEach var="li" items="${run} "> 
  
    
    <li><c:out value="${li}" /></li>

  
</c:forEach>

</ul>
</body>
</html>