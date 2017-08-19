package activity.physical.example.com.josip.physicalactivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.SystemClock;
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


public class WalkingActivity extends AppCompatActivity implements SensorEventListener {
    private Chronometer cr;
    private TextView atv;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate=0;
    private float last_x,last_y,last_z;
    private TextView tv1,tv2,tv3;
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
    private SeekBar seekBar;
    private int threshold;




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
        textViewX=(TextView) findViewById(R.id.textViewX);
        textViewY=(TextView) findViewById(R.id.textViewY);
        textViewZ=(TextView) findViewById(R.id.textViewZ);
        textViewSteps=(TextView) findViewById(R.id.textSteps);
        textSensitive=(TextView) findViewById(R.id.textSensitive);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(seekBarListener);
        threshold=10;
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
            if ((curTime - lastUpdate) > 20) {
                long difftime = (curTime - lastUpdate);
                lastUpdate = curTime;


                last_x = x;
                last_y = y;
                last_z = z;


                if(x>0.0){
                    start();

                }else if(x<=0.0){
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
