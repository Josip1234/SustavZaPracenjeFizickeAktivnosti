package com.example.physical.activity.regist;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.physical.activity.database.PhysicalActivityDatabase;

@Component("Registracija")
public class Registration implements registeryourself {
private int id;
private String ime;
private String prezime;
private char spol;

private String datumr;
private String drzavar;
private String drzavap;
private String email;
private String sifra;
private String zanimanje;
private PhysicalActivityDatabase db;
public Registration() {
	this.id=1;
	this.ime="Josip";
	this.prezime="Bošnjak";
	this.spol='m';
	this.datumr="1992-11-1992";
	
	this.drzavar="Švicarska";
	this.drzavap="Hrvatska";
	this.email="jbosnjak@unipu.hr";
	this.sifra="test";
	this.zanimanje="student";
	this.db=db;
}
public void register(){

	System.out.println("Successfull registration");
};

@Autowired
public Registration(int id,String ime,String prezime,char spol,String datumr,String drzavar,String drzavap,String email,String sifra,String zanimanje,PhysicalActivityDatabase db){
	this.id=id;
	this.ime=ime;
	this.prezime=prezime;
	this.spol=spol;
	this.datumr=datumr;
	
	this.drzavar=drzavar;
	this.drzavap=drzavap;
	this.email=email;
	this.sifra=sifra;
	this.zanimanje=zanimanje;
	this.db=db;
}

@Autowired
public Registration(PhysicalActivityDatabase db){
	this.db=db;
	db.InsertUser();
	register();
}

public void setId(int id) {
	this.id = id;
}
public void setIme(String ime) {
	this.ime = ime;
}
public void setPrezime(String prezime) {
	this.prezime = prezime;
}
public void setSpol(char spol) {
	this.spol = spol;
}
public void setDatumr(String datumr) {
	this.datumr = datumr;
}
public void setDrzavar(String drzavar) {
	this.drzavar = drzavar;
}
public void setDrzavap(String drzavap) {
	this.drzavap = drzavap;
}
public void setEmail(String email) {
	this.email = email;
}
public void setSifra(String sifra) {
	this.sifra = sifra;
}
public void setZanimanje(String zanimanje) {
	this.zanimanje = zanimanje;
}

public int getId() {
	return id;
}
public String getIme() {
	return ime;
}
public String getPrezime() {
	return prezime;
}
public char getSpol() {
	return spol;
}
public String getDatumr() {
	return datumr;
}
public String getDrzavar() {
	return drzavar;
}
public String getDrzavap() {
	return drzavap;
}
public String getEmail() {
	return email;
}
public String getSifra() {
	return sifra;
}
public String getZanimanje() {
	return zanimanje;
}





}
