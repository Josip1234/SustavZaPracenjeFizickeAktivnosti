package activity.physical.example.com.josip.physicalactivity.pomocneKlase;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import activity.physical.example.com.josip.physicalactivity.model.WalkingActivity;

/**
 * Created by Korisnik on 9.3.2018..
 */

public class StatistickiIzracuni {
    private int ukupanBrojKoraka;
    private double kilometri;
    private double prosjecnaBrzina;

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
        for (Integer korak: koraci
             ) {
            sum+=korak;

        }
        setUkupanBrojKoraka(sum);

    }
    public void izracunajUkupnoPrijedjenjeKilometre(List<Double> kilometri){
        double sum=0.0;
        for (double kilometar:kilometri
             ) {
            sum+=kilometar;
        }
        setKilometri(sum);

    }

    public void izracunajprosjecnuBrzinu(List<Double> brzina){
        int velicinaListe=brzina.size();
        double prosjek=0.00;
        double suma=0.00;
        for (Double brzin:brzina
             ) {
            suma+=brzin;
        }
        prosjek=suma/velicinaListe;
        setProsjecnaBrzina(prosjek);
    }


    }