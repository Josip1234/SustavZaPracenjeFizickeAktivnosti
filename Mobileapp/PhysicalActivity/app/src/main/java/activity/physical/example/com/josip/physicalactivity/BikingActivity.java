package activity.physical.example.com.josip.physicalactivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.SystemClock;
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
import java.util.ArrayList;
import activity.physical.example.com.josip.physicalactivity.model.BikeActivity;

public class BikingActivity extends AppCompatActivity  {
private Chronometer cron;
private Button mStart;
private Button mStop;
private Button mReset;
    private TextView mSpeed;
    private TextView mSpeedkmH;
    private TextView mUlica;
    private TextView mPostaNaziv;
    private TextView mDrzava;
    private TextView mKorisnik;
    private TextView mUdaljenost;
    private List<BikeActivity> listaVrijednosti;
    private BikeActivity bike;
    public void startcron(){
        cron=(Chronometer) findViewById(R.id.chronometer4);
        cron.start();
    }
    public String stopcron(){
        cron=(Chronometer) findViewById(R.id.chronometer4);
        cron.stop();
        String time=cron.getText().toString();

        System.out.println(time);
        bike.setVrijemeAktivnosti(time);
        return time;
    }
    public void resetcron(){
        cron=(Chronometer) findViewById(R.id.chronometer4);
        cron.setBase(SystemClock.elapsedRealtime());
        cron.stop();
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
        bike.setKorisnik(korisnik);
        return korisnik;


    }
    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;

        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
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
        public void onLocationChanged(Location loca) {
            if(loca!=null) {
                mSpeed=(TextView) findViewById(R.id.brzina);
                mSpeedkmH=(TextView) findViewById(R.id.brzinakm);
                mUlica=(TextView) findViewById(R.id.ulica);
                mPostaNaziv=(TextView) findViewById(R.id.postbrngr);
                mDrzava=(TextView) findViewById(R.id.drzava);
                mKorisnik=(TextView) findViewById(R.id.korisnik);
                mUdaljenost=(TextView) findViewById(R.id.udaljenost);

                double loc1 = 00.00;
                double loc2 = 00.00;
                try {
                    loc1 = Double.parseDouble(vrati_koordinate(0));
                    loc2 = Double.parseDouble(vrati_koordinate(1));
                    System.out.println(loc1);
                    System.out.println(loc2);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                double loc3 =  loca.getLatitude();
                double loc4 =  loca.getLongitude();
                float trenutna_brzina = loca.getSpeed();
                float brzina = (float) (trenutna_brzina * 3.6);
                bike.setBrzinaUkm(brzina);
                mSpeed.setText(String.valueOf(trenutna_brzina) + " m/s");
                mSpeedkmH.setText(String.valueOf(brzina) + " km/h");
                System.out.println(loc3);
                System.out.println(loc4);
                double distance = distance(loc1,loc2,loc3,loc4,"K");
                bike.setUdaljenost(distance);


                System.out.println("Udaljenost do druge točke:" + String.valueOf(distance) + " kilometara");
                try {

                    mUdaljenost.setText("Udaljenost:" + String.valueOf(distance) + "km");
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    mKorisnik.setText(vrati_korisnika());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dohvati_koordinate(loc3, loc4);
                String cityName = null;
                String stateName = null;
                String ad = null;
                Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                List<Address> addresses;




                try {
                    addresses = gcd.getFromLocation(loca.getLatitude(), loca.getLongitude(), 1);
                    if (addresses.size() > 0) {
                        try {
                            Log.i("poruka", addresses.get(0).getLocality());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.i("poruka", addresses.get(0).getCountryName());
                        Log.i("poruka", addresses.get(0).getAddressLine(0));
                        try {
                            Log.i("poruka", addresses.get(0).getAddressLine(1));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            Log.i("poruka", addresses.get(0).getAddressLine(2));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        cityName = addresses.get(0).getLocality();
                        stateName = addresses.get(0).getCountryName();
                        ad = addresses.get(0).getAddressLine(0);



                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String s =
                        cityName;
                String p = stateName;
                String a = ad;


                mUlica.setText(a);

                mPostaNaziv.setText(s);

                mDrzava.setText(p);
                bike.setLokacija(a+s+p);
                bike.setLongitude(loc4);
                bike.setLatitude(loc3);

                listaVrijednosti.add(new BikeActivity(bike.getVrijemeAktivnosti(), bike.getBrzinaUkm(), bike.getLokacija(), bike.getLongitude(), bike.getLatitude(), bike.getKorisnik(), bike.getUdaljenost()));
                for (BikeActivity bk:listaVrijednosti
                        ) {
                    System.out.println(bk.getKorisnik());
                    System.out.println(bk.getBrzinaUkm());
                    System.out.println(bk.getLatitude());
                    System.out.println(bk.getLongitude());
                    System.out.println(bk.getLokacija());
                    System.out.println(bk.getVrijemeAktivnosti());
                    System.out.println(bk.getUdaljenost());

                }



                loca.reset();

            }
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
        setContentView(R.layout.activity_biking);
        listaVrijednosti=new ArrayList<BikeActivity>();
        bike=new BikeActivity();
        mStart=(Button) findViewById(R.id.startch);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startcron();
            }
        });
        mStop=(Button) findViewById(R.id.stopch);
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopcron();
            }
        });
        mReset=(Button) findViewById(R.id.resetch);
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetcron();
            }
        });

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


    }

    public void posaljiPodatke(double lat ,double lon) throws IOException,JSONException{

        JSONArray array = new JSONArray();
        JSONObject object;
        object=new JSONObject();
        object.put("koordinata",lat);
        array.put(object);
        object=new JSONObject();
        object.put("koordinata",lon);
        array.put(object);
        String text = array.toString();
        FileOutputStream fos = openFileOutput("bicikliranje.json",MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();
        Log.i("message","succesfully written to json");
    }




}
