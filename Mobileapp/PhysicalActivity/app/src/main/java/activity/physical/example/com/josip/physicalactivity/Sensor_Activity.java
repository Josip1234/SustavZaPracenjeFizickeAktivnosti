package activity.physical.example.com.josip.physicalactivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Sensor_Activity extends AppCompatActivity implements SensorEventListener {
private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate=0;
    private float last_x,last_y,last_z;
    private static final int SHAKE_THRESHOLD=600;
    private TextView tv1,tv2,tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_);
        senSensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer=senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this,senAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
    Sensor mySensor = sensorEvent.sensor;
        if(mySensor.getType()==Sensor.TYPE_ACCELEROMETER){
            float x= sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime=System.currentTimeMillis();
            if((curTime-lastUpdate)>100){
                long difftime=(curTime-lastUpdate);
                lastUpdate=curTime;


                last_x=x;
                last_y=y;
                last_z=z;

                changeText();



            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    protected void onPause(){
        super.onPause();
        senSensorManager.unregisterListener(this);
    }
    protected void onResume(){
        super.onResume();
        senSensorManager.registerListener(this,senAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void changeText(){
        tv1=(TextView) findViewById(R.id.x);
        tv2=(TextView) findViewById(R.id.y);
        tv3=(TextView) findViewById(R.id.z);

        tv1.setText(Float.toString(last_x));
        tv2.setText(Float.toString(last_y));
        tv3.setText(Float.toString(last_z));

    }
}
