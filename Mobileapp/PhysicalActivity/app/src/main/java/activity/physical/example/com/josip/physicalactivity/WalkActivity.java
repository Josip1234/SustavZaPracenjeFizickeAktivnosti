package activity.physical.example.com.josip.physicalactivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import activity.physical.example.com.josip.physicalactivity.model.WalkingActivity;
import activity.physical.example.com.josip.physicalactivity.model.WalkingStatistika;
import activity.physical.example.com.josip.physicalactivity.pomocneKlase.ChronoHelper;
import activity.physical.example.com.josip.physicalactivity.pomocneKlase.IzracunUdaljenostiiBrzine;
import activity.physical.example.com.josip.physicalactivity.pomocneKlase.StatistickiIzracuni;


public class WalkActivity extends AppCompatActivity implements SensorEventListener {
    long timeWhenStopped = 0;
    private Chronometer cr;
    private TextView atv;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private TextView tv1, tv2, tv3;
    private static String time;
    private TextView textViewX;
    private TextView textViewY;
    private TextView textViewZ;
    private TextView textSensitive;
    private TextView textViewSteps;
    private TextView mKorisnik;
    private Button buttonReset;
    private float acceleration;
    private float previousY;
    private float currentY;
    private int numSteps;
    private SeekBar seekBar;
    private int threshold;
    private ChronoHelper chronoHelper;
    private TextView mBrzina;
    private TextView adr;
    private TextView tUdaljenost;
    private Button mReset;
    private Button mStart;
    private Button mStop;
    private JSONArray polje;
    private JSONObject walk;
    private ImageButton mPosaljiPodatke;
    private List<WalkingActivity> lista;
    private WalkingActivity walkingActivity;
    private Date date;
    private SimpleDateFormat sdf;
    private int brojKoristenjaAplikacije=0;
    private Map<String,String> autorizacija;
    private List<Integer> brojKoraka;
    private List<Double> kilometri;
    private List<Double> prosjecnaBrzina;
    private long vrijemeAktivnosti;

    private double vrijemeTocaka;
    private int count=0;
    private int brojMjerenja=0;
    private int brojBrzine=0;
    //čitaj podatke iz jsona
    public WalkingActivity procitajPodatke() throws IOException, JSONException {

        String userjson = "";
        String passjson = "";
        String naziv = "Walking.json";


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
            int koraci = data.getJSONObject(i).getInt("koraci");
            double brzinaUkm = data.getJSONObject(i).getDouble("brzinaUkm");
            String korisnik = data.getJSONObject(i).getString("korisnik");
            String vrijeme=data.getJSONObject(i).getString("datumIvrijeme");
            prijavaBuffer.append(udaljenost + "" + vrijemeAktivnosti + "" + koraci + "" + brzinaUkm + "" + korisnik+""+vrijeme);
            //kreiraj instancu i postavi podatke na te vrijednosti
            walkingActivity = new WalkingActivity(udaljenost, vrijemeAktivnosti, koraci, brzinaUkm, korisnik,vrijeme);
            lista.add(i,walkingActivity);

            Log.i("poruka", "pročitan json");


        }




        return walkingActivity;
    }

    //dohvati  koordinate
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

    //prethodne koordinate spremi u json
    public void kreiraj_prethodne_koordinate(double lat, double lon) throws IOException, JSONException {
//pogrešan json format popraviti
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

    //s obzirom na različit format jsona, na trenutnoj poziciji, dohvati koordinatu.
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





    //pročitaj korisnika spremljenog u json
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
    //implementiraj slušača lokacije
    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location loc) {
            //broj mjerenja
            count+=1;
            //dohvati koordinate
            double lat = loc.getLatitude();
            double lon = loc.getLongitude();

            //broj mjerenja brzine
            brojBrzine+=1;

            if (loc != null) {
                //dohvati brzinu
                double trenutna_brzina = loc.getSpeed();

                double loc1 = 00.00;
                double loc2 = 00.00;
                try {
                    //vrati prethodne koordinate
                    loc1 = Double.parseDouble(vrati_koordinate(0));
                    loc2 = Double.parseDouble(vrati_koordinate(1));
                    System.out.println(loc1);
                    System.out.println(loc2);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //trenutne koordinate postavi na vrijednosti
                double loc3 = lat;
                double loc4 = lon;


                //brzinu pretvoti u kilometre
                double brzina =  trenutna_brzina * 3.6;

                //izračunaj udaljenosti
                IzracunUdaljenostiiBrzine izracunUdaljenosti = new IzracunUdaljenostiiBrzine();
                izracunUdaljenosti.setLat1(loc1);
                izracunUdaljenosti.setLon1(loc2);
                izracunUdaljenosti.setLat2(loc3);
                izracunUdaljenosti.setLon2(loc4);
                izracunUdaljenosti.setUnit("K");



                //u bazu je potrebno spremiti koordinate i njihovo trenutno vrijeme kad su kreirani a i za potrebe optimizacije koda.
                //tako bi se iz baze izvlačile koordinate prema promijeni i mjerila bi se brzina na temelju točaka, kao i udaljenost
                //između njih



                //spremi trenutne koordinate
                dohvati_koordinate(loc3, loc4);

                //varijable za dohvat adrese
                String cityName = null;
                String stateName = null;
                String ad = null;
                String ad2 = null;
                String posta = null;
                Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                List<Address> addresses;
                //izračunaj udaljenost
                double distance = izracunUdaljenosti.distance(izracunUdaljenosti.getLat1(),izracunUdaljenosti.getLon1(),izracunUdaljenosti.getLat2(),izracunUdaljenosti.getLon2());

                tUdaljenost = (TextView) findViewById(R.id.udaljenost);
                tUdaljenost.setText(String.valueOf(distance));
                 //ako nema brzine
                if((brzina==0.00 || brzina==00.00) && count>1){
                    //za potrebe simulacije, brzina je postavljena kao randomn broj između 0 i 10
                    Random random = new Random();
                    brzina=random.nextInt(10);
                    mBrzina.setText("Brzina u km/h:" + String.valueOf(brzina));
                    prosjecnaBrzina.add(brzina);

                }else {
                    //ako je brzina dostupna, uzmi iz gotove funkcije
                    mBrzina.setText("Brzina u km/h " + String.valueOf(brzina));
                    prosjecnaBrzina.add(brzina);
                }
                kilometri.add(distance);
                //spremi udaljenost u json
                try {
                    walk.put("udaljenost", distance);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //dohvati adrese
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
                //spremi u json
                try {
                    walk.put("adresa", s + p + a);
                    walk.put("longitude", loc4);
                    walk.put("latitude", loc3);
                    walk.put("brzinaUkm", brzina);
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
        setContentView(R.layout.activity_walking);
        vrijemeAktivnosti=0;
        //lista za izračun korakka
        brojKoraka=new ArrayList<Integer>();
          int vrijednost=0;
         //po kreiranju aplikacije otvori i uzmi trenutni broj koraka kako
        //bi se zbrajali sveukupni rezultati
        //iz jsona izvadi broj koraka
        String naziv = "UkupnoHodanja.json";


        try {
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
                vrijednost += data.getJSONObject(i).getInt("ukupanBrojKoraka");
                //dodaj u listu koja izračunava ukupan broj koraka
                brojKoraka.add(vrijednost);

                Log.i("poruka", "pročitan json");


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }








//ovdje citati json i dodati u listu prethodne korake
       brojKoristenjaAplikacije+=1;
       if(brojKoristenjaAplikacije==1){
           Toast.makeText(this, R.string.walkingTutor, Toast.LENGTH_LONG).show();
       }


       //lista za izračun brzine
        prosjecnaBrzina=new ArrayList<Double>();
        //lista za izračun udaljenosti
        kilometri=new ArrayList<Double>();
        cr = (Chronometer) findViewById(R.id.chronometer4);
        chronoHelper = new ChronoHelper(cr);
        //odmah na početku startaj kronometar radi mjerenja ukupnog vremena
        chronoHelper.startcr();
        mBrzina=(TextView) findViewById(R.id.brzinaukm);
        //kreiranje trenutnog datuma
        date=new Date();
        sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String vrijeme=sdf.format(date);
        //defaultne vrijednosti za unos u json prvotna funkcija je bila slanje podataka, no zbog toga što je sve zapetljano, ovo ostavljamo, uklonjeno je samo slanje podataka
        lista = new ArrayList<WalkingActivity>();

        WalkingActivity walkingActivity = new WalkingActivity(00.00,"0:00",0,00.00,"",vrijeme);






        textViewSteps = (TextView) findViewById(R.id.textSteps);
        textViewSteps.setText(String.valueOf(walkingActivity.getKoraci()));
       //spremanje podataka u json
        polje = new JSONArray();
        walk = new JSONObject();
        try {
            walk.put("udaljenost",walkingActivity.getUdaljenost());
            walk.put("vrijemeAktivnosti",walkingActivity.getVrijemeAktivnosti());
            walk.put("koraci",walkingActivity.getKoraci());

            walk.put("brzinaUkm",walkingActivity.getBrzinaUkm());
            walk.put("korisnik",vrati_korisnika());
            walk.put("datumIvrijeme",vrijeme);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        polje.put(walk);
        String text = polje.toString();
        try {
            FileOutputStream os = openFileOutput("Walking.json", MODE_PRIVATE);
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
        tUdaljenost.setText(String.valueOf(walkingActivity.getUdaljenost()));
        mReset = (Button) findViewById(R.id.reset);
        //resetiran kronometar
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronoHelper.resetc();
                vrijemeAktivnosti=0;
            }
        });
        //pokreni kronometar
        mStart = (Button) findViewById(R.id.start);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronoHelper.startcr();
                vrijemeAktivnosti=SystemClock.elapsedRealtime();
            }
        });
        //stopiraj kronometar
        mStop = (Button) findViewById(R.id.stop);
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                chronoHelper.stopcr();
                vrijemeAktivnosti=chronoHelper.getVrijemeZaustavljanja();


            }
        });

       //uključivanje gps-a
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
        //zahtjevaj ažuriranje lokacije
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 10, locListener);
        //koordinate za accelerometar
        textViewX = (TextView) findViewById(R.id.textViewX);
        textViewY = (TextView) findViewById(R.id.textViewY);
        textViewZ = (TextView) findViewById(R.id.textViewZ);
        textViewSteps = (TextView) findViewById(R.id.textSteps);
        textSensitive = (TextView) findViewById(R.id.textSensitive);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        //mijenjanje osjetljivosti za izračun koraka
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(seekBarListener);


        previousY = 0;
        currentY = 0;
        numSteps = 0;
        acceleration = 0.00f;

        //dohvati accelerometar
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mKorisnik = (TextView) findViewById(R.id.korisnik);
        //dohvati korisnika iz jsona
        try {
            mKorisnik.setText(vrati_korisnika());
            walk.put("korisnik", vrati_korisnika());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void resetSteps(View v) {
        numSteps = 0;
        textViewSteps.setText(String.valueOf(numSteps));
    }
    //kada se promijeni vrijednost na seekbaru ažuriraj osjetljivost
    private OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            threshold = seekBar.getProgress();
            textSensitive.setText(String.valueOf(threshold));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            currentY = y;
            //računaj vrijeme promjene kada se promijeni vrijednost povećavaj korake
            long curTime = System.currentTimeMillis();
            if (Math.abs(currentY - previousY) > threshold) {
                numSteps++;

                textViewSteps.setText(String.valueOf(numSteps));

            }
            //stavi korake u json
            try {
                walk.put("koraci", numSteps);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            textViewX.setText(String.valueOf(x));
            textViewY.setText(String.valueOf(y));
            textViewZ.setText(String.valueOf(z));

            previousY = y;
            if ((curTime - lastUpdate) > 10) {
                long difftime = (curTime - lastUpdate);
                lastUpdate = curTime;


                last_x = x;
                last_y = y;
                last_z = z;



            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }



    @Override
    protected void onPause() {
        super.onPause();
        //nemoj ažurirati lokaciju kada se aplikacija pauzira
        senSensorManager.unregisterListener(this);

        time = chronoHelper.dohvatiRealnoVrijeme();
        try {
            walk.put("vrijemeAktivnosti", time);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        //u nastavku aplikacije nakon pauziranje ažuriraj lokaciju

        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
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

         //nakon što se pokrene aktivnost, ažuriraj aplikaciju
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
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
        public void onDestroy(){
            super.onDestroy();
            //nakon što se aplikacija tj aktivnost uništi
             String korisnik=mKorisnik.getText().toString();
             brojMjerenja+=1;





                int vrijednost=0;
                String userjson = "";
                String passjson = "";
                //iz jsona izvadi broj koraka
                String naziv = "sumaKoraka.json";


            try {
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
                    vrijednost += data.getJSONObject(i).getInt("izracun");
                    //dodaj u listu koja izračunava ukupan broj koraka
                    brojKoraka.add(vrijednost);

                    Log.i("poruka", "pročitan json");


                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            textViewSteps=(TextView) findViewById(R.id.textSteps);
            brojKoraka.add(Integer.parseInt(String.valueOf(textViewSteps.getText())));
            //pozovi klasu tj kreiraj instancu za izračun sume i prosjeka
            StatistickiIzracuni statistickiIzracuni = new StatistickiIzracuni();
            statistickiIzracuni.izracunajUkupanBrojKoraka(brojKoraka);
            int suma=statistickiIzracuni.getUkupanBrojKoraka();
            //zaustavljamo kronometar kako bi se dobilo vrijeme vrijeme se dobiva u milisekundama

            chronoHelper.stopcr();
            String vrijeme=chronoHelper.dohvatiRealnoVrijeme();
            //dohvati vrijeme kronometra
            double vrij=Math.abs(chronoHelper.getVrijemeZaustavljanja());
            //izračunaj prosječnu brzinu na temelju sume brzine i broja mjerenja brzine
            statistickiIzracuni.izracunajprosjecnuBrzinu(prosjecnaBrzina,brojBrzine);
            //izračunaj ukupnu udaljenost
            statistickiIzracuni.izracunajUkupnoPrijedjenjeKilometre(kilometri);
            System.out.println(statistickiIzracuni.getKilometri()+" "+statistickiIzracuni.getProsjecnaBrzina()+" Vrijeme:"+vrijeme);
            //stvori datum
            date=new Date();
            sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time="";
            time=sdf.format(date);
              //stavi u klasu walking statistika izračunate podatke
             WalkingStatistika statistika = new WalkingStatistika(korisnik, statistickiIzracuni.getKilometri(), vrij, statistickiIzracuni.getProsjecnaBrzina(), time, statistickiIzracuni.getUkupanBrojKoraka());
            //spremi ih u jason za potrbe čitanja i izračuna sveukupupnih podataka
            try {
                JSONArray polje = new JSONArray();
                JSONObject ob = new JSONObject();

                ob.put("korisnik",statistika.getEmail());

                ob.put("ukupnaUdaljenost",statistika.getUkupnaUdaljenost());


                ob.put("ukupnoVrijemeAktivnosti",statistika.getUkupnoVrijemeAktivnosti());



                ob.put("prosjecnaBrzina",statistika.getProsjecnaBrzinaUkm());



                ob.put("datum",statistika.getPeriod());



                ob.put("ukupanBrojKoraka",statistika.getUkupanBrojKoraka());

                polje.put(ob);
                String text = polje.toString();
                FileOutputStream fos = openFileOutput("UkupnoHodanja.json", MODE_PRIVATE);
                fos.write(text.getBytes());
                fos.close();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }






        }

















