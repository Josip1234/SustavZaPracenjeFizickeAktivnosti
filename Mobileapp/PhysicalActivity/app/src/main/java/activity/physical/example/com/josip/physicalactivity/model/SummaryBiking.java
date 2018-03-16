package activity.physical.example.com.josip.physicalactivity.model;

/**
 * Created by Korisnik on 12.3.2018..
 */

public class SummaryBiking {
    public String korisnik;
    public double ukupnaUdaljenost;
    public double ukupnoVrijemeAktivnosti;
    public double prosjecnaBrzinaUkm;
    public String period;

    public SummaryBiking() {

    }

    public SummaryBiking(String korisnik, double ukupnaUdaljenost, double ukupnoVrijemeAktivnosti, double prosjecnaBrzinaUkm, String period) {
        this.korisnik = korisnik;
        this.ukupnaUdaljenost = ukupnaUdaljenost;
        this.ukupnoVrijemeAktivnosti = ukupnoVrijemeAktivnosti;
        this.prosjecnaBrzinaUkm = prosjecnaBrzinaUkm;
        this.period = period;
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



    public double getUkupnoVrijemeAktivnosti() {
        return ukupnoVrijemeAktivnosti;
    }

    public void setUkupnoVrijemeAktivnosti(double ukupnoVrijemeAktivnosti) {
        this.ukupnoVrijemeAktivnosti = ukupnoVrijemeAktivnosti;
    }





    public double getProsjecnaBrzinaUkm() {
        return prosjecnaBrzinaUkm;
    }

    public void setProsjecnaBrzinaUkm(double prosjecnaBrzinaUkm) {
        this.prosjecnaBrzinaUkm = prosjecnaBrzinaUkm;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


}
