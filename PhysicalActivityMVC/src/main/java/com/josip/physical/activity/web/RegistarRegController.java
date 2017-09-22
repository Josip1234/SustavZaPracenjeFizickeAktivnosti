package com.josip.physical.activity.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josip.physical.activity.login.LoginReprository;
import com.josip.physical.activity.regist.RegistrationRepository;



@Controller
@RequestMapping("registar")
public class RegistarRegController {
	
	private RegistrationRepository registrationRepository;
	
	@Autowired
	public  RegistarRegController(RegistrationRepository registrationRepository) {
		// TODO Auto-generated constructor stub
		this.registrationRepository=registrationRepository;
	}
	
	
	@RequestMapping(value="registar",method=GET)
	public String registar(Model model){
		model.addAttribute("regist",registrationRepository.listaKorisnika());
		
		return "registar";
	}
	
}
