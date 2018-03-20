package activity.physical.example.com.josip.physicalactivity.pomocneKlase;

import java.util.List;

/**
 * Created by Korisnik on 9.3.2018..
 */

public class StatistickiIzracuni {
    private int ukupanBrojKoraka;
    private double kilometri;
    private double prosjecnaBrzina;
    private double prosjecnaUdaljenost;
    private double prosjekKoraka;

    public double getProsjekKoraka() {
        return prosjekKoraka;
    }

    public void setProsjekKoraka(double prosjekKoraka) {
        this.prosjekKoraka = prosjekKoraka;
    }

    public double getProsjecnaUdaljenost() {
        return prosjecnaUdaljenost;
    }

    public void setProsjecnaUdaljenost(double prosjecnaUdaljenost) {
        this.prosjecnaUdaljenost = prosjecnaUdaljenost;
    }

    public double getProsjecnaBrzina() {
        return prosjecnaBrzina;
    }

    public void setProsjecnaBrzina(double prosjecnaBrzina) {
        this.prosjecnaBrzina = prosjecnaBrzina;
    }

    public double getKilometri() {
        return kilometri;
    }

    public void setKilometri(double kilometri) {
        this.kilometri = kilometri;
    }

    public int getUkupanBrojKoraka() {
        return ukupanBrojKoraka;
    }

    public void setUkupanBrojKoraka(int ukupanBrojKoraka) {
        this.ukupanBrojKoraka = ukupanBrojKoraka;
    }

   
    public void izracunajUkupanBrojKoraka(List<Integer> koraci){
        int sum=0;
        int velicina=koraci.size();
        for (Integer korak: koraci
             ) {
            sum+=korak;

        }
        setUkupanBrojKoraka(sum);
        setProsjekKoraka(sum/velicina);

    }
    public void izracunajUkupnoPrijedjenjeKilometre(List<Double> kilometri){
        double sum=0.0;
        int velicina=kilometri.size();
        for (double kilometar:kilometri
             ) {
            sum+=kilometar;
        }

        setKilometri(sum);
        setProsjecnaUdaljenost(sum/velicina);

    }

    public void izracunajprosjecnuBrzinu(List<Double> brzina, int brojMjerenja){

        double prosjek=0.00;
        double suma=0.00;
        for (Double brzin:brzina
             ) {
            suma+=brzin;
        }
        prosjek=suma/brojMjerenja;
        setProsjecnaBrzina(prosjek);
    }


    }
