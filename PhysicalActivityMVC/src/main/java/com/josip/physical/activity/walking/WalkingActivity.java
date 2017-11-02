package com.josip.physical.activity.walking;

public class WalkingActivity {
private double udaljenost;
private String vrijemeAktivnosti;
private int koraci;
private String adresa;
private double longitude;
private double latitude;
private double brzinaUkm;
private String korisnik;
/**
 * @param udaljenost
 * @param vrijemeAktivnosti
 * @param koraci
 * @param adresa
 * @param longitude
 * @param latitude
 * @param brzinaUkm
 * @param korisnik
 */
public WalkingActivity(double udaljenost, String vrijemeAktivnosti, int koraci, String adresa, double longitude,
		double latitude, double brzinaUkm, String korisnik) {
	this.udaljenost = udaljenost;
	this.vrijemeAktivnosti = vrijemeAktivnosti;
	this.koraci = koraci;
	this.adresa = adresa;
	this.longitude = longitude;
	this.latitude = latitude;
	this.brzinaUkm = brzinaUkm;
	this.korisnik = korisnik;
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
public String getAdresa() {
	return adresa;
}
public void setAdresa(String adresa) {
	this.adresa = adresa;
}
public double getLongitude() {
	return longitude;
}
public void setLongitude(double longitude) {
	this.longitude = longitude;
}
public double getLatitude() {
	return latitude;
}
public void setLatitude(double latitude) {
	this.latitude = latitude;
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

}
