package com.josip.physical.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value= {"/","/index/","/mojprofil"})
public class ProfilController {
	@RequestMapping(value="/mojprofil", method=RequestMethod.POST)
	public String mojprofil(){
		
		return "mojprofil";
	}
}
