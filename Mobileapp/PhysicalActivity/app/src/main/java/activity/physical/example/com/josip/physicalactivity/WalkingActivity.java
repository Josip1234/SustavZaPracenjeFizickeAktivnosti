package activity.physical.example.com.josip.physicalactivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class WalkingActivity extends AppCompatActivity implements SensorEventListener {
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
    //private SeekBar seekBar;
    private int threshold;
    private TextView tLattitude;
    private TextView tLongittude;
    private TextView tLocDesc;
    private TextView state;
    private TextView adr;
    private TextView tUdaljenost;
    private Button mReset;

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


    public void resetc(){
        cr=(Chronometer) findViewById(R.id.chronometer2);
        cr.setBase(SystemClock.elapsedRealtime());
        cr.stop();
    }


    public void start() {
        cr = (Chronometer) findViewById(R.id.chronometer2);
        cr.start();
    }

    public void onclickedstopchronomethar() {
        cr = (Chronometer) findViewById(R.id.chronometer2);
        cr.stop();
    }

    public String getTimeAfterStop() {
        String time;
        cr = (Chronometer) findViewById(R.id.chronometer2);
        time = cr.getText().toString();
        return time;
    }
     public String getTime(){
        cr=(Chronometer) findViewById(R.id.chronometer2);
        String time=cr.getText().toString();
        return time;
     }
    public String getTimeAfterWishClick() {
        String time;
        cr = (Chronometer) findViewById(R.id.chronometer2);
        time = cr.getText().toString();
        return time;
    }
    public String vrati_korisnika() throws IOException,JSONException {
        String naziv="prijava";
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
    public void mapi(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking);
        tLattitude = (TextView) findViewById(R.id.outputLat);
        tLongittude = (TextView) findViewById(R.id.outputLong);
        tLocDesc = (TextView) findViewById(R.id.location_dsc);
        state=(TextView) findViewById(R.id.state_dsc);
        adr=(TextView) findViewById(R.id.homead);
        mKorisnik=(TextView) findViewById(R.id.korisnik);
        mReset=(Button) findViewById(R.id.reset);
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetc();
            }
        });
        try {
            mKorisnik.setText(vrati_korisnika());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener locListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location loc) {
                double lat = loc.getLatitude();
                double lon = loc.getLongitude();

                if(loc!=null) {
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
                    double loc3 =  lat;
                    double loc4 =  lon;


                float trenutna_brzina = loc.getSpeed();
                float brzina = (float) (trenutna_brzina * 3.6);

                tLattitude.setText(String.valueOf(trenutna_brzina));
                tLongittude.setText(String.valueOf(brzina));


                double distance = distance(loc1,loc2,loc3,loc4,"K");
                    tUdaljenost=(TextView) findViewById(R.id.udaljenost);
                    tUdaljenost.setText(String.valueOf(distance));
                    dohvati_koordinate(loc3, loc4);
                String cityName=null;
                String stateName=null;
                String ad=null;
                String ad2=null;
                String posta=null;
                Geocoder gcd = new Geocoder(getBaseContext(),Locale.getDefault());
                List<Address> addresses;


                try {
                     addresses=gcd.getFromLocation(loc.getLatitude(),loc.getLongitude(),1);
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


                tLocDesc.setText(s);
                state.setText(p);
                adr.setText(a);
                loc.reset();
            }}

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
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locListener);
        textViewX=(TextView) findViewById(R.id.textViewX);
        textViewY=(TextView) findViewById(R.id.textViewY);
        textViewZ=(TextView) findViewById(R.id.textViewZ);
        textViewSteps=(TextView) findViewById(R.id.textSteps);
        textSensitive=(TextView) findViewById(R.id.textSensitive);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        /*seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(seekBarListener);*/
        threshold=15;
        textSensitive.setText(String.valueOf(threshold));
        previousY=0;
        currentY=0;
        numSteps=0;
        acceleration=0.00f;


        senSensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer=senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this,senAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);




    }


   public void resetSteps(View v){
       numSteps=0;
       textViewSteps.setText(String.valueOf(numSteps));
   }
   private OnSeekBarChangeListener seekBarListener= new OnSeekBarChangeListener() {
       @Override
       public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
           threshold=seekBar.getProgress();
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

            currentY=y;
            long curTime = System.currentTimeMillis();
            if(Math.abs(currentY-previousY)>threshold){
                numSteps++;
                textViewSteps.setText(String.valueOf(numSteps));

            }

            textViewX.setText(String.valueOf(x));
            textViewY.setText(String.valueOf(y));
            textViewZ.setText(String.valueOf(z));

            previousY=y;
            if ((curTime - lastUpdate) > 10) {
                long difftime = (curTime - lastUpdate);
                lastUpdate = curTime;


                last_x = x;
                last_y = y;
                last_z = z;


                if(x>5){
                    start();

                }else if(x<-5){
                    onclickedstopchronomethar();
                    time=getTimeAfterStop();

                }



            }
        }
    }

            @Override
            public void onAccuracyChanged (Sensor sensor,int i){

            }


        protected void onPause(){
            super.onPause();
            senSensorManager.unregisterListener(this);

            time=getTimeAfterStop();
        };

        protected void onResume() {
            super.onResume();
            senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);


        };





        /*
        public void changeText() {
            tv1 = (TextView) findViewById(R.id.x);
            tv2 = (TextView) findViewById(R.id.y);
            tv3 = (TextView) findViewById(R.id.z);

            tv1.setText(Float.toString(last_x));
            tv2.setText(Float.toString(last_y));
            tv3.setText(Float.toString(last_z));

        };*/
    };
