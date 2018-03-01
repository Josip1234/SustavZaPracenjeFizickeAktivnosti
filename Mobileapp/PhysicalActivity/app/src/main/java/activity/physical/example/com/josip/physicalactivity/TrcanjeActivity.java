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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import activity.physical.example.com.josip.physicalactivity.model.RunningActivity;

public class TrcanjeActivity extends AppCompatActivity {
    private Chronometer cr;

    private TextView mtv1;
    private Button mStart;
    private Button mStop;
    private Button mReset;

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

    public void resetc() {
        cr = (Chronometer) findViewById(R.id.chronometer3);
        cr.setBase(SystemClock.elapsedRealtime());
        cr.stop();
    }

    public void startc() {
        cr = (Chronometer) findViewById(R.id.chronometer3);
        cr.start();
    }

    public void stopc() {

        cr = (Chronometer) findViewById(R.id.chronometer3);
        cr.stop();
        String time = cr.getText().toString();

    }

    public String getTimeAfterStop() {
        String time;
        cr = (Chronometer) findViewById(R.id.chronometer3);
        time = cr.getText().toString();
        return time;
    }

    public String getTimeAfterWishClick() {
        String time;
        cr = (Chronometer) findViewById(R.id.chronometer3);
        time = cr.getText().toString();
        return time;
    }


    public String vrati_korisnika() throws IOException, JSONException {
        String naziv = "prijava.json";
        String korisnik = "";
        FileInputStream fis = openFileInput(naziv);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }
        bis.close();
        fis.close();

        JSONArray data = new JSONArray(b.toString());
        StringBuffer prijavaBuffer = new StringBuffer();
        for (int i = 0; i < data.length(); i++) {
            String object = data.getJSONObject(i).getString("username");
            korisnik = object;
            prijavaBuffer.append(object);
        }

        Log.i("poruka", "pročitan json");
        Log.i("korisnik", korisnik);
        return korisnik;


    }

    public void kreiraj_prethodne_koordinate(double lat, double lon) throws IOException, JSONException {

        JSONArray array = new JSONArray();
        JSONObject object;
        object = new JSONObject();
        object.put("koordinata", lat);
        array.put(object);
        object = new JSONObject();
        object.put("koordinata", lon);
        array.put(object);
        String text = array.toString();
        FileOutputStream fos = openFileOutput("koordinate.json", MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();
        Log.i("message", "succesfully written to json");
    }


    public String vrati_koordinate(int pozicija) throws IOException, JSONException {
        String naziv = "koordinate.json";


        FileInputStream fis = openFileInput(naziv);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }
        bis.close();
        fis.close();

        JSONArray data = new JSONArray(b.toString());
        StringBuffer prijavaBuffer = new StringBuffer();


        String koordinata2 = data.getJSONObject(pozicija).getString("koordinata");
        prijavaBuffer.append(koordinata2);


        Log.i("poruka", "pročitan json");

        return prijavaBuffer.toString();


    }


    public void dohvati_koordinate(double lat, double lon) {


        try {
            kreiraj_prethodne_koordinate(lat, lon);
            Log.i("poruka", "Uspješno spremljene koordinate");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
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
                double loc3 = location.getLatitude();
                double loc4 = location.getLongitude();

                TextView tekst = (TextView) findViewById(R.id.kmh);
                TextView km = (TextView) findViewById(R.id.kilometar);
                System.out.println(loc3);
                System.out.println(loc4);
                float trenutna_brzina = location.getSpeed();
                float brzina = (float) (trenutna_brzina * 3.6);
                tekst.setText(String.valueOf(trenutna_brzina) + " m/s");
                km.setText(String.valueOf(brzina) + " km/h");


                double distance = distance(loc1, loc2, loc3, loc4, "K");

                System.out.println("Udaljenost do druge točke:" + String.valueOf(distance) + " kilometara");
                try {
                    mtv1 = (TextView) findViewById(R.id.dist);
                    mtv1.setText("Udaljenost:" + String.valueOf(distance) + "km");
                } catch (Exception e) {
                    e.printStackTrace();
                }


                // kilometri+=distance;
                // mBrojPretrcanih=(TextView) findViewById(R.id.brkm);
                //mBrojPretrcanih.setText(String.valueOf(kilometri));

                //System.out.println("Koordinata se pojavljuje"+brojPojavljivanjaKoordinata+"put");//
                dohvati_koordinate(loc3, loc4);


                loc1 = 00.00;
                loc2 = 00.00;

                String cityName = null;
                String stateName = null;
                String ad = null;
                String ad2 = null;
                String posta = null;
                Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                List<Address> addresses;


                try {
                    addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
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

                TextView t = (TextView) findViewById(R.id.ulica);
                t.setText(a);
                TextView tv = (TextView) findViewById(R.id.pcity);
                tv.setText(s);
                TextView dr = (TextView) findViewById(R.id.state);
                dr.setText(p);


                location.reset();

            }
        }

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


        mStart = (Button) findViewById(R.id.start);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startc();
            }
        });
        mStop = (Button) findViewById(R.id.stop);
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopc();
            }
        });
        mReset = (Button) findViewById(R.id.reset);
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetc();
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
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 10, locListener);

        TextView kor = (TextView) findViewById(R.id.korisnik);
        try {
            kor.setText(vrati_korisnika());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void onResume() {
        super.onResume();
        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 10, locListener);


    }

    protected void onStart() {
        super.onStart();
        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 10, locListener);
    }

}
