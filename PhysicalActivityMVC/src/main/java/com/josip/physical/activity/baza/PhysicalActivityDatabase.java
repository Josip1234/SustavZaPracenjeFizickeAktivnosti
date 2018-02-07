package com.josip.physical.activity.baza;
import java.sql.Driver;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.sql.PreparedStatement;
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


public PhysicalActivityDatabase(){
	
	this.ime_baze="physical";
	this.host="//localhost:3307/?";
	this.user="root";
	this.vrsta_baze="jdbc:mysql:";
	this.Driver="com.mysql.jdbc.Driver";
	this.encoding="?useUnicode=true&characterEncoding=UTF-8";
	
}

public String getHost(){
	return host;
}


public void setHost(String host) {
	this.host = host;
}

public void setUser(String user) {
	this.user = user;
}

public void setBroji(int broji) {
	this.broji = broji;
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

public boolean InsertUser(Registration rg) throws UnsupportedEncodingException{
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
	conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?"+"user=root");
	System.out.println("Spojen sam na bazu");
	System.out.println("Unosim korisnika:");
	Statement s = conn.createStatement();
	s.execute("SHOW DATABASES");
	s.execute("USE physical");
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
/*
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
	conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?"+"user=root");
	System.out.println("Spojen sam na bazu");
	Statement s = conn.createStatement();
	s.execute("SHOW DATABASES");
	s.execute("USE physical");
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

*/



public String getIme_baze() {
	return ime_baze;
}

public void setIme_baze(String ime_baze) {
	this.ime_baze = ime_baze;
}

/*
* Nakon loadiranja drivera, mo≈æemo napraviti vezu prema bazi
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


public List<Registration> listaKorisnika(String email) throws UnsupportedEncodingException{
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
			
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?"+"user=root");
			
			Statement s = conn.createStatement();
			s.execute("SHOW DATABASES");
			s.execute("USE physical");
			ResultSet rs = null;
			String query="SELECT OIB,ime,prezime,spol,datumr,email,sifra FROM registration WHERE email='"+email+"'";
			rs=s.executeQuery(query);
			
		    int broj=0;
			while(rs.next()){
				
				String oib=rs.getString("OIB");
				String ime = rs.getString("ime");
				String prezime = new String(rs.getString("prezime").getBytes ("iso-8859-1"), "UTF-8");
				String spol=rs.getString("spol");
				String datum=rs.getString("datumr");
				String emai=rs.getString("email");
		        String sifra=rs.getString("sifra");
		        
		      
		       
		       
		        registracija.add(new Registration(oib,ime,prezime,spol, datum,emai,sifra));
		        
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



public boolean delete(String email){
	
	try{
		   Class.forName(Driver).newInstance();
		   
		}catch (Exception err) {
			
			err.printStackTrace(System.err);
			System.exit(0);
		}
		String dbname=getIme_baze();
		Connection conn = null;
		try{
			
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?"+"user=root");
			
			Statement s = conn.createStatement();
			s.execute("SHOW DATABASES");
			s.execute("USE physical");
			PreparedStatement st = conn.prepareStatement("DELETE  FROM registration WHERE email='"+email+"'");
			st.executeUpdate();
			
		    conn.close();
		    
		    
		}catch (SQLException err) {
			System.err.println("SQL greska");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	
	
	
	
	return true;
}

public List<Registration> update(List<Registration> reg) throws UnsupportedEncodingException {
	
	List<Registration> regist = new ArrayList();
	
	regist=reg;
	
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
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?"+"user=root");
			System.out.println("Spojen sam na bazu");
			System.out.println("Aûuriram korisnika:");
			Statement s = conn.createStatement();
			s.execute("SHOW DATABASES");
			s.execute("USE physical");
			for (Registration registration : regist) {
				s.execute("UPDATE `registration` (`OIB`, `ime`, `prezime`, `email`, `sifra`) SET ('"+registration.getOIB()+"', '"+registration.getIme()+"', '"+registration.getPrezime()+"', '"+registration.getEmail()+"', '"+registration.getSifra()+"')");
			}
			
			System.out.println( "Korisnik aûuriran");
		    conn.close();
		}catch (SQLException err) {
			System.err.println("SQL greska");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	
	return regist;
}


public String dohvatiOib(String email){
	String oib="";
	try{
		   Class.forName(Driver).newInstance();
		   
		}catch (Exception err) {
			
			err.printStackTrace(System.err);
			System.exit(0);
		}
		String dbname=getIme_baze();
		Connection conn = null;
		try{
			
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?"+"user=root");
			
			Statement s = conn.createStatement();
			s.execute("SHOW DATABASES");
			s.execute("USE physical");
			ResultSet rs = null;
			String query="SELECT OIB FROM registration WHERE email='"+email+"'";
			rs=s.executeQuery(query);
			
		    int broj=0;
			while(rs.next()){
				
				oib=rs.getString("OIB");
				
		      
		       
		       
		      
				
				
			}
			rs.close();
			
		    conn.close();
		    
		    
		}catch (SQLException err) {
			System.err.println("SQL greska");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	
	
	
	
	return oib;
}








}
