package com.josip.physical.activity.baza;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.josip.physical.activity.regist.Registration;
import com.josip.physical.activity.regist.RegistrationImpl;
import com.josip.physical.activity.regist.RegistrationRepository;
import com.josip.physical.activity.summary.SummaryImplementation;


public class terstconn {
    
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
	


	public static void main(String[] args) throws UnsupportedEncodingException {
		//WalkingStatistika statistika = new WalkingStatistika("jbosnjak3@gmail.com",0,0,0,0,0,"1992-05-05",0,0);
		//SummaryImplementation implementation = new SummaryImplementation();
		//implementation.dodajStatistiku(statistika);
		/*String username="jbosnjak3@gmail.com";
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
			}
			}
		//boolean a;
		
		// TODO Auto-generated method stub
		
		//PhysicalActivityDatabase db = new PhysicalActivityDatabase();
	    //Registration a=new Registration();
		//db.spoji();
		//db.InsertUser(new Registration("125555555","Joiš","boiš","m","1992-01-01","grgr@gg.gg","122222222"));
		//a=db.delete("mmarkovic@gmail.com");
	 
		//System.out.println(a);
		//RegistrationImpl regImpl=new RegistrationImpl();
		//a=regImpl.pronadjiPoOibu("86052601428");
		//System.out.println(a.getPrezime());
		//regImpl.updateUser("11111144444", "marko", "markovi�", "jbosnjak3@gmail.com", "rggegeef");
		
	    /*
	    char username2;
	    String[] username3;*/
		/*List<Registration> reg=new ArrayList();
		
		reg=db.listaKorisnika("jbosnjak3@gmail.com");
		for (Registration registration : reg) {
			System.out.println(reg); 
			System.out.println(registration.getOIB());
			System.out.println(registration.getIme());
			System.out.println(registration.getPrezime());
		}*/
		/*a=db.delete("ad3h@gg.aa");
		System.out.println(a);
		/*
		int size=db.listaKorisnika().size();
		String[] email=new String[size];
		String[] sifra=new String[size];
		//System.out.println(db.listaKorisnika().get(2).getOIB());
		//System.out.println(db.listaKorisnika().get(2).getIme());
        //System.out.println(db.listaKorisnika().size());
        //username[0]=db.listaKorisnika().get(0).getEmail().substring(1,11);
        //System.out.println(username[0]);
        
		for(int i=0;i<size;i++){
			email[i]=db.listaKorisnika().get(i).getEmail();
			System.out.println(email[i].substring(1, email[i].length()-1));
			sifra[i]=db.listaKorisnika().get(i).getSifra();
			System.out.println(sifra[i].substring(1, sifra[i].length()-1));
		}
		*/
		
		/*String polje[]=db.mojprofil("jbosnjak3@gmail.com");
        for (String string : polje) {
			System.out.println(string);
		}
        */
        
	


	}
}
