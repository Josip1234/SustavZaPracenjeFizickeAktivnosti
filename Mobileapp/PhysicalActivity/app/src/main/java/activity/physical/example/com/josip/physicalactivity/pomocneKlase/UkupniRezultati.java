package activity.physical.example.com.josip.physicalactivity.pomocneKlase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import activity.physical.example.com.josip.physicalactivity.model.SummaryActivity;
import activity.physical.example.com.josip.physicalactivity.model.SummaryBiking;
import activity.physical.example.com.josip.physicalactivity.model.SummaryRunning;
import activity.physical.example.com.josip.physicalactivity.model.WalkingStatistika;

/**
 * Created by Korisnik on 17.3.2018..
 */

public class UkupniRezultati {
    public UkupniRezultati() {
    }
    public SummaryActivity ukupniRezultati(WalkingStatistika walk, SummaryRunning run, SummaryBiking bike){

        SummaryActivity summaryActivity = new SummaryActivity();
        summaryActivity.setKorisnik(walk.getEmail());
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String vrijeme=sdf.format(date);
        summaryActivity.setDatum(vrijeme);
        summaryActivity.setPrijedjeniKilometri((walk.getUkupnaUdaljenost()+run.getUkupnaUdaljenost()+bike.getUkupnaUdaljenost()));
        summaryActivity.setProsjecnaBrzina(((walk.getProsjecnaBrzinaUkm()+run.getProsjecnaBrzinaUkm()+bike.getProsjecnaBrzinaUkm())/3));
        summaryActivity.setUkupanBrojKoraka(walk.getUkupanBrojKoraka());
        summaryActivity.setUkupnoVrijeme((walk.getUkupnoVrijemeAktivnosti()+run.getUkupnoVrijemeAktivnosti()+bike.getUkupnoVrijemeAktivnosti()));


        return summaryActivity;
    }
}
