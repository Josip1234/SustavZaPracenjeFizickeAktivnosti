package com.josip.physical.acitivty.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.josip.physical.activity.baza.SpringDataSource;
import com.josip.physical.activity.regist.Registration;
import com.josip.physical.activity.regist.RegistrationImpl;


@Controller
@RequestMapping({"/","/physical/","/1e2b3tzrUZcvn"})
public class JsonGeneratorController {

		@Autowired
		Registration reg;
		@Autowired
		RegistrationImpl impl;
		
		@Bean
		public MessageSource messageSource(){
			ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
			messageSource.setBasename("messages");
			return messageSource;
		}

	    
		@RequestMapping(value="/1e2b3tzrUZcvn")
		@ResponseBody
		public Registration korisnik(){
			Registration korisnik=new Registration();
			return korisnik;
			
		}
		
		@RequestMapping(value= {"/1e2b3tzrUZcvn"},method=RequestMethod.GET, produces="application/json")
		public String RemoteService(Model model){
		   model.addAttribute("lista",korisnik());
	 		return "1e2b3tzrUZcvn";
		}
	}

