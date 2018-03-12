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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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

import activity.physical.example.com.josip.physicalactivity.model.WalkingActivity;
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
    private ImageButton image;
    private Map<String,String> autorizacija;
    private List<Integer> brojKoraka;
    private List<Double> kilometri;
    private List<Double> prosjecnaBrzina;
    private long vrijeme1;
    private long vrijeme2;
    private double vrijemeTocaka;
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
            walkingActivity = new WalkingActivity(udaljenost, vrijemeAktivnosti, koraci, brzinaUkm, korisnik,vrijeme);
            lista.add(i,walkingActivity);

            Log.i("poruka", "pročitan json");


        }

        Thread thread = new Thread(new Runnable(){
            public void run() {
                try {




                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(new MediaType("application","json"));
                    HttpEntity<WalkingActivity> request= new HttpEntity<WalkingActivity>(walkingActivity,headers);
                    RestTemplate restTemplate = new RestTemplate();
                    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                    converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
                    restTemplate.getMessageConverters().add(converter);
                    try {
                        ResponseEntity<WalkingActivity> response= restTemplate.exchange("http://10.0.2.2:8080/physical/1e2b3tzrUZcvn", HttpMethod.POST,request,WalkingActivity.class);
                        WalkingActivity result=response.getBody();
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


        return walkingActivity;
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
            vrijeme1= SystemClock.elapsedRealtime();


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



                double brzina =  trenutna_brzina * 3.6;


                IzracunUdaljenostiiBrzine izracunUdaljenosti = new IzracunUdaljenostiiBrzine();
                izracunUdaljenosti.setLat1(loc1);
                izracunUdaljenosti.setLon1(loc2);
                izracunUdaljenosti.setLat2(loc3);
                izracunUdaljenosti.setLon2(loc4);
                izracunUdaljenosti.setUnit("K");
                izracunUdaljenosti.setVrijeme1(vrijeme1);
                double distance = izracunUdaljenosti.distance(izracunUdaljenosti.getLat1(),izracunUdaljenosti.getLon1(),izracunUdaljenosti.getLat2(),izracunUdaljenosti.getLon2(),izracunUdaljenosti.getUnit());

                if(brzina==0.00 || brzina==00.00){

                    brzina=izracunUdaljenosti.izracunajBrzinuUkm(distance);
                    mBrzina.setText("Brzina u km/h:" + String.valueOf(brzina));
                    prosjecnaBrzina.add(brzina);

                }else {

                    mBrzina.setText("Brzina u km/h " + String.valueOf(brzina));
                    prosjecnaBrzina.add(brzina);
                }
                kilometri.add(distance);

                tUdaljenost = (TextView) findViewById(R.id.udaljenost);
                tUdaljenost.setText(String.valueOf(distance));
                try {
                    walk.put("udaljenost", distance);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                vrijeme2=SystemClock.elapsedRealtime();
                izracunUdaljenosti.setVrijeme2(vrijeme2);
                dohvati_koordinate(loc3, loc4);
                String cityName = null;
                String stateName = null;
                String ad = null;
                String ad2 = null;
                String posta = null;
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
        brojKoraka=new ArrayList<Integer>();
        prosjecnaBrzina=new ArrayList<Double>();
        kilometri=new ArrayList<Double>();
        cr = (Chronometer) findViewById(R.id.chronometer4);
        chronoHelper = new ChronoHelper(cr);
        mBrzina=(TextView) findViewById(R.id.brzinaukm);
        image=(ImageButton) findViewById(R.id.posaljiPodatke);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = chronoHelper.getTime();
                try {
                    date=new Date();
                    sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String vrijeme=sdf.format(date);
                    walk.put("datumIvrijeme",vrijeme);
                    walk.put("vrijemeAktivnosti", time);
                } catch (JSONException e) {
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



                try {
                    procitajPodatke();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        date=new Date();
        sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String vrijeme=sdf.format(date);
        lista = new ArrayList<WalkingActivity>();

        WalkingActivity walkingActivity = new WalkingActivity(00.00,"0:00",0,00.00,"",vrijeme);






        textViewSteps = (TextView) findViewById(R.id.textSteps);
        textViewSteps.setText(String.valueOf(walkingActivity.getKoraci()));

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
        textViewX = (TextView) findViewById(R.id.textViewX);
        textViewY = (TextView) findViewById(R.id.textViewY);
        textViewZ = (TextView) findViewById(R.id.textViewZ);
        textViewSteps = (TextView) findViewById(R.id.textSteps);
        textSensitive = (TextView) findViewById(R.id.textSensitive);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(seekBarListener);


        previousY = 0;
        currentY = 0;
        numSteps = 0;
        acceleration = 0.00f;


        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mKorisnik = (TextView) findViewById(R.id.korisnik);
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
            long curTime = System.currentTimeMillis();
            if (Math.abs(currentY - previousY) > threshold) {
                numSteps++;

                textViewSteps.setText(String.valueOf(numSteps));

            }
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

/*
                if(x>5){
                    start();

                }else if(x<-5){
                    onclickedstopchronomethar();
                    time=getTimeAfterStop();

                }*/


            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }



    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);

        time = chronoHelper.dohvatiRealnoVrijeme();
        try {
            walk.put("vrijemeAktivnosti", time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*
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
*/

        //RestTemplate rest = new RestTemplate(true);

        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        /*

        try {
            procitajPodatke();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
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
        }*/

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
        Toast.makeText(this, "Dobrodošli u aplikaciju", Toast.LENGTH_SHORT).show();

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





        /*
        public void changeText() {
            tv1 = (TextView) findViewById(R.id.x);
            tv2 = (TextView) findViewById(R.id.y);
            tv3 = (TextView) findViewById(R.id.z);

            tv1.setText(Float.toString(last_x));
            tv2.setText(Float.toString(last_y));
            tv3.setText(Float.toString(last_z));

        };*/
        @Override
        public void onDestroy(){
            super.onDestroy();



                int vrijednost=0;
                String userjson = "";
                String passjson = "";
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
            StatistickiIzracuni statistickiIzracuni = new StatistickiIzracuni();
            statistickiIzracuni.izracunajUkupanBrojKoraka(brojKoraka);
            int suma=statistickiIzracuni.getUkupanBrojKoraka();
            //zaustavljamo kronometar kako bi se dobilo vrijeme vrijeme se dobiva u milisekundama

            chronoHelper.stopcr();
            String vrijeme=chronoHelper.dohvatiRealnoVrijeme();
            statistickiIzracuni.izracunajprosjecnuBrzinu(prosjecnaBrzina);
            statistickiIzracuni.izracunajUkupnoPrijedjenjeKilometre(kilometri);
            System.out.println(statistickiIzracuni.getKilometri()+" "+statistickiIzracuni.getProsjecnaBrzina()+" Vrijeme:"+vrijeme);

            try {
                JSONArray array = new JSONArray();
                JSONObject object;
                object = new JSONObject();
                object.put("izracun",suma);
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
/*
private class SlanjePodatakaNaServer extends AsyncTask<Void, Void, WalkingActivity> {
    private WalkingActivity walkingActivity;
    private List<WalkingActivity> walkingActivities;

    @Override
    protected void onPreExecute() {
        walkingActivity = new WalkingActivity();
        walkingActivities = new ArrayList<WalkingActivity>();
        try {
            FileInputStream fileInputStream = openFileInput("Walking.json");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            StringBuffer stringBuffer = new StringBuffer();
            while (bufferedInputStream.available() != 0) {
                char znakovi = (char) bufferedInputStream.read();
                stringBuffer.append(znakovi);
            }
            bufferedInputStream.close();
            fileInputStream.close();

            try {
                JSONArray hodanje = new JSONArray(stringBuffer.toString());
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < hodanje.length(); i++) {
                    double udaljenost = hodanje.getJSONObject(i).getDouble("udaljenost");
                    String vrijemeAktivnosti = hodanje.getJSONObject(i).getString("vrijemeAktivnosti");
                    int koraci = hodanje.getJSONObject(i).getInt("koraci");
                    String adresa = hodanje.getJSONObject(i).getString("adresa");
                    double longitude = hodanje.getJSONObject(i).getDouble("longitude");
                    double latitude = hodanje.getJSONObject(i).getDouble("latitude");
                    double brzinaUkm = hodanje.getJSONObject(i).getDouble("brzinaUkm");
                    String korisnik = hodanje.getJSONObject(i).getString("korisnik");
                    String vrijeme = hodanje.getJSONObject(i).getString("datumIvrijeme");
                    buffer.append(udaljenost + "" + vrijemeAktivnosti + "" + koraci + "" + adresa + "" + longitude + "" + latitude + "" + brzinaUkm + "" + korisnik + " " + vrijeme);
                    walkingActivities.add(new WalkingActivity(udaljenost, vrijemeAktivnosti, koraci, adresa, longitude, latitude, brzinaUkm, korisnik, vrijeme));
                    walkingActivity.setUdaljenost(udaljenost);
                    walkingActivity.setVrijemeAktivnosti(vrijemeAktivnosti);
                    walkingActivity.setKoraci(koraci);
                    walkingActivity.setAdresa(adresa);
                    walkingActivity.setLongitude(longitude);
                    walkingActivity.setLatitude(latitude);
                    walkingActivity.setBrzinaUkm(brzinaUkm);
                    walkingActivity.setKorisnik(korisnik);
                    walkingActivity.setDatumIvrijeme(vrijeme);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected WalkingActivity doInBackground(Void... params) {
        //Looper.prepare();


        //MultiValueMap<String, WalkingActivity> mapa = new LinkedMultiValueMap<String,WalkingActivity>();
        //mapa.add("kljuc",new WalkingActivity(walk.getUdaljenost(),walk.getVrijemeAktivnosti(),walk.getKoraci(),walk.getAdresa(),walk.getLongitude(),walk.getLatitude(),walk.getBrzinaUkm(),walk.getKorisnik()));
        // WalkingActivity mapa = walk;

        //RestTemplate rest = new RestTemplate(true);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        for (WalkingActivity walk : walkingActivities
                ) {
            System.out.println(walk.getKorisnik());
            walkingActivity = new WalkingActivity(walk.getUdaljenost(), walk.getVrijemeAktivnosti(), walk.getKoraci(), walk.getAdresa(), walk.getLongitude(), walk.getLatitude(), walk.getBrzinaUkm(), walk.getKorisnik(), walk.getDatumIvrijeme());
        }
        HttpHeaders requestHeaders = new HttpHeaders();
        //rješavanje eof-a i korištenje starije verzije http veze
        //requestHeaders.set("Connection","Close");
        requestHeaders.setContentType(new MediaType("application", "json"));
        HttpEntity<WalkingActivity> requestEntity = new HttpEntity<WalkingActivity>(walkingActivity, requestHeaders);

        RestTemplate restTemplate = new RestTemplate(true);
        //za rješavanje eof filea za starije android uređaje
        // restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        //MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter1 = new MappingJackson2HttpMessageConverter();
        //mappingJackson2HttpMessageConverter1.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        walkingActivity = restTemplate.postForObject("http://10.0.2.2:8080/physical/walking", walkingActivity, WalkingActivity.class);


        //restTemplate.postForLocation("http://10.0.2.2:8080/physical/walking",walkingActivity);


        //HttpEntity<MultiValueMap<String,WalkingActivity>> request = new HttpEntity<MultiValueMap<String, WalkingActivity>>(mapa,headers);
        //HttpEntity<WalkingActivity> request = new HttpEntity<WalkingActivity>(mapa,headers);

        //rest.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        // rest.postForEntity("http://10.0.2.2:8080/physical/walking",walk,WalkingActivity.class);
        //ResponseEntity<WalkingActivity> responseEntity = rest.postForEntity("http://10.0.2.2:8080/physical/walking",walk,WalkingActivity.class);
        //rest.postForLocation("http://10.0.2.2:8080/physical/walking",walk);
        //System.out.println("response header:"+responseEntity.getHeaders().toString());
        //System.out.println("response body:"+responseEntity.getBody().toString());


        return walkingActivity;
    }

    @Override
    protected void onPostExecute(WalkingActivity res) {
        System.out.println(res.toString());


    }

}

*/
















