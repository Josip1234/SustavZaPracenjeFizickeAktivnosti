package com.josip.physical.activity.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistarRegController {
	
	private RegistrationRep registrationRep;
	@Autowired
	public RegistarRegController(RegistrationRep registrationRep){
		this.registrationRep=registrationRep;
	}
	@RequestMapping(value="registar",method=GET)
	public String registar(Model model){
		model.addAttribute("lista",registrationRep.listaKorisnika().get(0).getOIB());
		
		return "registar";
	}
	
}
