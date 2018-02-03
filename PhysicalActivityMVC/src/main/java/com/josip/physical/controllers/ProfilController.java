package com.josip.physical.controllers;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value= {"/","/index/","/mojprofil"})
public class ProfilController {
	
	
	
	@RequestMapping(value="/mojprofil", method=RequestMethod.POST)
	public String mojprofil(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Object credentials = auth.getCredentials();
		System.out.println(username);
		return "mojprofil";
	}
}
