package activity.physical.example.com.josip.physicalactivity.model;

/**
 * Created by Josip on 1.11.2017..
 */

public class SummaryActivity {

    private String korisnik;
    private int ukupanBrojKoraka;
    private String ukupnoVrijeme;
    private double prijedjeniKilometri;
    private double prosjecnaBrzina;

    public SummaryActivity( String korisnik, int ukupanBrojKoraka, String ukupnoVrijeme, double prijedjeniKilometri, double prosjecnaBrzina) {

        this.korisnik = korisnik;
        this.ukupanBrojKoraka = ukupanBrojKoraka;
        this.ukupnoVrijeme = ukupnoVrijeme;
        this.prijedjeniKilometri = prijedjeniKilometri;
        this.prosjecnaBrzina = prosjecnaBrzina;
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
