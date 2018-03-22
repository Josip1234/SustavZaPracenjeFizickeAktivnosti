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
import java.util.Random;

import activity.physical.example.com.josip.physicalactivity.model.BikingActivity;
import activity.physical.example.com.josip.physicalactivity.model.RunningActivity;
import activity.physical.example.com.josip.physicalactivity.model.SummaryBiking;
import activity.physical.example.com.josip.physicalactivity.model.SummaryRunning;
import activity.physical.example.com.josip.physicalactivity.pomocneKlase.ChronoHelper;
import activity.physical.example.com.josip.physicalactivity.pomocneKlase.IzracunUdaljenostiiBrzine;
import activity.physical.example.com.josip.physicalactivity.pomocneKlase.StatistickiIzracuni;

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

    private double distance=0;
    private int broj = 0;
    private int brojBrzine = 0;
    private int brojMjerenja = 0;
    private List<Double> kilometri;
    private List<Double> prosjecnaBrzina;
    private TextView mKorisnik;
    private TextView brzinaUkm;

    private String naziv="brojKoristenja.json";
    private int brojKoristenja=0;

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
        for (int i = 0; i < data.length(); i++) {
            double udaljenost = data.getJSONObject(i).getDouble("udaljenost");
            String vrijemeAktivnosti = data.getJSONObject(i).getString("vrijemeAktivnosti");
            double brzinaUkm = data.getJSONObject(i).getDouble("brzinaUkm");
            String korisnik = data.getJSONObject(i).getString("korisnik");
            String datum = data.getJSONObject(i).getString("datum");
            prijavaBuffer.append(udaljenost + "" + vrijemeAktivnosti + "" + brzinaUkm + "" + korisnik + "" + datum);
            runningActivity = new RunningActivity(vrijemeAktivnosti, brzinaUkm, korisnik, udaljenost, datum);


            Log.i("poruka", "pročitan json");


        }


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
            broj += 1;
            brojBrzine += 1;
            double lat = loc.getLatitude();
            double lon = loc.getLongitude();


            if (loc != null) {
                double trenutna_brzina = loc.getSpeed();

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


                double brzina = trenutna_brzina * 3.6;


                IzracunUdaljenostiiBrzine izracunUdaljenosti = new IzracunUdaljenostiiBrzine();
                izracunUdaljenosti.setLat1(loc1);
                izracunUdaljenosti.setLon1(loc2);
                izracunUdaljenosti.setLat2(loc3);
                izracunUdaljenosti.setLon2(loc4);
                izracunUdaljenosti.setUnit("K");
                //za potrebe simulacije zaustavljamo kronometar


                //u bazu je potrebno spremiti koordinate i njihovo trenutno vrijeme kad su kreirani a i za potrebe optimizacije koda.
                //tako bi se iz baze izvlačile koordinate prema promijeni i mjerila bi se brzina na temelju točaka, kao i udaljenost
                //između njih


                dohvati_koordinate(loc3, loc4);


                String cityName = null;
                String stateName = null;
                String ad = null;
                String ad2 = null;
                String posta = null;
                Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                List<Address> addresses;
                //ako se aplikacija ne koristi prvi put
                if(brojKoristenja>0) {
                    distance = izracunUdaljenosti.distance(izracunUdaljenosti.getLat1(), izracunUdaljenosti.getLon1(), izracunUdaljenosti.getLat2(), izracunUdaljenosti.getLon2());
                }
                tUdaljenost = (TextView) findViewById(R.id.udaljenost);
                tUdaljenost.setText(String.valueOf(distance));

                if ((brzina == 0.00 || brzina == 00.00) && broj > 1) {

                    Random random = new Random();
                    brzina = random.nextInt(30);
                    brzinaUkm.setText("Brzina u km/h:" + String.valueOf(brzina));
                    prosjecnaBrzina.add(brzina);

                } else {

                    brzinaUkm.setText("Brzina u km/h " + String.valueOf(brzina));
                    prosjecnaBrzina.add(brzina);
                }
                kilometri.add(distance);

                try {
                    run.put("udaljenost", distance);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


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
                    run.put("adresa", s + p + a);

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
        prosjecnaBrzina = new ArrayList<Double>();
        kilometri = new ArrayList<Double>();

        try {
            brojKoristenja+=dohvatiBrojKoristenja();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mKorisnik = (TextView) findViewById(R.id.korisnik);
        brzinaUkm = (TextView) findViewById(R.id.brzinaukm);
        String kor = "";
        try {
            kor = vrati_korisnika();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mKorisnik.setText(kor);

        cr = (Chronometer) findViewById(R.id.chronometer3);
        chronoHelper = new ChronoHelper(cr);
        chronoHelper.startcr();

        date = new Date();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String vrijeme = sdf.format(date);


        RunningActivity runningActivity = new RunningActivity("0:00", 00.00, "korisnik", 00.00, vrijeme);


        polje = new JSONArray();
        run = new JSONObject();
        try {
            run.put("udaljenost", runningActivity.getUdaljenost());
            run.put("vrijemeAktivnosti", runningActivity.getVrijemeAktivnosti());

            run.put("brzinaUkm", runningActivity.getBrzinaUkm());
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
        tUdaljenost.setText(String.valueOf(runningActivity.getUdaljenost()));
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
    public void onDestroy() {
        super.onDestroy();
        String korisnik = mKorisnik.getText().toString();


        int vrijednost = 0;
        String userjson = "";
        String passjson = "";


        StatistickiIzracuni statistickiIzracuni = new StatistickiIzracuni();


        chronoHelper.stopcr();
        String vrijeme = chronoHelper.dohvatiRealnoVrijeme();
        double vrij = Math.abs(chronoHelper.getVrijemeZaustavljanja());
        statistickiIzracuni.izracunajprosjecnuBrzinu(prosjecnaBrzina, brojBrzine);
        statistickiIzracuni.izracunajUkupnoPrijedjenjeKilometre(kilometri);
        System.out.println(statistickiIzracuni.getKilometri() + " " + statistickiIzracuni.getProsjecnaBrzina() + " Vrijeme:" + vrijeme);
        date = new Date();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = "";
        time = sdf.format(date);

        SummaryRunning statistika = new SummaryRunning(korisnik, statistickiIzracuni.getKilometri(), vrij, statistickiIzracuni.getProsjecnaBrzina(), time);

        try {
            JSONArray polje = new JSONArray();
            JSONObject ob = new JSONObject();

            ob.put("korisnik", statistika.getKorisnik());

            ob.put("ukupnaUdaljenost", statistika.getUkupnaUdaljenost());


            ob.put("ukupnoVrijemeAktivnosti", statistika.getUkupnoVrijemeAktivnosti());


            ob.put("prosjecnaBrzina", statistika.getProsjecnaBrzinaUkm());


            ob.put("datum", statistika.getPeriod());


            polje.put(ob);
            String text = polje.toString();
            FileOutputStream fos = openFileOutput("UkupnoTrcanja.json", MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}