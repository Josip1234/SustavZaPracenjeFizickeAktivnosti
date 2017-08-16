package com.josip.physical.activity.regist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.baza.PhysicalActivityDatabase;



import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



@Component("Registracija")
public class Registration {
private String OIB;
private String ime;
private String prezime;
private String spol;

private String datumr;
private String email;
private String sifra;

private PhysicalActivityDatabase db;
public Registration() {
	this.OIB="14520369430";
	this.ime="Josip";
	this.prezime="Bošnjak";
	this.spol="m";
	this.datumr="1992-11-1992";
	this.email="jbosnjak@unipu.hr";
	this.sifra="test";
	
	this.db=db;
}
public void register(){

	System.out.println("Successfull registration");
};


public Registration(String OIB,String ime,String prezime,String spol,String datumr,String email,String sifra,PhysicalActivityDatabase db){
	this.OIB=OIB;
	this.ime=ime;
	this.prezime=prezime;
	this.spol=spol;
	this.datumr=datumr;
	
	this.email=email;
	this.sifra=sifra;
	
	this.db=db;
}


public Registration(PhysicalActivityDatabase db){
	this.db=db;
	db.InsertUser();
	register();
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






}
