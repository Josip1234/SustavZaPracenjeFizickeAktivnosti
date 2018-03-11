package com.josip.physical.activity.indeks_tjelesne_mase;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.regist.Registration;

@Component("Indeks tjelesne mase")
public class BMICalculator implements BMIReprository {
	
	private double masa_u_kg;
	
	private double visina_osobe_u_metrima;
	private double rezultat;
	private char spol;
    Registration rg;
    
	public BMICalculator(){
	    this.spol='m';
		this.masa_u_kg=67.00;
		this.visina_osobe_u_metrima=1.67;
		
	}
	public BMICalculator(char spol,double masa,double visina){
		this.spol=spol;
		this.masa_u_kg=masa;
		this.visina_osobe_u_metrima=visina;
		
	}
   
	
  

	public double getMasa_u_kg() {
		return masa_u_kg;
	}

	public void setMasa_u_kg(double masa_u_kg) {
		this.masa_u_kg = masa_u_kg;
	}

	public double getVisina_osobe() {
		return visina_osobe_u_metrima;
	}

	public void setVisina_osobe(double visina_osobe) {
		this.visina_osobe_u_metrima = visina_osobe;
	}

	public double getRezultat() {
		return rezultat;
	}

	public void setRezultat(double rezultat) {
		this.rezultat = rezultat;
	}
    
	public double izracunajBMI(double masa,double visina_osobe){
		
		this.rezultat=masa/(visina_osobe*visina_osobe);
		return this.rezultat;
	}
	
	public double getVisina_osobe_u_metrima() {
		return visina_osobe_u_metrima;
	}
	public void setVisina_osobe_u_metrima(double visina_osobe_u_metrima) {
		this.visina_osobe_u_metrima = visina_osobe_u_metrima;
	}
	public char getSpol() {
		return spol;
	}
	public void setSpol(char spol) {
		this.spol = spol;
	}
	public Registration getRg() {
		return rg;
	}
	public void setRg(Registration rg) {
		this.rg = rg;
	}
	public String granica_BMI(char spol,double BMI,String izraz){
		if(BMI<19.1 && spol=='f'){
			izraz= "prenizak BMI";
		}else if(BMI<20.7 && spol=='m' ){
			izraz="prenizak BMI";
		}else if((BMI>=19.1 && BMI<=25.8)&& spol=='f'){
			izraz= "idealan BMI";
		}else if((BMI>=20.7 && BMI<=26.4)&&spol=='m'){
			izraz= "idealan BMI";
		}else if((BMI>25.8&&BMI<=27.3)&&spol=='f'){
			izraz= "Lagano visok BMI";
		}else if((BMI>26.4 && BMI<=27.8)&&spol=='m'){
			izraz= "Lagano visok BMI";
		}else if((BMI>27.3&&BMI<=32.3)&&spol=='f'){
			izraz= "Visok BMI";
			
		}else if((BMI>27.8&&BMI<=31.1)&&spol=='m'){
			izraz= "Visok BMI";
		}else if((BMI>32.3 &&BMI<=45)&&spol=='f'){
			izraz= "previsok BMI";
		}else if((BMI>31.1 && BMI<=45)&&spol=='m'){
			izraz= "previsok BMI";
			
		}else if((BMI>45)&&(spol=='m'||spol=='f')){
			izraz= "izrazito visok BMI";
		}else{
			izraz="greska";
		}
		return izraz;
		
	}
	
	
	

}
