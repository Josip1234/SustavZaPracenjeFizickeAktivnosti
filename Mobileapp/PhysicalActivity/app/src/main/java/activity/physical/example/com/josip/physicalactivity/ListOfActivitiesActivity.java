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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Intent.getIntent;

public class ListOfActivitiesActivity extends AppCompatActivity {
    private boolean unesen;
    private int brojKoristenjaAplikacije=0;
    private String naziv="brojKoristenja.json";

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
        try {
            brojKoristenjaAplikacije+=dohvatiBrojKoristenja();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(brojKoristenjaAplikacije==0){
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
