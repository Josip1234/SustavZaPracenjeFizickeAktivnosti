package activity.physical.example.com.josip.physicalactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class RezultActivity extends AppCompatActivity {
    private EditText datePicker;
    private String date;
    private Button mPokazi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezult);
        mPokazi=(Button) findViewById(R.id.pokaziRezultate);


        mPokazi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                datePicker=(EditText) findViewById(R.id.datePicker);
                date=String.valueOf(datePicker);
                System.out.println(date);
            }
        });

    }
}
