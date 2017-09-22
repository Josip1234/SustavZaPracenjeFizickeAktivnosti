package com.josip.physical.activity.baza;
import java.sql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.login.Login;
import com.josip.physical.activity.regist.Registration;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;




@Component("Spajanje na bazu")
public class PhysicalActivityDatabase {

private String ime_baze;
private String host;
private String user;
private String vrsta_baze;
private String Driver;
private String encoding;

public int broji=0;
private Login lg;

public PhysicalActivityDatabase(){
	
	this.ime_baze="physicalactivity";
	this.host="//127.0.0.1/";
	this.user="root";
	this.vrsta_baze="jdbc:mysql:";
	this.Driver="com.mysql.jdbc.Driver";
	this.encoding="?useUnicode=true&characterEncoding=UTF-8";
	this.lg=lg;
}

public String getHost(){
	return host;
}


public String getEncoding() {
	return encoding;
}



public void setEncoding(String encoding) {
	this.encoding = encoding;
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

public void setDriver(String driver) {
	Driver = driver;
}

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
	conn=DriverManager.getConnection(vrsta_baze+host+encoding);
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
	Statement s = conn.createStatement();
	s.execute("SHOW DATABASES");
	s.execute("USE physicalactivity");
	ResultSet rs = null;
	String query="SELECT OIB FROM registration WHERE email='"+email+"'";
	rs=s.executeQuery(query);
	String oib="";
	while(rs.next()){
		oib=rs.getString("OIB");
		System.out.println(oib);
		broji+=1;
		
	}
	rs.close();
	System.out.println(oib);
    conn.close();
    
}catch (SQLException err) {
	System.err.println("SQL greska");
	err.printStackTrace(System.err);
	System.exit(0);
}
if(broji<2 && broji>0) return true;
else return false;
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

/*
public List<Registration> listaKorisnika(){
	List<Registration> registracija = new ArrayList<Registration>();
	try{
		   Class.forName(Driver).newInstance();
		   
		}catch (Exception err) {
			
			err.printStackTrace(System.err);
			System.exit(0);
		}
		String dbname=getIme_baze();
		Connection conn = null;
		try{
			
			conn=DriverManager.getConnection(vrsta_baze+host);
			
			Statement s = conn.createStatement();
			s.execute("SHOW DATABASES");
			s.execute("USE physicalactivity");
			ResultSet rs = null;
			String query="SELECT OIB,ime,prezime,spol,datumr,email,sifra FROM registration";
			rs=s.executeQuery(query);
			
		    int broj=0;
			while(rs.next()){
				
				String oib=rs.getString("OIB");
				String ime = rs.getString("ime");
				String prezime = rs.getString("prezime");
				String spol=rs.getString("spol");
				String datum=rs.getString("datumr");
				String email=rs.getString("email");
		        String sifra=rs.getString("sifra");
		        
		      
		       
		       
		        registracija.add(new Registration(oib+broj,ime+broj,prezime+broj,spol+broj, datum+broj,email+broj,sifra+broj));
		        
				broj+=1;
				
				
			}
			rs.close();
			
		    conn.close();
		    
		    
		}catch (SQLException err) {
			System.err.println("SQL greska");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	
	
	
	
	return registracija;
}

*/

public String[] mojprofil(String email){
	String[] profil = new String[7];
	String confirm="";
	confirm=email;
	try{
		   Class.forName(Driver).newInstance();
		   
		}catch (Exception err) {
			
			err.printStackTrace(System.err);
			System.exit(0);
		}
		String dbname=getIme_baze();
		Connection conn = null;
		try{
			
			conn=DriverManager.getConnection(vrsta_baze+host);
			
			Statement s = conn.createStatement();
			s.execute("SHOW DATABASES");
			s.execute("USE physicalactivity");
			ResultSet rs = null;
			String query="SELECT OIB,ime,prezime,spol,datumr,email,sifra FROM registration WHERE email='"+confirm+"'";
			rs=s.executeQuery(query);
			
		    int broj=0;
			while(rs.next()){
				
				String oib=rs.getString("OIB");
				profil[broj]=oib;
				broj+=1;
				String ime = rs.getString("ime");
				profil[broj]=ime;
				broj+=1;
				String prezime = rs.getString("prezime");
				profil[broj]=prezime;
				broj+=1;
				String spol=rs.getString("spol");
				profil[broj]=spol;
				broj+=1;
				String datum=rs.getString("datumr");
				profil[broj]=datum;
				broj+=1;
				String e=rs.getString("email");
				profil[broj]=e;
				broj+=1;
		        String sifra=rs.getString("sifra");
		        profil[broj]=sifra;
				
			}
			rs.close();
			
		    conn.close();
		    
		    
		}catch (SQLException err) {
			System.err.println("SQL greska");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	
	
	
	
	return profil;
}











}
