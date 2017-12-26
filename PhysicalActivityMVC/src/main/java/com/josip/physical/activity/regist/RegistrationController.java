package com.josip.physical.activity.regist;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.josip.physical.activity.baza.PhysicalActivityDatabase;

@Controller
@RequestMapping(value= {"/","/index/","/registracija"})
public class RegistrationController {
	@RequestMapping(value="/registracija",method=RequestMethod.GET)
	public String registracija(Model model){
		model.addAttribute(new Registration());
		return "registracija";

	}
	
	@RequestMapping(value="/registracija",method=RequestMethod.POST)
	public String vrijednosti(@Valid Registration rg,Errors errors,@RequestParam("OIB")String OIB,@RequestParam("ime")String ime,@RequestParam("prezime")String prezime,@RequestParam("spol")String spol,@RequestParam("datumr")String datumr,@RequestParam("email")String email,@RequestParam("sifra")String sifra,Model model){
	
		
		String O=OIB;
		String i=ime;
		String p=prezime;
		String s=spol;
		String e=email;
		String sf=sifra;
		String dt=datumr.toString();
	    rg = new Registration(O,i,p,s,dt,e,sf);
	    
	    if(errors.hasErrors()){
	    	
	    	return "registracija";
	    }else{
        PhysicalActivityDatabase db = new PhysicalActivityDatabase();
        db.InsertUser(rg);
        return "redirect:index";
	    }


	   
	}
	
}
