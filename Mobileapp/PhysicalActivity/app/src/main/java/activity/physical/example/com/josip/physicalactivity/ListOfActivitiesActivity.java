package activity.physical.example.com.josip.physicalactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Intent.getIntent;

public class ListOfActivitiesActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_activities);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            String ime=extras.getString("ime");
            String sifra=extras.getString("šifra");
            Log.i("poruka",ime);
            Log.i("poruka",sifra);
        }

    }
}
