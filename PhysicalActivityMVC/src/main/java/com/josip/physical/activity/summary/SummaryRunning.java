package com.josip.physical.activity.summary;

import org.springframework.stereotype.Component;

@Component("TrcanjeUkupno")
public class SummaryRunning {
	public String korisnik;
    public double ukupnaUdaljenost;
    public double prosjecnaUdaljenost;
    public double ukupnoVrijemeAktivnosti;
    public double prosjecnoVrijemeAktivnosti;
    public double prosjecnaBrzinaUkm;
    public double period;	
public SummaryRunning() {
	
}
public String getKorisnik() {
	return korisnik;
}
public void setKorisnik(String korisnik) {
	this.korisnik = korisnik;
}
public double getUkupnaUdaljenost() {
	return ukupnaUdaljenost;
}
public void setUkupnaUdaljenost(double ukupnaUdaljenost) {
	this.ukupnaUdaljenost = ukupnaUdaljenost;
}
public double getProsjecnaUdaljenost() {
	return prosjecnaUdaljenost;
}
public void setProsjecnaUdaljenost(double prosjecnaUdaljenost) {
	this.prosjecnaUdaljenost = prosjecnaUdaljenost;
}
public double getUkupnoVrijemeAktivnosti() {
	return ukupnoVrijemeAktivnosti;
}
public void setUkupnoVrijemeAktivnosti(double ukupnoVrijemeAktivnosti) {
	this.ukupnoVrijemeAktivnosti = ukupnoVrijemeAktivnosti;
}
public double getProsjecnoVrijemeAktivnosti() {
	return prosjecnoVrijemeAktivnosti;
}
public void setProsjecnoVrijemeAktivnosti(double prosjecnoVrijemeAktivnosti) {
	this.prosjecnoVrijemeAktivnosti = prosjecnoVrijemeAktivnosti;
}
public double getProsjecnaBrzinaUkm() {
	return prosjecnaBrzinaUkm;
}
public void setProsjecnaBrzinaUkm(double prosjecnaBrzinaUkm) {
	this.prosjecnaBrzinaUkm = prosjecnaBrzinaUkm;
}
public double getPeriod() {
	return period;
}
public void setPeriod(double period) {
	this.period = period;
}

}
