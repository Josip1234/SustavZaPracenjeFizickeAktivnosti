package com.josip.physical.activity.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josip.physical.activity.login.LoginReprository;
@Controller
@RequestMapping(value={"/","/physical","/index","/dohvatpodataka"})
public class DohvatController {
	private LoginReprository loginRepository;
	@Autowired
	public DohvatController(LoginReprository loginRepository){
		this.loginRepository=loginRepository;
	}
	@RequestMapping(value="/dohvatpodataka", method=GET)
	public String dohvatpodataka(Model model){
		model.addAttribute("rep",loginRepository.kreirajListuKorisnika());
		return "dohvatpodataka";
	}
	
}
