package activity.physical.example.com.josip.physicalactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.Switch;

public class WalkingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private Chronometer cr;
    private Switch sv;
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
}
