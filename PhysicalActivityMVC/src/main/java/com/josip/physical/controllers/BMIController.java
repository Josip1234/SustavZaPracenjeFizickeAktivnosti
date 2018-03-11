package com.josip.physical.controllers;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.josip.physical.activity.indeks_tjelesne_mase.BMICalculator;
import com.josip.physical.activity.regist.Registration;

@Controller
@RequestMapping(value= {"/","/index/","/BMICalc"})
public class BMIController {
	@RequestMapping(value="/BMICalc",method=GET)
	public String BMICalc(){
		return "BMICalc";
	}
	@RequestMapping(value="/BMICalc",method=POST)
	public void rezultat(@RequestParam("spol")char spol,@RequestParam("visina_osobe_u_metrima")double visina_osobe_u_metrima,@RequestParam("masa_u_kg")double masa_u_kg,Model model){
		char sex=spol;
		String izraz="";
		double rezultat=0.0;
		String sp="";
		String ind="";
		double a=0.0;
		double b=0.0;
		a=masa_u_kg;
		b=visina_osobe_u_metrima;
		sp="m";
		BMICalculator bm = new BMICalculator(sex,a,b);
		rezultat=bm.izracunajBMI(bm.getMasa_u_kg(),bm.getVisina_osobe());
		bm.setRezultat(rezultat);
		ind=bm.granica_BMI(bm.getSpol(),rezultat,izraz);
		model.addAttribute("rezultat",rezultat+" "+ind);
		izraz="";
		}
	
 

}
