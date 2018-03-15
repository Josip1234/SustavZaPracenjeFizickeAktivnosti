package activity.physical.example.com.josip.physicalactivity.model;

/**
 * Created by Korisnik on 12.3.2018..
 */

public class WalkingStatistika {
    private String email;
    private double ukupnaUdaljenost;
    private double ukupnoVrijemeAktivnosti;
    private double prosjecnaBrzinaUkm;
    private String period;
    private int ukupanBrojKoraka;


    public WalkingStatistika() {

    }

    public WalkingStatistika(String email, double ukupnaUdaljenost,  double ukupnoVrijemeAktivnosti,  double prosjecnaBrzinaUkm, String period, int ukupanBrojKoraka ) {
        this.email = email;
        this.ukupnaUdaljenost = ukupnaUdaljenost;
        this.ukupnoVrijemeAktivnosti = ukupnoVrijemeAktivnosti;

        this.prosjecnaBrzinaUkm = prosjecnaBrzinaUkm;
        this.period = period;
        this.ukupanBrojKoraka = ukupanBrojKoraka;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getUkupanBrojKoraka() {
        return ukupanBrojKoraka;
    }

    public void setUkupanBrojKoraka(int ukupanBrojKoraka) {
        this.ukupanBrojKoraka = ukupanBrojKoraka;
    }



}
