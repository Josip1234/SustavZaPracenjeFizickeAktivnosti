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

import activity.physical.example.com.josip.physicalactivity.model.SummaryActivity;
import activity.physical.example.com.josip.physicalactivity.pomocneKlase.StatistickiIzracuni;

public class RezultActivity extends AppCompatActivity {

    private TextView mUkupanBrojKoraka;
    private TextView mVus;

    public int procitajSumu() throws IOException, JSONException {
        int vrijednost=0;
        String vrijeme="";

        String naziv = "sumaKoraka.json";


        FileInputStream fis = openFileInput(naziv);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }
        bis.close();
        fis.close();

        JSONArray data = new JSONArray(b.toString());
        StringBuffer prijavaBuffer = new StringBuffer();
        for (int i = 0; i < data.length(); i++) {
            vrijednost = data.getJSONObject(i).getInt("izracun");
            vrijeme=data.getJSONObject(i).getString("vrijeme");

            Log.i("poruka", "pročitan json");


        }
        return vrijednost;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezult);
        int rezultat=0;

        try {
            rezultat=procitajSumu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mUkupanBrojKoraka=(TextView) findViewById(R.id.ukbk);
        mUkupanBrojKoraka.setText(String.valueOf(rezultat));

        mVus=(TextView) findViewById(R.id.vus);




    }
}
