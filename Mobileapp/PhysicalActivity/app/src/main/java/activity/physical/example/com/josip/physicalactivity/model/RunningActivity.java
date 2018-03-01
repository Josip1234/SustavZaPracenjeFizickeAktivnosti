package activity.physical.example.com.josip.physicalactivity.model;

/**
 * Created by Josip on 31.10.2017..
 */

public class RunningActivity {
    private String vrijemeAktivnosti;
    private double brzinaUkm;
    private String lokacija;
    private double longitude;
    private double latitude;
    private String korisnik;
    private double udaljenost;
    private String datum;

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public RunningActivity(){

    }
    public RunningActivity(String vrijemeAktivnosti, double brzinaUkm, String lokacija, double longitude, double latitude, String korisnik, double udaljenost) {
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
