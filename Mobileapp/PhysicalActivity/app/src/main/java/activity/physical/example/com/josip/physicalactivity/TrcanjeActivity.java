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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.DoubleBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import activity.physical.example.com.josip.physicalactivity.model.BikingActivity;
import activity.physical.example.com.josip.physicalactivity.model.RunningActivity;
import activity.physical.example.com.josip.physicalactivity.pomocneKlase.ChronoHelper;

public class TrcanjeActivity extends AppCompatActivity {
    long timeWhenStopped = 0;
    private Chronometer cr;
    private ChronoHelper chronoHelper;
    private static String time;

    private TextView textViewSteps;

    private TextView adr;
    private TextView tUdaljenost;
    private Button mReset;
    private Button mStart;
    private Button mStop;
    private JSONArray polje;
    private JSONObject run;

    private RunningActivity runningActivity;
    private Date date;
    private SimpleDateFormat sdf;
    private ImageButton image;



    public RunningActivity procitajPodatke() throws IOException, JSONException {


        String naziv = "Trcanje.json";


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
        for (int i = 0; i < data.length() - 1; i++) {
            double udaljenost = data.getJSONObject(i).getDouble("udaljenost");
            String vrijemeAktivnosti = data.getJSONObject(i).getString("vrijemeAktivnosti");
            double brzinaUkm = data.getJSONObject(i).getDouble("brzinaUkm");
            String korisnik = data.getJSONObject(i).getString("korisnik");
            String datum = data.getJSONObject(i).getString("datum");
            prijavaBuffer.append(udaljenost + "" + vrijemeAktivnosti  +  "" + brzinaUkm + "" + korisnik + "" + datum);
            runningActivity = new RunningActivity(vrijemeAktivnosti, brzinaUkm,  korisnik, udaljenost, datum);


            Log.i("poruka", "pročitan json");


        }

        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {


                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(new MediaType("application", "json"));
                    HttpEntity<RunningActivity> request = new HttpEntity<RunningActivity>(runningActivity, headers);
                    RestTemplate restTemplate = new RestTemplate();
                    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                    converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
                    restTemplate.getMessageConverters().add(converter);
                    try {
                        ResponseEntity<RunningActivity> response = restTemplate.exchange("http://10.0.2.2:8080/physical/1e2b3tzrUZcvn", HttpMethod.POST, request, RunningActivity.class);
                        RunningActivity result = response.getBody();
                        System.out.println(result.toString());
                    } catch (HttpClientErrorException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();


        return runningActivity;
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
        FileOutputStream fos = openFileOutput("koordinateRun.json", MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();
        Log.i("message", "succesfully written to json");
    }


    public String vrati_koordinate(int pozicija) throws IOException, JSONException {
        String naziv = "koordinateRun.json";


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










    public String vrati_korisnika() throws IOException, JSONException {
        String naziv = "PodaciKorisnika.json";
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
            String object = data.getJSONObject(i).getString("korisnik");
            korisnik = object;
            prijavaBuffer.append(object);
        }
        Log.i("poruka", "pročitan json");
        Log.i("korisnik", korisnik);

        return korisnik;


    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location loc) {
            double lat = loc.getLatitude();
            double lon = loc.getLongitude();

            if (loc != null) {
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
                double loc3 = lat;
                double loc4 = lon;


                float trenutna_brzina = loc.getSpeed();
                float brzina = (float) (trenutna_brzina * 3.6);


                double distance = distance(loc1, loc2, loc3, loc4, "K");
                tUdaljenost = (TextView) findViewById(R.id.udaljenost);
                tUdaljenost.setText(String.valueOf(distance));
                try {
                    run.put("udaljenost", distance);
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
                    addresses = gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
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
                        if (cityName == null) {
                            cityName = "Nema naziva grada";
                        }
                        if (stateName == null) {
                            stateName = "Nema naziva države";
                        }
                        if (ad == null) {
                            ad = "Nema adrese";
                        }


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String s =
                        cityName;
                String p = stateName;
                String a = ad;


                adr.setText(s + p + a);

                try {
                    run.put("lokacija", s + p + a);
                    run.put("longitude", loc4);
                    run.put("latitude", loc3);
                    run.put("brzinaUkm", brzina);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


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
        cr = (Chronometer) findViewById(R.id.chronometer3);
        chronoHelper = new ChronoHelper(cr);
        image = (ImageButton) findViewById(R.id.posaljiPodatke);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = chronoHelper.getTime();
                try {
                    date = new Date();
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String vrijeme = sdf.format(date);
                    run.put("datum", vrijeme);
                    run.put("vrijemeAktivnosti", time);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                polje.put(run);
                String text = polje.toString();
                try {
                    FileOutputStream os = openFileOutput("Trcanje.json", MODE_PRIVATE);
                    try {
                        os.write(text.getBytes());
                        os.close();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                try {
                    procitajPodatke();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        date = new Date();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String vrijeme= sdf.format(date);


        RunningActivity bikingActivity = new RunningActivity("0:00", 00.00, "korisnik",00.00,vrijeme );





        polje = new JSONArray();
        run = new JSONObject();
        try {
            run.put("udaljenost", bikingActivity.getUdaljenost());
            run.put("vrijemeAktivnosti", bikingActivity.getVrijemeAktivnosti());

            run.put("brzinaUkm", bikingActivity.getBrzinaUkm());
            run.put("korisnik", vrati_korisnika());
            run.put("datum", vrijeme);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        polje.put(run);
        String text = polje.toString();
        try {
            FileOutputStream os = openFileOutput("Trcanje.json", MODE_PRIVATE);
            try {
                os.write(text.getBytes());
                os.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        adr = (TextView) findViewById(R.id.homead);

        tUdaljenost = (TextView) findViewById(R.id.udaljenost);
        tUdaljenost.setText(String.valueOf(bikingActivity.getUdaljenost()));
        mReset = (Button) findViewById(R.id.reset);
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronoHelper.resetc();
            }
        });
        mStart = (Button) findViewById(R.id.start);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronoHelper.startcr();
            }
        });
        mStop = (Button) findViewById(R.id.stop);
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                chronoHelper.stopcr();


            }
        });


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
    @Override
    protected void onPause() {
        super.onPause();


        time = chronoHelper.getTime();
        try {
            run.put("vrijemeAktivnosti", time);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
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

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Dobrodošli na trcanje", Toast.LENGTH_SHORT).show();


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

    }}