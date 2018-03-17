package activity.physical.example.com.josip.physicalactivity;

import android.content.Intent;
import android.net.Network;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
    }
    public void pokreniAktivnost(View view){

        //idi na prijavu
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();

        try {
            JSONArray polje = new JSONArray();
            JSONObject ob = new JSONObject();

            ob.put("korisnik", "");

            ob.put("ukupnaUdaljenost", 00.00);


            ob.put("ukupnoVrijemeAktivnosti", "");


            ob.put("prosjecnaBrzina", 00.00);


            ob.put("datum", "");


            polje.put(ob);
            String text = polje.toString();
            FileOutputStream fos = openFileOutput("UkupnoBicikliranja.json", MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONArray polje = new JSONArray();
            JSONObject ob = new JSONObject();

            ob.put("korisnik", "");

            ob.put("ukupnaUdaljenost", 00.00);


            ob.put("ukupnoVrijemeAktivnosti", 00.00);


            ob.put("prosjecnaBrzina", 00.00);


            ob.put("datum", "");


            polje.put(ob);
            String text = polje.toString();
            FileOutputStream fos = openFileOutput("UkupnoTrcanja.json", MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            JSONArray polje = new JSONArray();
            JSONObject ob = new JSONObject();

            ob.put("korisnik","");

            ob.put("ukupnaUdaljenost",00.00);


            ob.put("ukupnoVrijemeAktivnosti",00.00);



            ob.put("prosjecnaBrzina",00.00);



            ob.put("datum","");



            ob.put("ukupanBrojKoraka",0);

            polje.put(ob);
            String text = polje.toString();
            FileOutputStream fos = openFileOutput("UkupnoHodanja.json", MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    }




