package activity.physical.example.com.josip.physicalactivity;

import android.content.Intent;
import android.net.Network;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StartingActivity extends AppCompatActivity {
    //broji broj puta koliko se aplikacija koristi
    private int brojKoristenja=0;
    private String naziv="brojKoristenja.json";

    public int brojKoristenja() throws IOException,JSONException {
        FileInputStream fis = openFileInput(naziv);
        if(fis==null){
            try {
                JSONArray polje = new JSONArray();
                JSONObject ob = new JSONObject();

                ob.put("brojKoristenja", 0);



                polje.put(ob);
                String text = polje.toString();
                FileOutputStream fos = openFileOutput(naziv, MODE_PRIVATE);
                fos.write(text.getBytes());
                fos.close();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }else {
            BufferedInputStream bis = new BufferedInputStream(fis);
            StringBuffer b = new StringBuffer();
            while (bis.available() != 0) {
                char c = (char) bis.read();
                b.append(c);
            }
            bis.close();
            fis.close();
            int brojKoristenja = 0;
            JSONArray data = new JSONArray(b.toString());
            StringBuffer prijavaBuffer = new StringBuffer();
            for (int i = 0; i < data.length(); i++) {
                brojKoristenja += data.getJSONObject(i).getInt("brojKoristenja");

                Log.i("poruka", "pročitan json");


            }


            return brojKoristenja;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        //uvecavaj broj koristenja
        try {
            brojKoristenja+=brojKoristenja();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //ako je broj korištenja jednak nuli aplikacija se koristi prvi put
        if(brojKoristenja==0){
            Toast.makeText(this, R.string.Starting, Toast.LENGTH_LONG).show();
            brojKoristenja+=1;
            JSONArray polje = new JSONArray();
            JSONObject ob = new JSONObject();
            //uvećaj i zapamti rezultat
            try {
                ob.put("brojKoristenja", brojKoristenja);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            polje.put(ob);
            String text = polje.toString();
            FileOutputStream fos = null;
            try {
                fos = openFileOutput(naziv, MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fos.write(text.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    public void pokreniAktivnost(View view){
         //funkcija za pokretanje aktivnosti
        //idi na prijavu
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //isprazni json datoteke nakon uništenja aplikacije

        //iz jsona izvadi broj koraka
        String naziv = "sumaKoraka.json";

         int vrijednost=0;
         //postavi vrijednost izracuna na nulu
        try {
            JSONArray polje = new JSONArray();
            JSONObject ob = new JSONObject();

            ob.put("izracun", vrijednost);



            polje.put(ob);
            String text = polje.toString();
            FileOutputStream fos = openFileOutput(naziv, MODE_PRIVATE);
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




