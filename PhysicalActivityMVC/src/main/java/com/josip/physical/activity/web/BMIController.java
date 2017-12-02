package com.josip.physical.activity.web;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.josip.physical.activity.indeks_tjelesne_mase.BMICalculator;

@Controller
@RequestMapping(value={"/index","/BMICalc","/BMICalc"})
public class BMIController {
	@RequestMapping(value="/BMICalc",method=POST)
	public String rezultat(@RequestParam("visina_osobe_u_metrima")double visina_osobe_u_metrima,@RequestParam("masa_u_kg")int masa_u_kg,Model model){
		String izraz="";
		double rezultat=0.0;
		String sp="";
		String ind="";
		int a=0;
		double b=0.0;
		a=masa_u_kg;
		b=visina_osobe_u_metrima;
		sp="m";
		BMICalculator bm = new BMICalculator(a,b);
		rezultat=bm.izracunajBMI(bm.getMasa_u_kg(),bm.getVisina_osobe());
		bm.setRezultat(rezultat);
		ind=bm.granica_BMI(rezultat,sp,izraz);
		model.addAttribute("rezultat",rezultat+" "+ind);
		izraz="";
		return "BMICalc";
		
		
		
	}
	
 

}
