package com.josip.physical.activity.regist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.baza.PhysicalActivityDatabase;
@Component
public class RegistrationImpl implements RegistrationRepository {

	@Autowired
	PhysicalActivityDatabase db=new PhysicalActivityDatabase();
	@Autowired
	Registration rg;
	@Override
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

		
	}


