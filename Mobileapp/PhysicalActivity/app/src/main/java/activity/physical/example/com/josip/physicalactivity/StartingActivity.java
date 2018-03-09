package activity.physical.example.com.josip.physicalactivity;

import android.content.Intent;
import android.net.Network;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            JSONArray array = new JSONArray();
            JSONObject object;
            object = new JSONObject();
            object.put("izracun",0);
            array.put(object);
            String text = array.toString();
            FileOutputStream fos = openFileOutput("sumaKoraka.json", MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
