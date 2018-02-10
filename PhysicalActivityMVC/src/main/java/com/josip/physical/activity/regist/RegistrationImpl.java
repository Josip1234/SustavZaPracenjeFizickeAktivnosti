package com.josip.physical.activity.regist;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;


import com.josip.physical.activity.baza.PhysicalActivityDatabase;
@Component
public class RegistrationImpl implements RegistrationRepository {
	
	List<Registration> korisnikli=new ArrayList<Registration>();
	int length=0;
	@Autowired
	PhysicalActivityDatabase db;
	   static JdbcTemplate obj;
	    static SimpleDriverDataSource ds;
	    static String DB_USERNAME="root";
	    static String DB_PASSWORD ="";
	    static String DB_URL = "jdbc:mysql://localhost:3306/physical";
	   
	    
		public static DataSource getConn() {
			DriverManagerDataSource ds = new DriverManagerDataSource();
			ds.setDriverClassName("com.mysql.jdbc.Driver");
			ds.setUrl("jdbc:mysql://127.0.0.1/physical");
			ds.setUsername("root");
			ds.setPassword("");
			return ds;
		}
		
	public Registration korisnik(String username) {
		Registration rg=new Registration();
		obj=new JdbcTemplate(getConn());
		if(null != obj) {
			String select="SELECT OIB,ime,prezime,spol,datumr,email,sifra FROM `registration` WHERE email='"+username+"'";
			List<Registration> reg=obj.query(select,new RowMapper() {
				public Registration mapRow(final ResultSet result,final int rowNum) throws SQLException{
					Registration kor=new Registration();
					kor.setOIB(result.getString("OIB"));
					kor.setIme(result.getString("ime"));
					kor.setPrezime(result.getString("prezime"));
					kor.setSpol(result.getString("spol"));
					kor.setDatumr(result.getString("datumr"));
					kor.setEmail(result.getString("email"));
					kor.setSifra(result.getString("sifra"));
					
					return kor;
					
				}});
				
			for (Registration registration : reg) {
				System.out.println(registration.toString());
				rg=new Registration(registration.getOIB(),registration.getIme(),registration.getPrezime(),registration.getSpol(),registration.getDatumr(),registration.getEmail(),registration.getSifra());
			}
			}
		
		return rg;
	}
	
	
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
	public boolean spremiPodatke(String OIB, String ime, String prezime, String spol, String datumr,
			String email, String sifra) throws UnsupportedEncodingException {
		Registration reg =new Registration();
		reg.setIme(ime);
		reg.setDatumr(datumr);
		reg.setEmail(email);
		reg.setOIB(OIB);
		reg.setPrezime(prezime);
		reg.setSifra(sifra);
		reg.setSpol(spol);
		
		
		db.InsertUser(new Registration(OIB,ime,prezime,spol,datumr,email,sifra));
	
				return true;
	}
	/*@Override
	public Registration pronadjiPoOibu(String OIB) throws UnsupportedEncodingException {
		Registration korisnik = new Registration();
		List<Registration> list = new ArrayList<Registration>();
		for(int i=0;i<korisnikli.size();i++){
			if(korisnikli.get(i).getOIB().equals(OIB)){
			    list=db.listaKorisnika(korisnikli.get(i).getEmail());
				//ispraviti ovo u setteru je u glavnoj klasi postavljeno za dohvat enkodiranja
				System.out.println(korisnikli.get(i).getOIB());
				System.out.println(korisnikli.get(i).getIme());
				korisnik.setOIB(OIB);
				korisnik.setIme(list.get(i).getIme());
				korisnik.setPrezime(list.get(i).getPrezime());
				korisnik.setSpol(list.get(i).getSpol());
				korisnik.setDatumr(list.get(i).getDatumr());
				korisnik.setEmail(list.get(i).getEmail());
				korisnik.setSifra(list.get(i).getSifra());
				break;
			}else{
				continue;
			}
		}
		
		return korisnik;
		
	}*/
	@Override
	public List<Registration> ListUser(String email) {
		List<Registration> reg=new ArrayList<Registration>();
		reg=db.listaKorisnika(email);
		return reg;
	}
	@Override
	public boolean updateUser(String oib, String ime, String prezime, String email, String sifra) throws UnsupportedEncodingException {
		List<Registration> registration = new ArrayList();
		registration.add(new Registration(oib,ime,prezime,email,sifra));
		db.update(registration);
		return true;
	}
	@Override
	public boolean deleteUser(String email) {
		db.delete(email);
		return false;
	}
	@Override
	public String pronadjiOib(String email) {
		String oib="";
		oib=db.dohvatiOib(email);
		return oib;
	}

		
	}


