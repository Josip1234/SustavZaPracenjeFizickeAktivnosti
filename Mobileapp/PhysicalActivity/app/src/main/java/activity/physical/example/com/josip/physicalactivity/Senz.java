package activity.physical.example.com.josip.physicalactivity;

/**
 * Created by Josip on 12.8.2017..
 */

import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Senz extends AppCompatActivity {

    private SensorManagerSimulator mSensorManager;
    private TextView mTextViewAccelerometer;
    private SensorEventListener mEventListenerAccelerometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_main);

        mTextViewAccelerometer = (TextView) findViewById(R.id.text_accelerometer);




        mSensorManager = SensorManagerSimulator.getSystemService(this,
                SENSOR_SERVICE);

        mSensorManager.connectSimulator();


        initListeners();

    }

    private void initListeners() {
        mEventListenerAccelerometer = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                mTextViewAccelerometer.setText("Accelerometer: " + values[0]
                        + ", " + values[1] + ", " + values[2]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };





        };

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mEventListenerAccelerometer,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);
    };


    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(mEventListenerAccelerometer);

        super.onStop();
    };
};

