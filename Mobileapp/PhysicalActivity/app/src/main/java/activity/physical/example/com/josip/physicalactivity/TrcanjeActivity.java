package activity.physical.example.com.josip.physicalactivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.DoubleBuffer;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TrcanjeActivity extends AppCompatActivity  {
private Chronometer cr;

private TextView mtv1;
public int indeks=0;
    private Button map;
private double[] kilometri;
private float suma=0;
private int brojPojavljivanjaKoordinata=0;
public void mapiraj(View v){

}



    public void stop(View v){
        cr=(Chronometer) findViewById(R.id.chronometer2);
        cr.stop();
    }
    public String vrati_korisnika() throws IOException,JSONException {
        String naziv="prijava.json";
        String korisnik="";
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
            korisnik=object;
            prijavaBuffer.append(object);
        }
        Log.i("poruka","pročitan json");
        Log.i("korisnik",korisnik);
        return korisnik;


    }

    public void kreiraj_prethodne_koordinate(double lat ,double lon) throws IOException,JSONException{

        JSONArray array = new JSONArray();
        JSONObject object;
        object=new JSONObject();
        object.put("koordinata",lat);
        array.put(object);
        object=new JSONObject();
        object.put("koordinata",lon);
        array.put(object);
        String text = array.toString();
        FileOutputStream fos = openFileOutput("koordinate.json",MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();
        Log.i("message","succesfully written to json");
    }


    public String vrati_koordinate(int pozicija) throws IOException,JSONException {
        String naziv="koordinate.json";


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
        StringBuffer prijavaBuffer = new StringBuffer();


                String koordinata2 = data.getJSONObject(pozicija).getString("koordinata");
                prijavaBuffer.append(koordinata2);








        Log.i("poruka","pročitan json");

        return prijavaBuffer.toString();


    }


    public void dohvati_koordinate(double lat,double lon){


        try {
            kreiraj_prethodne_koordinate(lat,lon);
            Log.i("poruka","Uspješno spremljene koordinate");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            String loc3="";
            String loc4="";
            try {
                loc3=vrati_koordinate(0);
                loc4=vrati_koordinate(1);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            double loc1=location.getLatitude();
            double loc2=location.getLongitude();
            System.out.println(loc1);
            System.out.println(loc2);
            Location l1=new Location("Lokacija 1");

            try {
                l1.setLatitude(Double.parseDouble(loc3));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            try {
                l1.setLongitude(Double.parseDouble(loc4));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Location l2=new Location("Lokacija 2");
            l2.getLatitude();
            l2.getLongitude();
            float distance=l2.distanceTo(l1)/1000;
            System.out.println("Udaljenost do druge točke:"+String.valueOf(distance)+" kilometara");
            try {
                mtv1=(TextView) findViewById(R.id.dist);
                mtv1.setText("Udaljenost:"+String.valueOf(distance)+"km");
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*
            brojPojavljivanjaKoordinata+=1;
            System.out.println("Koordinata se pojavljuje"+brojPojavljivanjaKoordinata+"put");*/
            dohvati_koordinate(loc1,loc2);
            TextView tekst=(TextView) findViewById(R.id.kmh);
            TextView km=(TextView) findViewById(R.id.kilometar);

            loc3="";
            loc4="";

            String cityName=null;
            String stateName=null;
            String ad=null;
            String ad2=null;
            String posta=null;
            Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;


            //double temp=suma;


            // kilometri[0]=location.distanceTo(loc2)/1000;


            //suma+=kilometri[0];

/*
            TextView k=(TextView) findViewById(R.id.brkm);
            k.setText(suma+"km/h");*/


            try {
                addresses=gcd.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                if(addresses.size()>0){
                    try {
                        Log.i("poruka",addresses.get(0).getLocality());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.i("poruka",addresses.get(0).getCountryName());
                    Log.i("poruka",addresses.get(0).getAddressLine(0));
                    try {
                        Log.i("poruka", addresses.get(0).getAddressLine(1));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    try {
                        Log.i("poruka", addresses.get(0).getAddressLine(2));
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    cityName=addresses.get(0).getLocality();
                    stateName=addresses.get(0).getCountryName();
                    ad=addresses.get(0).getAddressLine(0);
                    float trenutna_brzina=location.getSpeed();
                    float brzina= (float) (trenutna_brzina*3.6);
                    tekst.setText(String.valueOf(trenutna_brzina)+" m/s");
                    km.setText(String.valueOf(brzina)+" km/h");


                }
            } catch (IOException e) {
                e.printStackTrace();
            }




            String s =
                    cityName;
            String p=stateName;
            String a=ad;

            TextView t=(TextView) findViewById(R.id.ulica);
            t.setText(a);
            TextView tv=(TextView) findViewById(R.id.pcity);
            tv.setText(s);
            TextView dr=(TextView) findViewById(R.id.state);
            dr.setText(p);

        };

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trcanje);
        cr=(Chronometer) findViewById(R.id.chronometer2);
        cr.start();
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);

        TextView kor=(TextView) findViewById(R.id.korisnik);
        try {
            kor.setText(vrati_korisnika());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }};



