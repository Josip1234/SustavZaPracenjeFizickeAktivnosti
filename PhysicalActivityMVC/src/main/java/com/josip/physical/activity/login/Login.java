package com.josip.physical.activity.login;

import com.josip.physical.activity.baza.PhysicalActivityDatabase;
import javax.validation.constraints.*;

import org.springframework.stereotype.Component;

@Component("Prijava")
public class Login implements log {

	
    private String username;
    
    private String sifra;
	private PhysicalActivityDatabase db;
    
    
   public Login(){
	   this.username="jbosnjak3@gmail.com";
	   this.sifra="eecae4Ai";
   }
    
    public Login(String username,String sifra){
    	this.username=username;
    	this.sifra=sifra;
    	
    }
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	
	
	public void prijavi_se(){
         System.out.println("Korisnik je logiran");
	}
   
	

}
