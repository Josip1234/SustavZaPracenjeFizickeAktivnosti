package com.josip.physical.activity.baza;
import java.sql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.login.Login;
import com.josip.physical.activity.regist.Registration;




@Component("Spajanje na bazu")
public class PhysicalActivityDatabase{

private String ime_baze;
private String host;
private String user;
private String vrsta_baze;
private String Driver;
private Registration rg;
public int broji=0;
private Login lg;

public PhysicalActivityDatabase(){
	
	this.ime_baze="physicalactivity";
	this.host="//127.0.0.1/";
	this.user="root";
	this.vrsta_baze="jdbc:mysql:";
	this.Driver="com.mysql.jdbc.Driver";
	this.lg=lg;
}



public void setVrsta_baze(String vrsta_baze) {
	this.vrsta_baze = vrsta_baze;
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

public boolean InsertUser(Registration rg){
 rg=new Registration(rg.getOIB(),rg.getIme(),rg.getPrezime(),rg.getSpol(),rg.getDatumr(),rg.getEmail(),rg.getSifra());

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
	
	
	s.execute("INSERT INTO `registration` (`OIB`, `ime`, `prezime`, `spol`, `datumr`, `email`, `sifra`) VALUES ('"+rg.getOIB()+"', '"+rg.getIme()+"', '"+rg.getPrezime()+"', '"+rg.getSpol()+"', '"+rg.getDatumr()+"', '"+rg.getEmail()+"', '"+rg.getSifra()+"')");
	System.out.println( "Korisnik unesen");
    conn.close();
}catch (SQLException err) {
	System.err.println("SQL greska");
	err.printStackTrace(System.err);
	System.exit(0);
}

return true;
}

public boolean traziKorisnika(String email){

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
	ResultSet rs = null;
	String query="SELECT OIB FROM registration WHERE email='email'";
	rs=s.executeQuery(query);
	String oib="";
	while(rs.next()){
		oib=rs.getString("OIB");
		broji+=1;
		
	}
	rs.close();
	
    conn.close();
    
}catch (SQLException err) {
	System.err.println("SQL greska");
	err.printStackTrace(System.err);
	System.exit(0);
}
if(broji==1){
	return true;
}else{
	return false;
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
