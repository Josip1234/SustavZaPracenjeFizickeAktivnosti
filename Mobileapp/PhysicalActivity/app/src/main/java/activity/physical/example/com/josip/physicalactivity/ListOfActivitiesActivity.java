package activity.physical.example.com.josip.physicalactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Intent.getIntent;

public class ListOfActivitiesActivity extends AppCompatActivity {
    private boolean unesen;
    private int brojKoristenjaAplikacije=0;
    public boolean kreirajJsonDatoteku(String user,String pass) throws JSONException, IOException {
       JSONArray jsonArray = new JSONArray();
       JSONObject jsonObject;
       jsonObject=new JSONObject();
       jsonObject.put("korisnik",user);
       jsonObject.put("sifra",pass);
       jsonArray.put(jsonObject);
       String tekst=jsonArray.toString();
       FileOutputStream stream = openFileOutput("PodaciKorisnika.json",MODE_PRIVATE);
       stream.write(tekst.getBytes());
       stream.close();
       return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_activities);
        brojKoristenjaAplikacije+=1;
        if(brojKoristenjaAplikacije==1){
            Toast.makeText(this, R.string.lista, Toast.LENGTH_LONG).show();
        }
        unesen=false;
        //dohvati podatke poslane sa prethodne aktivnosti
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            String ime=extras.getString("ime");
            String sifra=extras.getString("šifra");
            try {
                //stavi ih u json
                unesen=kreirajJsonDatoteku(ime,sifra);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i("poruka","Json je:"+String.valueOf(unesen));
            Log.i("poruka",ime);
            Log.i("poruka",sifra);


        }

    }
}
