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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TrcanjeActivity extends AppCompatActivity  {
private Chronometer cr;


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
        object.put("latitude",lat);
        object.put("longitude",lon);
        array.put(object);
        String text = array.toString();
        FileOutputStream fos = openFileOutput("koordinate.json",MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();
        Log.i("message","succesfully written to json");
    }


    public double vrati_koordinate(String naziv_koordinate) throws IOException,JSONException {
        String naziv="koordinate.json";
        double lat=00.00;

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
        if(naziv=="latitude") {

                double object = data.getJSONObject(0).getDouble(naziv_koordinate);

                lat = object;

                prijavaBuffer.append(object);

        }else if(naziv=="longitude"){
            double object = data.getJSONObject(1).getDouble(naziv_koordinate);

            lat = object;

            prijavaBuffer.append(object);
        }

        Log.i("poruka","pročitan json");
        return lat;


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
            double loc1=location.getLatitude();
            double loc2=location.getLongitude();
            System.out.println(loc1);
            System.out.println(loc2);
            brojPojavljivanjaKoordinata+=1;
            System.out.println("Koordinata se pojavljuje"+brojPojavljivanjaKoordinata+"put");
            dohvati_koordinate(loc1,loc2);
            TextView tekst=(TextView) findViewById(R.id.kmh);
            TextView km=(TextView) findViewById(R.id.kilometar);
            if(location==null){
                tekst.setText("nema brzine");
            }
            else{
                float trenutna_brzina=location.getSpeed();
                float brzina= (float) (trenutna_brzina*3.6);
                tekst.setText(String.valueOf(trenutna_brzina)+" m/s");
                km.setText(String.valueOf(brzina)+" km/h");
            };

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



