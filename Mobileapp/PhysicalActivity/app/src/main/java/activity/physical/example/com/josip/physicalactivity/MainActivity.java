package activity.physical.example.com.josip.physicalactivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import activity.physical.example.com.josip.physicalactivity.SqlLite.RegistrationDataSource;
import activity.physical.example.com.josip.physicalactivity.SqlLite.SqlLiteTablice;
import activity.physical.example.com.josip.physicalactivity.activity.physical.example.com.josip.physicalactivity.interfaces.PhysicalInterface;
import activity.physical.example.com.josip.physicalactivity.model.Registration;
import activity.physical.example.com.josip.physicalactivity.model.WalkingActivity;


public class MainActivity extends AppCompatActivity implements PhysicalInterface {
    private TextView mfail;
    private RegistrationDataSource registrationDataSource;

    public void prijava(View v) throws IOException, JSONException {
        boolean autoriziran = false;
        EditText email;
        EditText sifra;
        TextView tv;
        String em, ps;
        email = (EditText) findViewById(R.id.email);
        sifra = (EditText) findViewById(R.id.sifra);
        tv = (TextView) findViewById(R.id.txt);
        em = email.getText().toString();
        ps = sifra.getText().toString();
        tv.setText(em + ps);
        Registration login = new Registration(em, ps);
        login.setSifra(ps);
        login.setEmail(em);
        autoriziran = procitaj_json(login.getEmail(), login.getSifra());


        if (autoriziran == true) {
            Intent intent = new Intent(MainActivity.this, ListOfActivitiesActivity.class);
            intent.putExtra("ime", em);
            intent.putExtra("šifra", ps);
            startActivity(intent);
        } else {
            mfail = (TextView) findViewById(R.id.fail);
            mfail.setText("Nema korisnika ne možete dalje");
        }
    }

    public void kreiraj_json_polje(Registration[] registrations) throws IOException, JSONException {

        JSONArray array = new JSONArray();
        JSONObject object;
        object = new JSONObject();
        for (Registration reg : registrations
                ) {
            object.put("username", reg.getEmail());
            object.put("pass", reg.getSifra());

        }


        array.put(object);
        String text = array.toString();
        FileOutputStream fos = openFileOutput("prijava.json", MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();

        Log.i("message", "succesfully written to json");


    }

    public boolean procitaj_json(String username, String password) throws IOException, JSONException {
        boolean found = false;
        String userjson = "";
        String passjson = "";
        String naziv = "prijava.json";



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
            String object = data.getJSONObject(i).getString("username");
            String pass = data.getJSONObject(i).getString("pass");
            prijavaBuffer.append(object + " " + pass);
            Registration registration = new Registration(object,pass);
            registrationDataSource.dodajKorisnika(registration);
            //user.add(i,object);
            //user.add(i+1,pass);

            //ovo treba ići poslije
            /*
            if (object.contentEquals(username)) {
                if (pass.contentEquals(password)) {
                    found=true;
                    break;



                }else {

                    continue;


                }
            } else {
                found=false;
                continue;


            }
        }
           */

            Log.i("poruka", "pročitan json");





        }

        found=autoriziraj(username,password);

        return found;
    };
    public boolean autoriziraj(String username,String password){
        boolean found=false;


        found=registrationDataSource.pronadjiKorisnika(username, password);

        return found;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registrationDataSource=new RegistrationDataSource(this);
        registrationDataSource.otvori();
        registrationDataSource.izbrisiSve();
        new HttpReqTask().execute();

    }
    @Override
    protected void onResume(){
        super.onResume();
        registrationDataSource.otvori();

    }
    @Override
    protected void onPause(){
        super.onPause();
        registrationDataSource.zatvori();
    }



    class HttpReqTask extends AsyncTask<Void,Void,Registration[]> {

        private final String uri="10.0.2.2";

        @Override
        protected Registration[] doInBackground(Void... voids) {
            try{
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
        @Override
        protected void onPostExecute(Registration[] registration){
            super.onPostExecute(registration);
            JSONArray array = new JSONArray();
            JSONObject object;


            for (Registration reg:registration
                 ) {
                Log.i("email",String.valueOf(reg.getEmail()));
                Log.i("sifra",String.valueOf(reg.getSifra()));


                try {
                    object = new JSONObject();
                    object.put("username",String.valueOf(reg.getEmail()));
                    object.put("pass",String.valueOf(reg.getSifra()));
                    array.put(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            String text = array.toString();
            FileOutputStream fos = null;
            try {
                fos = openFileOutput("prijava.json", MODE_PRIVATE);
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

            Log.i("poruka", "uspješno zapisano json");


        }
    }
}



