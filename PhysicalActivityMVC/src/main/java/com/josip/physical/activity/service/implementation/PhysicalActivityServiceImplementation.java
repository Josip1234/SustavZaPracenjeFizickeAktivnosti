package com.josip.physical.activity.service.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.josip.physical.activity.baza.PhysicalActivityDatabase;
import com.josip.physical.activity.login.Login;
import com.josip.physical.activity.regist.Registration;
import com.josip.physical.activity.service.PhysicalActivityService;

public class PhysicalActivityServiceImplementation implements PhysicalActivityService{
/*
	@Autowired
	Login login;
	
	@Autowired
	PhysicalActivityDatabase database;
	
	@Override
	public List<Login> getListOfUsers() {
		// TODO Auto-generated method stub
		database = new PhysicalActivityDatabase();
		List<Login> login = new ArrayList<Login>();
		try{
			   Class.forName(database.getDriver()).newInstance();
			   
			}catch (Exception err) {
				
				err.printStackTrace(System.err);
				System.exit(0);
			}
			String dbname=database.getIme_baze();
			Connection conn = null;
			try{
				
				conn=DriverManager.getConnection(database.getVrsta_baze()+database.getHost());
				
				Statement s = conn.createStatement();
				s.execute("SHOW DATABASES");
				s.execute("USE physicalactivity");
				ResultSet rs = null;
				String query="SELECT email,sifra FROM registration";
				rs=s.executeQuery(query);
				
			    int broj=0;
				while(rs.next()){
					
					String username=rs.getString("email");
			        String password=rs.getString("sifra");
			        
			      
			       
			       
			        login.add(new Login(username+broj,password+broj));
			        
					broj+=1;
					
					
				}
				rs.close();
				
			    conn.close();
			    
			    
			}catch (SQLException err) {
				System.err.println("SQL greska");
				err.printStackTrace(System.err);
				System.exit(0);
			}
		return null;
	}
	
	
	
	*/
	
}

