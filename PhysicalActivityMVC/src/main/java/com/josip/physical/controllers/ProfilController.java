package com.josip.physical.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.josip.physical.activity.regist.Registration;
import com.josip.physical.activity.regist.RegistrationImpl;


@Controller
@RequestMapping(value= {"/","/index/","/mojprofil"})
public class ProfilController {
	 @Autowired
	 RegistrationImpl impl;
	 @Autowired
	 Registration re;
	 
	
	@RequestMapping(value="/mojprofil", method=RequestMethod.GET)
	public String mojprofil(){
		String oib="";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Object credentials = auth.getCredentials();
		oib=impl.pronadjiOib(username);
		System.out.println(username);
		System.out.println(oib);
		return "mojprofil";
	}
	@RequestMapping(value="/index",method=POST)
	public String show(Model model,@RequestParam(value="OIB",defaultValue="10111111111") String OIB,@RequestParam(value="ime",defaultValue="blabla") String ime,@RequestParam(value="prezime",defaultValue="gegrgeg") String prezime,@RequestParam(value="email",defaultValue="email@email.email") String email,@RequestParam(value="sifra",defaultValue="grgegergg") String sifra) {
		return "index";
	}
}
