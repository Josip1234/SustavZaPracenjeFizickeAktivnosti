package activity.physical.example.com.josip.physicalactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import activity.physical.example.com.josip.physicalactivity.model.SummaryActivity;
import activity.physical.example.com.josip.physicalactivity.model.SummaryBiking;
import activity.physical.example.com.josip.physicalactivity.model.SummaryRunning;
import activity.physical.example.com.josip.physicalactivity.model.WalkingActivity;
import activity.physical.example.com.josip.physicalactivity.model.WalkingStatistika;
import activity.physical.example.com.josip.physicalactivity.pomocneKlase.PosaljiUkupnePodatke;
import activity.physical.example.com.josip.physicalactivity.pomocneKlase.StatistickiIzracuni;
import activity.physical.example.com.josip.physicalactivity.pomocneKlase.UkupniRezultati;

public class RezultActivity extends AppCompatActivity {

    private TextView mUkupanBrojKoraka;
    private TextView mVus;
    private TextView mkilom;
    private WalkingStatistika stats;
    private TextView mProsjecnaBrzina;
    private SummaryBiking sumBike;
    private TextView vrijemeBicikliranja;
    private TextView prijedjenjiKilometri;
    private TextView mProsjekBrzine;
    private SummaryRunning summaryRunning;
    private TextView mVrijemeTrcanja;
    private TextView mKilo;
    private TextView mProsjek;
    private UkupniRezultati ukupniRezultati;
    private SummaryActivity summaryActivity;
    private TextView ukKorakAkt;
    private TextView ukVrijemeAkt;
    private TextView ukUdaljenostAkt;
    private TextView ukProsjekBrzinaAkti;

    public WalkingStatistika procitajPodatke(WalkingStatistika stats) throws IOException, JSONException {

        int vrijednost=0;
        String vrijeme="";

        String naziv = "UkupnoHodanja.json";


        FileInputStream fis = openFileInput(naziv);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }
        bis.close();
        fis.close();
        double ukupno=0.00;
        JSONArray data = new JSONArray(b.toString());
        StringBuffer prijavaBuffer = new StringBuffer();
        for (int i = 0; i < data.length(); i++) {
            String korisnik=data.getJSONObject(i).getString("korisnik");
            ukupno=data.getJSONObject(i).getDouble("ukupnaUdaljenost");
            double ukupnoVrijemeAktivnosti=data.getJSONObject(i).getDouble("ukupnoVrijemeAktivnosti");
            double prosjecnaBrzinaUkilometrima=data.getJSONObject(i).getDouble("prosjecnaBrzina");
            String datum=data.getJSONObject(i).getString("datum");
            int ukupanBrojKoraka=data.getJSONObject(i).getInt("ukupanBrojKoraka");
            prijavaBuffer.append(korisnik + "" + ukupno + "" + ukupnoVrijemeAktivnosti+ "" + prosjecnaBrzinaUkilometrima + "" + datum+" "+ukupanBrojKoraka);
            stats.setUkupanBrojKoraka(ukupanBrojKoraka);
            stats.setUkupnaUdaljenost(ukupno);
            stats.setUkupnoVrijemeAktivnosti(ukupnoVrijemeAktivnosti);
            stats.setProsjecnaBrzinaUkm(prosjecnaBrzinaUkilometrima);
            stats.setEmail(korisnik);
            stats.setPeriod(datum);
            Log.i("poruka", "pročitan json");


        }
        return stats;
    }

    public SummaryBiking procitajPodatke(SummaryBiking stats) throws IOException, JSONException {

        int vrijednost=0;
        String vrijeme="";

        String naziv = "UkupnoBicikliranja.json";


        FileInputStream fis = openFileInput(naziv);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }
        bis.close();
        fis.close();
        double ukupno=0.00;
        JSONArray data = new JSONArray(b.toString());
        StringBuffer prijavaBuffer = new StringBuffer();
        for (int i = 0; i < data.length(); i++) {
            String korisnik=data.getJSONObject(i).getString("korisnik");
            ukupno=data.getJSONObject(i).getDouble("ukupnaUdaljenost");
            double ukupnoVrijemeAktivnosti=data.getJSONObject(i).getDouble("ukupnoVrijemeAktivnosti");
            double prosjecnaBrzinaUkilometrima=data.getJSONObject(i).getDouble("prosjecnaBrzina");
            String datum=data.getJSONObject(i).getString("datum");

            prijavaBuffer.append(korisnik + "" + ukupno + "" + ukupnoVrijemeAktivnosti+ "" + prosjecnaBrzinaUkilometrima + "" + datum+" ");

            stats.setUkupnaUdaljenost(ukupno);
            stats.setUkupnoVrijemeAktivnosti(ukupnoVrijemeAktivnosti);
            stats.setProsjecnaBrzinaUkm(prosjecnaBrzinaUkilometrima);
            stats.setKorisnik(korisnik);
            stats.setPeriod(datum);
            Log.i("poruka", "pročitan json");


        }
        return stats;
    }

    public SummaryRunning procitajPodatke(SummaryRunning stats) throws IOException, JSONException {

        int vrijednost=0;
        String vrijeme="";

        String naziv = "UkupnoTrcanja.json";


        FileInputStream fis = openFileInput(naziv);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }
        bis.close();
        fis.close();
        double ukupno=0.00;
        JSONArray data = new JSONArray(b.toString());
        StringBuffer prijavaBuffer = new StringBuffer();
        for (int i = 0; i < data.length(); i++) {
            String korisnik=data.getJSONObject(i).getString("korisnik");
            ukupno=data.getJSONObject(i).getDouble("ukupnaUdaljenost");
            double ukupnoVrijemeAktivnosti=data.getJSONObject(i).getDouble("ukupnoVrijemeAktivnosti");
            double prosjecnaBrzinaUkilometrima=data.getJSONObject(i).getDouble("prosjecnaBrzina");
            String datum=data.getJSONObject(i).getString("datum");

            prijavaBuffer.append(korisnik + "" + ukupno + "" + ukupnoVrijemeAktivnosti+ "" + prosjecnaBrzinaUkilometrima + "" + datum+" ");

            stats.setUkupnaUdaljenost(ukupno);
            stats.setUkupnoVrijemeAktivnosti(ukupnoVrijemeAktivnosti);
            stats.setProsjecnaBrzinaUkm(prosjecnaBrzinaUkilometrima);
            stats.setKorisnik(korisnik);
            stats.setPeriod(datum);
            Log.i("poruka", "pročitan json");


        }
        return stats;
    }




    public String pretvoriUminute(double vrijeme){
        double sekunde=vrijeme/1000;
        double minute=sekunde/60;
        return String.valueOf(minute);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezult);
        int rezultat=0;
        stats=new WalkingStatistika();
        try {
            stats=procitajPodatke(stats);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mUkupanBrojKoraka=(TextView) findViewById(R.id.ukbk);
        mUkupanBrojKoraka.setText(String.valueOf(stats.getUkupanBrojKoraka()));

        mVus=(TextView) findViewById(R.id.vus);
        mVus.setText(pretvoriUminute(stats.getUkupnoVrijemeAktivnosti()));

        mkilom=(TextView) findViewById(R.id.pk);
        mkilom.setText(String.valueOf(stats.getUkupnaUdaljenost()));

        mProsjecnaBrzina=(TextView) findViewById(R.id.pb);
        mProsjecnaBrzina.setText(String.valueOf(stats.getProsjecnaBrzinaUkm()));

        sumBike=new SummaryBiking();
        try {
            sumBike=procitajPodatke(sumBike);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        vrijemeBicikliranja=(TextView) findViewById(R.id.vrbic);
        vrijemeBicikliranja.setText(String.valueOf(pretvoriUminute(sumBike.getUkupnoVrijemeAktivnosti())));

        prijedjenjiKilometri=(TextView) findViewById(R.id.prkmubic);
        prijedjenjiKilometri.setText(String.valueOf(sumBike.getUkupnaUdaljenost()));

        mProsjekBrzine=(TextView) findViewById(R.id.prbrubic);
        mProsjekBrzine.setText(String.valueOf(sumBike.getProsjecnaBrzinaUkm()));

        summaryRunning = new SummaryRunning();
        try {
            summaryRunning=procitajPodatke(summaryRunning);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mVrijemeTrcanja=(TextView) findViewById(R.id.vrt);
        mVrijemeTrcanja.setText(String.valueOf(pretvoriUminute(summaryRunning.getUkupnoVrijemeAktivnosti())));
        mKilo=(TextView) findViewById(R.id.prkrutr);
        mKilo.setText(String.valueOf(summaryRunning.getUkupnaUdaljenost()));
        mProsjek=(TextView) findViewById(R.id.probrztrc);
        mProsjek.setText(String.valueOf(summaryRunning.getProsjecnaBrzinaUkm()));


        ukupniRezultati=new UkupniRezultati();
        summaryActivity=new SummaryActivity();
        summaryActivity=ukupniRezultati.ukupniRezultati(stats,summaryRunning,sumBike);


        PosaljiUkupnePodatke posaljiUkupnePodatke = new PosaljiUkupnePodatke();
        posaljiUkupnePodatke.posalji(summaryActivity);
        posaljiUkupnePodatke.posalji(sumBike);
        posaljiUkupnePodatke.posalji(stats);
        posaljiUkupnePodatke.posalji(summaryRunning);

        ukKorakAkt = (TextView) findViewById(R.id.ukKorakAkt);
        ukKorakAkt.setText(String.valueOf(summaryActivity.getUkupanBrojKoraka()));

        ukVrijemeAkt=(TextView) findViewById(R.id.ukVrijemeAkt);
        ukVrijemeAkt.setText(pretvoriUminute(summaryActivity.getUkupnoVrijeme()));

        ukUdaljenostAkt = (TextView) findViewById(R.id.ukUdaljenostAkt);
        ukUdaljenostAkt.setText(String.valueOf(summaryActivity.getPrijedjeniKilometri()));

        ukProsjekBrzinaAkti= (TextView) findViewById(R.id.ukProsjekBrzinaAkti);
        ukProsjekBrzinaAkti.setText(String.valueOf(summaryActivity.getProsjecnaBrzina()));


    }
}
