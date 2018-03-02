package com.josip.physical.activity.planiranje;

import org.springframework.stereotype.Component;

@Component("Model planiranja")
public class ModelPlaniranja {
   private int brojKoraka;
   private String adresa;
   private String vrijeme;
   private double longituda;
   private double latituda;

   public ModelPlaniranja() {
	   
   }
public ModelPlaniranja(int brojKoraka, String adresa, String vrijeme, double longituda, double latituda) {
	this.brojKoraka = brojKoraka;
	this.adresa = adresa;
	this.vrijeme = vrijeme;
	this.longituda = longituda;
	this.latituda = latituda;
}
public int getBrojKoraka() {
	return brojKoraka;
}
public void setBrojKoraka(int brojKoraka) {
	this.brojKoraka = brojKoraka;
}
public String getAdresa() {
	return adresa;
}
public void setAdresa(String adresa) {
	this.adresa = adresa;
}
public String getVrijeme() {
	return vrijeme;
}
public void setVrijeme(String vrijeme) {
	this.vrijeme = vrijeme;
}
public double getLongituda() {
	return longituda;
}
public void setLongituda(double longituda) {
	this.longituda = longituda;
}
public double getLatituda() {
	return latituda;
}
public void setLatituda(double latituda) {
	this.latituda = latituda;
}
   
}
