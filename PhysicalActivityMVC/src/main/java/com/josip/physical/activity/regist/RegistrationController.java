package com.josip.physical.activity.regist;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.josip.physical.activity.baza.PhysicalActivityDatabase;


@Controller
@RequestMapping(value= {"/","/index/","/registracija","/mojprofil"})
public class RegistrationController {
	private RegistrationRepository registrationRepository;
	
	
	
	@Autowired
	public RegistrationController(RegistrationRepository registrationRepository) {
		this.registrationRepository=registrationRepository;
		
		// TODO Auto-generated constructor stub
	}
	@RequestMapping(value="/registracija",method=RequestMethod.GET)
	public String registracija(Model model){
		model.addAttribute(new Registration());
		return "registracija";

	}
	@RequestMapping(value="/registracija",method=RequestMethod.POST)
	public String procesirajRegistraciju(@Valid Registration korisnik,Errors errors) throws UnsupportedEncodingException{
		if(errors.hasErrors()){
			return "registracija";
		}else {
		registrationRepository.spremiPodatke(korisnik.getOIB(),korisnik.getIme(),korisnik.getPrezime(),korisnik.getSpol(),korisnik.getDatumr(),korisnik.getEmail(),korisnik.getSifra());
		return "redirect:registracija/mojprofil/"+korisnik.getOIB();
		}
	}
	@RequestMapping(value="registracija/mojprofil/{OIB}",method=GET)
	public String showProfile(@PathVariable String OIB,Model model) throws UnsupportedEncodingException {
		 
        Registration kor = registrationRepository.pronadjiPoOibu(OIB);
        
		model.addAttribute("korisnik",kor);
		return "mojprofil";
	}
	
	
	
	/*
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
*/

	   
	}
	

