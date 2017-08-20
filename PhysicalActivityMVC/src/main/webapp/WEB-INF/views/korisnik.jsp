<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Korisnikovi podaci</title>
</head>
<body>
<p>Korisnikov oib je: ${OIB} </p>
<p>Korisnikovo ime je: ${Ime}</p>
<p>Korisnikovo prezime: ${Prezime}</p>
<p>Spol korisnika: ${spol} </p>
<p>Datum rođenja: ${Datum_rodjenja }</p>
<p>Korisnikov e-mail: ${Email}</p>
<p>Šifra:${sifra}</p>
</body>
</html>