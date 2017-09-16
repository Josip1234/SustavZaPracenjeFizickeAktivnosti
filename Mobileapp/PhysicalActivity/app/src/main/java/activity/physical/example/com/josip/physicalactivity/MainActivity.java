package activity.physical.example.com.josip.physicalactivity;

import android.content.Intent;
import android.os.AsyncTask;
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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.channels.SocketChannel;

public class MainActivity extends AppCompatActivity{
    private TextView mfail;
   public void prijava(View v){
       boolean autoriziran=false;
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




       if(autoriziran==true) {
           Intent intent = new Intent(MainActivity.this, ListOfActivitiesActivity.class);
           intent.putExtra("ime", em);
           intent.putExtra("šifra", ps);
           startActivity(intent);
       }else{
           mfail=(TextView) findViewById(R.id.fail);
           mfail.setText("Nema korisnika ne možete dalje");
       }
   }

    public void kreiraj_json_polje(String a,String b) throws IOException,JSONException{

            JSONArray array = new JSONArray();
            JSONObject object;
            object=new JSONObject();
            object.put("username",a);
            object.put("pass",b);

           if (object.length() > 0) {
            new sendDataToServer().execute(String.valueOf(object));

            }
            array.put(object);
            String text = array.toString();
            FileOutputStream fos = openFileOutput("prijava.json",MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        Log.i("message","succesfully written to json");

        }

    public void procitaj_json() throws IOException,JSONException{
        String naziv="prijava.json";
        FileInputStream fis = openFileInput(naziv);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while (bis.available() !=0){
            char c = (char) bis.read();
            b.append(c);
        }
        bis.close();
        fis.close();

        JSONArray data = new JSONArray(b.toString());
        StringBuffer prijavaBuffer= new StringBuffer();
        for(int i=0;i<data.length();i++){
            String object=data.getJSONObject(i).getString("username");
            String pass=data.getJSONObject(i).getString("pass");
            prijavaBuffer.append(object+" "+pass);
        }

        Log.i("poruka","pročitan json");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    };

class sendDataToServer extends AsyncTask<String,String,String>{
    @Override
    protected String doInBackground(String... params) {

        String JsonResponse = null;
        String JsonDATA = params[0];
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://10.0.2.2:8080/physical/mobilnaverifikacijavalidacija");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            // is output buffer writter
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
//set headers and method
            Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
            writer.write(JsonDATA);
// json data
            writer.close();
            InputStream inputStream = urlConnection.getInputStream();
//input stream
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = reader.readLine()) != null)
                buffer.append(inputLine + "\n");
            if (buffer.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }
            JsonResponse = buffer.toString();
//response data

            try {
//send to post execute
                return JsonResponse;
            }catch (Exception e) {
                return null;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {

                }
            }
        }
        return null;




    }


    @Override
    protected void onPostExecute(String s) {
    }

}
}
