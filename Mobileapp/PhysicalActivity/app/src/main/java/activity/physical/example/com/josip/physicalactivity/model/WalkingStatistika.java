package activity.physical.example.com.josip.physicalactivity.model;

/**
 * Created by Korisnik on 12.3.2018..
 */

public class WalkingStatistika {
    private String email;
    private double ukupnaUdaljenost;
    private double prosjecnaUdaljenost;
    private double ukupnoVrijemeAktivnosti;
    private double prosjecnoVrijemeAktivnosti;
    private double prosjecnaBrzinaUkm;
    private String period;
    private int ukupanBrojKoraka;
    private double prosjecanBrojKoraka;

    public WalkingStatistika() {

    }

    public WalkingStatistika(String email, double ukupnaUdaljenost, double prosjecnaUdaljenost,
                             double ukupnoVrijemeAktivnosti, double prosjecnoVrijemeAktivnosti, double prosjecnaBrzinaUkm, String period,
                             int ukupanBrojKoraka, double prosjecanBrojKoraka) {
        this.email = email;
        this.ukupnaUdaljenost = ukupnaUdaljenost;
        this.prosjecnaUdaljenost = prosjecnaUdaljenost;
        this.ukupnoVrijemeAktivnosti = ukupnoVrijemeAktivnosti;
        this.prosjecnoVrijemeAktivnosti = prosjecnoVrijemeAktivnosti;
        this.prosjecnaBrzinaUkm = prosjecnaBrzinaUkm;
        this.period = period;
        this.ukupanBrojKoraka = ukupanBrojKoraka;
        this.prosjecanBrojKoraka = prosjecanBrojKoraka;
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

    public double getProsjecanBrojKoraka() {
        return prosjecanBrojKoraka;
    }

    public void setProsjecanBrojKoraka(double prosjecanBrojKoraka) {
        this.prosjecanBrojKoraka = prosjecanBrojKoraka;
    }

}
