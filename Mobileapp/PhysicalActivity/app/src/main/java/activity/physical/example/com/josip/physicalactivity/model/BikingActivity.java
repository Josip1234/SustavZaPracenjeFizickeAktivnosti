package activity.physical.example.com.josip.physicalactivity.model;

/**
 * Created by Josip on 1.11.2017..
 */

public class BikingActivity {
    private String vrijemeAktivnosti;
    private double brzinaUkm;
    private String korisnik;
    private double udaljenost;
    private String datum;
    public BikingActivity(){

    }


    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public BikingActivity(String vrijemeAktivnosti, double brzinaUkm, String korisnik, double udaljenost, String datum) {
        this.vrijemeAktivnosti = vrijemeAktivnosti;
        this.brzinaUkm = brzinaUkm;

        this.korisnik = korisnik;
        this.udaljenost = udaljenost;
        this.datum=datum;

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
