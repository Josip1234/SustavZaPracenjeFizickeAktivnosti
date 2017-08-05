<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Register</h1>
<form method="POST">
First name: <input type="text" name="ime"/><br/>
Last name: <input type="text" name="prezime"/><br/>
Spol: <select name="spol"><option value="m">Male</option><option value="z">Female</option></select>
<br>
Date of birth: <input type="text" name="datumr"><br/>
State of birth: <input type="text" name="drzavar"><br>
State of living: <input type="text" name="drzavap"><br>
Email: <input type="text" name="email"><br>
Password:  <input type="password" name="sifra"><br>
Working as: <input type="text" name="zanimanje"><br>
<input type="submit" value="Register">

</form>
</body>
</html>