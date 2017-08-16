package com.josip.physical.activity.baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component("Spajanje na bazu")
public class PhysicalActivityDatabase{

private String ime_baze;
private String host;
private String user;
private String vrsta_baze;
private String Driver;

public PhysicalActivityDatabase(){
	
	this.ime_baze="physicalactivity";
	this.host="//127.0.0.1/";
	this.user="root";
	this.vrsta_baze="jdbc:mysql:";
	this.Driver="com.mysql.jdbc.Driver";
}



public void setVrsta_baze(String vrsta_baze) {
	this.vrsta_baze = vrsta_baze;
}



public void setDriver(String driver) {
	Driver = driver;
}



public String getVrsta_baze() {
	return this.vrsta_baze;
}





public String getDriver() {
	return this.Driver;
}





/*
* Prvo loadati drivere za bazu podataka	
*/

public void InsertUser(){
 

try{
   Class.forName(Driver).newInstance();
   System.out.println("Driver dohvacen");
}catch (Exception err) {
	System.out.println("Driver nije dohvacen mozda nije online");
	err.printStackTrace(System.err);
	System.exit(0);
}
String dbname=getIme_baze();
Connection conn = null;
try{
	System.out.println("Spajam se nma bazu...");
	conn=DriverManager.getConnection(vrsta_baze+host);
	System.out.println("Spojen sam na bazu");
	System.out.println("Unosim korisnika:");
	Statement s = conn.createStatement();
	s.execute("SHOW DATABASES");
	s.execute("USE physicalactivity");
	
	
	s.execute("INSERT INTO `registration` (`id`, `ime`, `prezime`, `spol`, `datumr`, `drzavar`, `drzavap`, `email`, `sifra`, `zanimanje`) VALUES (1, 'n', 'n', 'n', '2017-10-11', 'n', 'n', 'n', 'n', 'n')");
	System.out.println( "Korisnik unesen");
    conn.close();
}catch (SQLException err) {
	System.err.println("SQL greška");
	err.printStackTrace(System.err);
	System.exit(0);
}


}







public String getIme_baze() {
	return ime_baze;
}

public void setIme_baze(String ime_baze) {
	this.ime_baze = ime_baze;
}

/*
* Nakon loadiranja drivera, možemo napraviti vezu prema bazi
*/
public void spoji(){
try {

    Class.forName("com.mysql.jdbc.Driver").newInstance();
    System.out.println("Registriran driver");

} catch (Exception e) {

    System.out.println(e.toString());

}
try {
Connection conn= DriverManager.getConnection(vrsta_baze+host);
try {

    Class.forName("com.mysql.jdbc.Driver").newInstance();
    System.out.println("Spojeno");
    conn.close();
} catch (Exception e) {

    System.out.println(e.toString());

}

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}

}
