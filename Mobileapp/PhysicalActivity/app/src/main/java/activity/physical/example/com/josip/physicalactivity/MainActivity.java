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
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import activity.physical.example.com.josip.physicalactivity.activity.physical.example.com.josip.physicalactivity.interfaces.PhysicalInterface;
import activity.physical.example.com.josip.physicalactivity.model.Registration;


public class MainActivity extends AppCompatActivity implements PhysicalInterface {
    private TextView mfail;


    public void prijava(View v) {
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
        try {
            kreiraj_json_polje(em, ps);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


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

    public void kreiraj_json_polje(String a, String b) throws IOException, JSONException {

        JSONArray array = new JSONArray();
        JSONObject object;
        object = new JSONObject();
        object.put("username", a);
        object.put("pass", b);

        array.put(object);
        String text = array.toString();
        FileOutputStream fos = openFileOutput("prijava.json", MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();

        Log.i("message", "succesfully written to json");


    }

    public void procitaj_json() throws IOException, JSONException {
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
        }

        Log.i("poruka", "pročitan json");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new HttpReqTask().execute();
    }



    class HttpReqTask extends AsyncTask<Void,Void,Registration> {
        Registration rg = new Registration();
        private final String uri="10.0.2.2";
        @Override
        protected Registration doInBackground(Void... voids) {
            try{
                String url="http://"+uri+":8080/physical/1e2b3tzrUZcvn";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getForObject(url,Registration.class);

                return rg;
            }catch (Exception ex){
                Log.e("poruka",ex.getMessage());
            }
            return null;

        }
        @Override
        protected void onPostExecute(Registration registration){
            super.onPostExecute(registration);
            Log.i("email",String.valueOf(registration.getEmail()));
            Log.i("sifra",String.valueOf(registration.getSifra()));
            try {
                kreiraj_json_polje(String.valueOf(registration.getEmail()),String.valueOf(registration.getSifra()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}



