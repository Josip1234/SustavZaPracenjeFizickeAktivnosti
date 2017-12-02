package com.josip.physical.activity.regist;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component("Registracija")
public class Registration {
@NotNull
@Size(min=11,max=11)
private String OIB;
@NotNull
private String ime;
@NotNull
private String prezime;
@NotNull
private String spol;
@NotNull
private String datumr;
@NotNull
private String email;
@NotNull
private String sifra;



public Registration() {
	this.OIB="";
	this.ime="";
	this.prezime="";
	this.spol="";
	this.datumr="0000-00-00";
	this.email="";
	this.sifra="";
	
}
public void register(){

	System.out.println("Successfull registration");
};


public Registration(String OIB,String ime,String prezime,String spol,String datumr,String email,String sifra){
	this.OIB=OIB;
	this.ime=ime;
	this.prezime=prezime;
	this.spol=spol;
	this.datumr=datumr;
	
	this.email=email;
	this.sifra=sifra;
	
}




public void setOIB(String OIB) {
	this.OIB = OIB;
}
public void setIme(String ime) {
	this.ime = ime;
}
public void setPrezime(String prezime) {
	this.prezime = prezime;
}
public void setSpol(String spol) {
	this.spol = spol;
}
public void setDatumr(String datumr) {
	this.datumr = datumr;
}


public void setEmail(String email) {
	this.email = email;
}
public void setSifra(String sifra) {
	this.sifra = sifra;
}


public String getOIB() {
	return OIB;
}
public String getIme() {
	return ime;
}
public String getPrezime() {
	return prezime;
}
public String getSpol() {
	return spol;
}
public String getDatumr() {
	return datumr;
}


public String getEmail() {
	return email;
}
public String getSifra() {
	return sifra;
}

/*
@Override
public String toString(){
	
	return getOIB()+","+getIme()+","+getPrezime()+","+getSpol()+","+getDatumr()+","+getEmail()+","+getSifra();
	
}
*/

}
