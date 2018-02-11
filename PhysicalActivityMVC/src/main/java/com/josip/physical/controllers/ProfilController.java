package com.josip.physical.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.josip.physical.activity.baza.PhysicalActivityDatabase;
import com.josip.physical.activity.regist.Registration;
import com.josip.physical.activity.regist.RegistrationImpl;
import com.josip.physical.activity.regist.RegistrationRepository;

@Controller
@RequestMapping(value= {"/","/index/","/mojprofil"})
public class ProfilController {
	        @Autowired
	        Registration kor;
	      
	        @Autowired
	        RegistrationImpl impl;
	     /*   static JdbcTemplate obj;
	        static SimpleDriverDataSource ds;
	        static String DB_USERNAME="root";
	        static String DB_PASSWORD ="";
	        static String DB_URL = "jdbc:mysql://localhost:3306/physical";
	        
	        public static SimpleDriverDataSource getConn() {
	        	try {
					ds.setDriver(new com.mysql.jdbc.Driver());
					ds.setUrl(DB_URL);
					ds.setUsername(DB_USERNAME);
					ds.setPassword(DB_PASSWORD);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return ds;
	        }
	        */
	@RequestMapping(value="/mojprofil", method=RequestMethod.GET)
	public String mojprofil(Model model){
	    
		
		String oib="";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Object credentials = auth.getCredentials();
		Registration rg= impl.korisnik(username);
		kor=rg;
		System.out.println(username);
		System.out.println(oib);
		model.addAttribute("korisnik",kor);
		return "mojprofil";
	}
	@RequestMapping(value="/mojprofil",method=POST)
	public String show(Model model,@RequestParam(value="OIB") String OIB,@RequestParam(value="ime") String ime,@RequestParam(value="prezime") String prezime,@RequestParam(value="email") String email,@RequestParam(value="sifra") String sifra) {
		boolean up;
		
		System.out.println(OIB+ime+prezime+email+sifra);
		String oib="";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Object credentials = auth.getCredentials();
		Registration rg= impl.korisnik(username);
		up=impl.Update(OIB, ime, prezime, email, sifra,username);
		kor=rg;
		System.out.println(username);
		System.out.println(oib);
		System.out.println(up);
		model.addAttribute("korisnik",kor);
		return "mojprofil";
	}

	
}
