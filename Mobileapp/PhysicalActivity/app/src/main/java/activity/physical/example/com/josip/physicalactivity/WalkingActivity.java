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
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private Context mCon;
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

    public String getTimeAfterWishClick() {
        String time;
        cr = (Chronometer) findViewById(R.id.chronometer2);
        time = cr.getText().toString();
        return time;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking);
        tLattitude = (TextView) findViewById(R.id.outputLat);
        tLongittude = (TextView) findViewById(R.id.outputLong);
        tLocDesc = (TextView) findViewById(R.id.location_dsc);
        final LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location loc) {
                double lat = loc.getLatitude();
                double lon = loc.getLongitude();
                String provider = locManager.getBestProvider(new Criteria(), true);

                if (ActivityCompat.checkSelfPermission(mCon, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mCon, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Location locations = locManager.getLastKnownLocation(provider);
                List<String> providerList = locManager.getAllProviders();
                if(null!=locations && null!=providerList && providerList.size()>0){
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        List<Address> listAddresses = geocoder.getFromLocation(lat, lon, 1);
                        if(null!=listAddresses&&listAddresses.size()>0){
                            String _Location = listAddresses.get(0).getAddressLine(0);
                            tLocDesc.setText(_Location);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }








                tLattitude.setText(String.valueOf(lat));
                tLongittude.setText(String.valueOf(lon));
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
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
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
