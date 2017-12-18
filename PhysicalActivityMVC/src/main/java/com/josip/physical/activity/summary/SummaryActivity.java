package com.josip.physical.activity.summary;

public class SummaryActivity {
private String aktivnost;
private String korisnik;
private int ukupanBrojKoraka;
private String ukupnoVrijeme;
private double prijedjeniKilometri;
private double prosjecnaBrzina;
/**
 * @param aktivnost
 * @param korisnik
 * @param ukupanBrojKoraka
 * @param ukupnoVrijeme
 * @param prijedjeniKilometri
 * @param prosjecnaBrzina
 */
public SummaryActivity(String aktivnost, String korisnik, int ukupanBrojKoraka, String ukupnoVrijeme,
		double prijedjeniKilometri, double prosjecnaBrzina) {
	this.aktivnost = aktivnost;
	this.korisnik = korisnik;
	this.ukupanBrojKoraka = ukupanBrojKoraka;
	this.ukupnoVrijeme = ukupnoVrijeme;
	this.prijedjeniKilometri = prijedjeniKilometri;
	this.prosjecnaBrzina = prosjecnaBrzina;
}
public String getAktivnost() {
	return aktivnost;
}
public void setAktivnost(String aktivnost) {
	this.aktivnost = aktivnost;
}
public String getKorisnik() {
	return korisnik;
}
public void setKorisnik(String korisnik) {
	this.korisnik = korisnik;
}
public int getUkupanBrojKoraka() {
	return ukupanBrojKoraka;
}
public void setUkupanBrojKoraka(int ukupanBrojKoraka) {
	this.ukupanBrojKoraka = ukupanBrojKoraka;
}
public String getUkupnoVrijeme() {
	return ukupnoVrijeme;
}
public void setUkupnoVrijeme(String ukupnoVrijeme) {
	this.ukupnoVrijeme = ukupnoVrijeme;
}
public double getPrijedjeniKilometri() {
	return prijedjeniKilometri;
}
public void setPrijedjeniKilometri(double prijedjeniKilometri) {
	this.prijedjeniKilometri = prijedjeniKilometri;
}
public double getProsjecnaBrzina() {
	return prosjecnaBrzina;
}
public void setProsjecnaBrzina(double prosjecnaBrzina) {
	this.prosjecnaBrzina = prosjecnaBrzina;
}

}
