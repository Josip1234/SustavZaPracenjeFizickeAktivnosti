package com.josip.physical.activity.login;
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
import com.josip.physical.activity.regist.Registration;
@Component
public class LoginWrapper implements LoginReprository {
	@Autowired
	PhysicalActivityDatabase db = new PhysicalActivityDatabase();
    
	@Autowired
	Registration rg;
	
	@Override
	public List<Login> kreirajListuKorisnika() {
		List<Login> login = new ArrayList<Login>();
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
				String query="SELECT email,sifra FROM registration";
				rs=s.executeQuery(query);
				
			    int broj=0;
				while(rs.next()){
					
					
					String email=rs.getString("email");
			        String sifra=rs.getString("sifra");
			        
			       
			       
			        login.add(new Login(email+broj,sifra+broj));
			        
					broj+=1;
					
					
				}
				rs.close();
				
			    conn.close();
			    
			    
			}catch (SQLException err) {
				System.err.println("SQL greska");
				err.printStackTrace(System.err);
				System.exit(0);
			}
		
		return login;
	}



	
}
