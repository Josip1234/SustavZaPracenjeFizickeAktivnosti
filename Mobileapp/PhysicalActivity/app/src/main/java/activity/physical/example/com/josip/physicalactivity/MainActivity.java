package activity.physical.example.com.josip.physicalactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
   public void prijava(View v){
       EditText email;
       EditText sifra;
       TextView tv;
       String em,ps;
       email=(EditText) findViewById(R.id.email);
       sifra=(EditText) findViewById(R.id.sifra);
      tv = (TextView) findViewById(R.id.txt);
       em=email.getText().toString();
       ps=sifra.getText().toString();
       tv.setText(em+ps);
       Login login = new Login(em,ps);
       login.setPassword(ps);
       login.setUsername(em);
       try {
           kreiraj_json_polje(em,ps);
       } catch (IOException e) {
           e.printStackTrace();
       } catch (JSONException e) {
           e.printStackTrace();
       }
       Intent intent = new Intent(MainActivity.this,ListOfActivitiesActivity.class);
       startActivity(intent);
   }

    public void kreiraj_json_polje(String a,String b) throws IOException,JSONException{

            JSONArray array = new JSONArray();
            JSONObject object;
            object=new JSONObject();
            object.put(a,b);
            array.put(object);
            String text = array.toString();
            FileOutputStream fos = openFileOutput("prijava",MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        Log.i("message","succesfully written to json");
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    };


}