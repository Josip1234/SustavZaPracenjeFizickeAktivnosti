package com.josip.physical.activity.regist;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.josip.physical.activity.baza.PhysicalActivityDatabase;
@Component
public class RegistrationImpl implements RegistrationRepository {
	List<Registration> korisnikli=new ArrayList<Registration>();
	int length=0;
	/*@Autowired
	PhysicalActivityDatabase db=new PhysicalActivityDatabase();
	*/
	@Autowired
	Registration rg;
	/*@Override
	public List<Registration> listaKorisnika() {
		
			List<Registration> registracija = new ArrayList<Registration>();
			try{
				   Class.forName(db.getDriver()).newInstance();
				   
				}catch (Exception err) {
					
					err.printStackTrace(System.err);
					System.exit(0);
				}
				String dbname=db.getIme_baze();
				Connection conn = null;
				try{
					
					conn=DriverManager.getConnection(db.getVrsta_baze()+db.getHost());
					
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
	@Override
	public List<Registration> spremiPodatke(String OIB, String ime, String prezime, String spol, String datumr,
			String email, String sifra) {
		List<Registration> korisnik=new ArrayList<Registration>();
		
				
				korisnikli.add(new Registration(OIB, ime, prezime, spol, datumr,email,  sifra));
				
				
				return korisnikli;
	}
	@Override
	public Registration pronadjiPoOibu(String OIB) throws UnsupportedEncodingException {
		Registration korisnik = new Registration();
		for(int i=0;i<korisnikli.size();i++){
			if(korisnikli.get(i).getOIB().equals(OIB)){
				System.out.println(new String (korisnikli.get(i).getOIB().getBytes ("iso-8859-1"), "UTF-8"));
				System.out.println(new String (korisnikli.get(i).getIme().getBytes ("iso-8859-1"), "UTF-8"));
				korisnik.setOIB(new String (OIB.getBytes ("iso-8859-1"), "UTF-8"));
				korisnik.setIme(new String (korisnikli.get(i).getIme().getBytes ("iso-8859-1"), "UTF-8"));
				korisnik.setPrezime(new String (korisnikli.get(i).getPrezime().getBytes ("iso-8859-1"), "UTF-8"));
				korisnik.setSpol(new String (korisnikli.get(i).getSpol().getBytes ("iso-8859-1"), "UTF-8"));
				korisnik.setDatumr(new String (korisnikli.get(i).getDatumr().getBytes ("iso-8859-1"), "UTF-8"));
				korisnik.setEmail(new String (korisnikli.get(i).getEmail().getBytes ("iso-8859-1"), "UTF-8"));
				korisnik.setSifra(new String (korisnikli.get(i).getSifra().getBytes ("iso-8859-1"), "UTF-8"));
				break;
			}else{
				continue;
			}
		}
		
		return korisnik;
		
	}

		
	}


