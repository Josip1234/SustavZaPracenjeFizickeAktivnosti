package com.josip.physical.activity.biking;

public class BikingActivity {
private String vrijemeAktivnosti;
private double brzinaUkm;
private String lokacija;
private double longitude;
private double latitude;
private String korisnik;
private double udaljenost;
/**
 * @param vrijemeAktivnosti
 * @param brzinaUkm
 * @param lokacija
 * @param longitude
 * @param latitude
 * @param korisnik
 * @param udaljenost
 */
public BikingActivity(String vrijemeAktivnosti, double brzinaUkm, String lokacija, double longitude, double latitude,
		String korisnik, double udaljenost) {
	this.vrijemeAktivnosti = vrijemeAktivnosti;
	this.brzinaUkm = brzinaUkm;
	this.lokacija = lokacija;
	this.longitude = longitude;
	this.latitude = latitude;
	this.korisnik = korisnik;
	this.udaljenost = udaljenost;
}
public String getVrijemeAktivnosti() {
	return vrijemeAktivnosti;
}
public void setVrijemeAktivnosti(String vrijemeAktivnosti) {
	this.vrijemeAktivnosti = vrijemeAktivnosti;
}
public double getBrzinaUkm() {
	return brzinaUkm;
}
public void setBrzinaUkm(double brzinaUkm) {
	this.brzinaUkm = brzinaUkm;
}
public String getLokacija() {
	return lokacija;
}
public void setLokacija(String lokacija) {
	this.lokacija = lokacija;
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
public String getKorisnik() {
	return korisnik;
}
public void setKorisnik(String korisnik) {
	this.korisnik = korisnik;
}
public double getUdaljenost() {
	return udaljenost;
}
public void setUdaljenost(double udaljenost) {
	this.udaljenost = udaljenost;
}

}
