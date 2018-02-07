package com.josip.physical.activity.regist;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.baza.PhysicalActivityDatabase;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component("Registracija")
public class Registration  {
@NotNull
@Size(min=11,max=11,message="{OIB.size}")
private String OIB;
@NotNull
@Size(max=50,message="{ime.size}")
private String ime;
@NotNull
@Size(max=50,message="{prezime.size}")
private String prezime;
@NotNull
private String spol;
@NotNull
private String datumr;
@NotNull
@Size(max=50,min=10,message="{email.size}")
@Email(message="{email.valid}")
private String email;
@NotNull
@Size(min=8,message="{sifra.size}")
private String sifra;



public Registration() {
	
	
}



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
public String getPrezime() throws UnsupportedEncodingException {
	return new String(prezime.getBytes ("iso-8859-1"), "UTF-8");
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


@Override
public String toString(){
	
	return "Registration OIB:"+OIB+",ime:"+ime+",prezime:"+prezime+",spol:"+spol+",datumr:"+datumr+",Email:"+email+",sifra:"+sifra+"";
	
}


}
