package activity.physical.example.com.josip.physicalactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
    public boolean kreirajJsonDatoteku(String user) throws JSONException, IOException {
       JSONArray jsonArray = new JSONArray();
       JSONObject jsonObject;
       jsonObject=new JSONObject();
       jsonObject.put("korisnik",user);
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
        unesen=false;
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            String ime=extras.getString("ime");
            String sifra=extras.getString("šifra");
            try {
                unesen=kreirajJsonDatoteku(ime);
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
