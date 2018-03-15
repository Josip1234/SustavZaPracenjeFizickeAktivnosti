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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import activity.physical.example.com.josip.physicalactivity.model.SummaryActivity;
import activity.physical.example.com.josip.physicalactivity.model.WalkingStatistika;
import activity.physical.example.com.josip.physicalactivity.pomocneKlase.StatistickiIzracuni;

public class RezultActivity extends AppCompatActivity {

    private TextView mUkupanBrojKoraka;
    private TextView mVus;
    private TextView mkilom;
    private WalkingStatistika stats;
    private TextView mProsjecnaBrzina;

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




    }
}
