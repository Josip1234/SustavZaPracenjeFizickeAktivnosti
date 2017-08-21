package com.josip.physical.activity.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.josip.physical.activity.indeks_tjelesne_mase.BMICalculator;
import com.josip.physical.activity.indeks_tjelesne_mase.BMIReprository;
import com.josip.physical.activity.regist.Registration;
@Controller
@RequestMapping(value="/")
public class IndexController {

@RequestMapping(value="index",method=GET)
public String index(){
	return "index";
}
@RequestMapping(value="home",method=GET)
public String home(){
	return "home";
}
@RequestMapping(value="registracija",method=GET)
public String registracija(){
	return "registracija";

}

@RequestMapping(value="registracija",method=POST)
	public String vrijednosti(@RequestParam("OIB")String OIB,Model model){
		Registration registration=new Registration();
		String O="";
		registration.setOIB(OIB);
		O=registration.getOIB();
		model.addAttribute("OIB",O);
		return "redirect:index";
	}

@RequestMapping(value="korisnik",method=RequestMethod.GET)
public String korisnik(Model model){
	Registration registration = new Registration();
	String podatak="";
	podatak=registration.getOIB();
	String ime="";
	ime=registration.getIme();
	String prezime="";
	prezime=registration.getPrezime();
	model.addAttribute("OIB",podatak);
	model.addAttribute("Ime",ime);
	model.addAttribute("Prezime",prezime);
	String spol="";
	spol=registration.getSpol();
	model.addAttribute("spol",spol);
	String datumr="";
	datumr=registration.getDatumr();
	model.addAttribute("Datum_rodjenja",datumr);
	String email="";
	email=registration.getEmail();
	model.addAttribute("Email",email);
	String sifra="";
	sifra=registration.getSifra();
	model.addAttribute("sifra",sifra);
	return "korisnik";
}


@RequestMapping(value="BMICalc",method=GET)
public String BMICalc(){
	return "BMICalc";
}
}
