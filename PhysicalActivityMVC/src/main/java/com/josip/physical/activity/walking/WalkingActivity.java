package com.josip.physical.activity.walking;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;


//@Component("walking")
public class WalkingActivity {
/*private double udaljenost;
private String vrijemeAktivnosti;
private int koraci;
private double brzinaUkm;
private String korisnik;
private String datumIvrijeme;
public WalkingActivity() {
	
}
public WalkingActivity(double udaljenost, String vrijemeAktivnosti, int koraci,  double brzinaUkm, String korisnik,String datumIvrijeme) {
	this.udaljenost = udaljenost;
	this.vrijemeAktivnosti = vrijemeAktivnosti;
	this.koraci = koraci;
	this.brzinaUkm = brzinaUkm;
	this.korisnik = korisnik;
	this.datumIvrijeme= datumIvrijeme;
}
public String getDatumIvrijeme() {
	return datumIvrijeme;
}
public void setDatumIvrijeme(String datumIvrijeme) {
	this.datumIvrijeme = datumIvrijeme;
}
public double getUdaljenost() {
	return udaljenost;
}
public void setUdaljenost(double udaljenost) {
	this.udaljenost = udaljenost;
}
public String getVrijemeAktivnosti() {
	return vrijemeAktivnosti;
}
public void setVrijemeAktivnosti(String vrijemeAktivnosti) {
	this.vrijemeAktivnosti = vrijemeAktivnosti;
}
public int getKoraci() {
	return koraci;
}
public void setKoraci(int koraci) {
	this.koraci = koraci;
}

public double getBrzinaUkm() {
	return brzinaUkm;
}
public void setBrzinaUkm(double brzinaUkm) {
	this.brzinaUkm = brzinaUkm;
}
public String getKorisnik() {
	return korisnik;
}
public void setKorisnik(String korisnik) {
	this.korisnik = korisnik;
}
/*
@Override
public String toString(){
	
	return udaljenost+vrijemeAktivnosti+koraci+adresa+longitude+latitude+brzinaUkm+korisnik;
	
}*/
}
