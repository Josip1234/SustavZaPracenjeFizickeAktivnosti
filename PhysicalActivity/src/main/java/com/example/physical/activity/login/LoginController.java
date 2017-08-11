package com.example.physical.activity.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/listakorisnika")
public class LoginController {
private LoginRepository loginRepository;
@Autowired
public LoginController(LoginRepository loginRepository){
	this.loginRepository=loginRepository;
}
@RequestMapping(method=RequestMethod.GET)
public String listakorisnika(Model model){
	model.addAttribute(loginRepository.findUser(username, sifra));
	return "listakorisnika";
}
}
