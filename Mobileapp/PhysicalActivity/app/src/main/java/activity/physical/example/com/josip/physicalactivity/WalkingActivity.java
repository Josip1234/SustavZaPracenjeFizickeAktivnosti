package activity.physical.example.com.josip.physicalactivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;





public class WalkingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,SensorEventListener {
    private Chronometer cr;
    private Switch sv;
    private TextView atv;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate=0;
    private float last_x,last_y,last_z;
    private static final int SHAKE_THRESHOLD=600;
    private TextView tv1,tv2,tv3;

    public void start(){
        cr=(Chronometer) findViewById(R.id.chronometer2);
        cr.start();
    }
    public void onclickedstopchronomethar(){
        cr=(Chronometer) findViewById(R.id.chronometer2);
        cr.stop();
    }
    public String getTimeAfterStop(){
        String time;
        cr=(Chronometer) findViewById(R.id.chronometer2);
        time=cr.getText().toString();
        return time;
    }
    public String getTimeAfterWishClick(){
        String time;
        cr=(Chronometer) findViewById(R.id.chronometer2);
        time=cr.getText().toString();
        return time;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking);
        senSensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer=senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this,senAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);



        sv=(Switch) findViewById(R.id.switch2);
        sv.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
         if(isChecked){
             start();
         }else{
             onclickedstopchronomethar();

             getTimeAfterStop();
         }
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();
            if ((curTime - lastUpdate) > 10) {
                long difftime = (curTime - lastUpdate);
                lastUpdate = curTime;


                last_x = x;
                last_y = y;
                last_z = z;


                if(x<0.0){
                    start();
                }else if(x>0.0){
                    onclickedstopchronomethar();
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
