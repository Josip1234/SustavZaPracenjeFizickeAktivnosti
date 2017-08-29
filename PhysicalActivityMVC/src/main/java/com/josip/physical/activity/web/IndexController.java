package com.josip.physical.activity.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import com.josip.physical.activity.baza.PhysicalActivityDatabase;
import com.josip.physical.activity.indeks_tjelesne_mase.BMICalculator;
import com.josip.physical.activity.indeks_tjelesne_mase.BMIReprository;
import com.josip.physical.activity.login.Login;
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
	public String vrijednosti(@RequestParam("OIB")String OIB,@RequestParam("ime")String ime,@RequestParam("prezime")String prezime,@RequestParam("spol")String spol,@RequestParam("datumr")Date datumr,@RequestParam("email")String email,@RequestParam("sifra")String sifra,Model model){
	
		
		String O=OIB;
		String i=ime;
		String p=prezime;
		String s=spol;
		String e=email;
		String sf=sifra;
		String dt=datumr.toString();
	    Registration rg = new Registration(O,i,p,s,dt,e,sf);
        PhysicalActivityDatabase db = new PhysicalActivityDatabase();
        


		model.addAttribute("OIB",O);
	   
		return "redirect:korisnik"+db.InsertUser(rg);
	}


@RequestMapping(value="korisniktrue",method=RequestMethod.GET)
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
	return "korisniktrue";
}


@RequestMapping(value="BMICalc",method=GET)
public String BMICalc(){
	return "BMICalc";
}
@RequestMapping(value="prijavnica",method=GET)
public String prijavnica(){
	return "prijavnica";
}

@RequestMapping(value="prijavnica",method=POST)
public String provjeriPrijavu(@RequestParam("email")String email,@RequestParam("sifra")String sifra){
	System.out.println(email);
    System.out.println(sifra);
    Login lg = new Login(email,sifra);
    boolean user;
    PhysicalActivityDatabase db = new PhysicalActivityDatabase();
    user=db.traziKorisnika(email);
    System.out.println(user);
	return "redirect:index";
}

}
