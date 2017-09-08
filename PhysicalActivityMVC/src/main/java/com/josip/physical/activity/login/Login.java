package com.josip.physical.activity.login;

import com.josip.physical.activity.baza.PhysicalActivityDatabase;

import java.util.Map;

import javax.validation.constraints.*;

import org.springframework.stereotype.Component;

@Component("Prijava")
public class Login implements log {

	
    private String username;
    
	
    private String sifra;
    private boolean autoriziran;
	private PhysicalActivityDatabase db;
    
    
   public Login(){
	   this.username="jbosnjak3@gmail.com";
	   this.sifra="bls";
	   this.autoriziran=true;
   }
    
   
    
	public Login(String email, String sifra,boolean autoriziran) {
		this.username=username;
    	this.sifra=sifra;
    	this.autoriziran=autoriziran;
	}

	public void setAutoriziran(boolean autoriziran) {
		this.autoriziran = autoriziran;
	}

	public boolean isAutoriziran() {
		return autoriziran;
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



@Override
   public String toString(){
	   StringBuilder sb = new StringBuilder();
	   sb.append("\nLogin podaci\n");
	   sb.append("Korisničko ime:"+getUsername()+"\n");
	   sb.append("Lozinka:"+getSifra()+"\n");
	   return sb.toString();
   }
	

}
