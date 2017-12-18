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

<c:forEach var="email" items="${rep} "> 
  <tr>
    
    <td><c:out value="${email}" /></td>

  </tr>
</c:forEach>



<a href="index">Povratak u izbornik</a>

</body>
