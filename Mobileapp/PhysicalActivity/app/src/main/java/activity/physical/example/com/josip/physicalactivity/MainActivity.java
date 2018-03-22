package activity.physical.example.com.josip.physicalactivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import activity.physical.example.com.josip.physicalactivity.SqlLite.RegistrationDataSource;
import activity.physical.example.com.josip.physicalactivity.model.Registration;


public class MainActivity extends AppCompatActivity {

    private RegistrationDataSource registrationDataSource;
    private int brojKoristenja=0;
    private Button mButton;
    private final String uri="10.0.2.2";
    private String url="http://"+uri+":8080/physical/1e2b3tzrUZcvn";
    private String naziv="brojKoristenja.json";

   //funkcija za provjeru internet veze
    private boolean vezaPremaMrezi() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable &&
                cm.getActiveNetworkInfo().isConnected();
        return isNetworkConnected;
    }



    //funkcija za prijavu u sustav
    public void prijava(View v) throws IOException, JSONException {
        //nije autoriziran
        boolean autoriziran = false;
        EditText email;
        EditText sifra;

        String em, ps;
        email = (EditText) findViewById(R.id.email);
        sifra = (EditText) findViewById(R.id.sifra);

        //dohvati uneseno korisnicko ime  i lozinku
        em = email.getText().toString();
        ps = sifra.getText().toString();

        //krairaj instancu login a
        Registration login = new Registration(em, ps);
        login.setSifra(ps);
        login.setEmail(em);
        //pozovi funkciju za prijavu
        autoriziran = prijavi(login.getEmail(), login.getSifra());
        //autoriziran=true;
         //ako je korisnika autetificiran autoriziraj mu korištenja aplikacije i pošalji ime i šifru prema listi
        if (autoriziran == true) {
            Intent intent = new Intent(MainActivity.this, ListOfActivitiesActivity.class);
            intent.putExtra("ime", em);
            intent.putExtra("šifra", ps);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.nijeAutoriziran, Toast.LENGTH_LONG).show();
        }
        //ako nije autoriziran odgovori da nema korisnika i da ne može dalje korisnik ići bez registracije nja bazu
    }


    //funkcija koja vraća true ako je korisnik prijavljen ako nije vraća false
    public boolean prijavi(String username, String password) throws IOException, JSONException {
        boolean found = false;


        found=autoriziraj(username,password);

        return found;
    };
    //funkcija za autentifikaciju
    public boolean autoriziraj(String username,String password){
        boolean found=false;
        //otvori sql lite baze
        registrationDataSource.citaj();
        //napravi upit na bazu sa korisnickim imenom i lozinkom
        found=registrationDataSource.pronadjiKorisnika(username, password);
        //zatvori bazu
        registrationDataSource.zatvori();
        //vrati rezultat
        return found;
    };
    //dohvati broj korištenja aplikacije
    public int dohvatiBrojKoristenja() throws IOException,JSONException{
        FileInputStream fis = openFileInput(naziv);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean provjeriMrezu=false;
        mButton=(Button) findViewById(R.id.Loginbutton);
        mButton.setVisibility(View.INVISIBLE);
        //broji broj korištenja aplikacije
        try {
            brojKoristenja+=dohvatiBrojKoristenja();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //ako se aplikacija koristi prvi put
        if(brojKoristenja==0){
            Toast.makeText(this, R.string.prijava, Toast.LENGTH_LONG).show();
        }
        //stvori registracijski izbor podataka
        registrationDataSource=new RegistrationDataSource(this);
        //otvori bazu
        registrationDataSource.otvori();
        //izbrisi sve iz baze
        registrationDataSource.izbrisiSve();
        //zatvori bazu
        registrationDataSource.zatvori();
        //provjera dali postoji internet veza




        //ako ima interneta izvrši asinkroni task ako ne vrati se u ptehodnu aktivnost a prije toga pokaži obavijset
        provjeriMrezu=vezaPremaMrezi();
        if(provjeriMrezu==true){
            new HttpReqTask().execute();
        }else{
            Intent intent = new Intent(this,StartingActivity.class);
            startActivity(intent);
            Toast.makeText(this, R.string.internetVeza, Toast.LENGTH_LONG).show();
        }



    }



//asinkroni task koji prima podatke sa web poslužitelja
    class HttpReqTask extends AsyncTask<Void,Void,Registration[]>  {
        //lokacija za lokalni pristup na web preko mobilne aplikacije
        private final String uri="10.0.2.2";

        @Override
        protected Registration[] doInBackground(Void... voids) {
            try{
                //registracija convertera za pretvaranje jsona u polje te prihvat podataka sa servera
                String url="http://"+uri+":8080/physical/1e2b3tzrUZcvn";
                RestTemplate restTemplate = new RestTemplate();
                MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
                mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
                restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
               Registration[] rg= restTemplate.getForObject(url,Registration[].class);

                return rg;
            }catch (Exception ex){
                Log.e("poruka",ex.getMessage());
            }
            return null;

        }
        //unos primljenih podataka u sql lite bazu
        @Override
        protected void onPostExecute(Registration[] registration) {
            super.onPostExecute(registration);

            //otvori bazu
            registrationDataSource.otvori();
            try {
                for (Registration reg:registration
                     ) {
                    //ako je null objekt izađi iz petlje

                        Log.i("email", String.valueOf(reg.getEmail()));
                        Log.i("sifra", String.valueOf(reg.getSifra()));
                        //dodaj korisnicko ime i lozinku dohvaćenih sa weba
                        registrationDataSource.dodajKorisnika(reg);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }


            //zatvori bazu
            registrationDataSource.zatvori();



            //pokazi button za  prijavu na vidljivoj poziciji nakon što završiš radnje
            mButton.setVisibility(View.VISIBLE);

        }
    }
}



