package activity.physical.example.com.josip.physicalactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import activity.physical.example.com.josip.physicalactivity.pomocneKlase.StatistickiIzracuni;

public class RezultActivity extends AppCompatActivity {

    private StatistickiIzracuni statistickiIzracuni;
    private TextView mUkupanBrojKoraka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezult);

        statistickiIzracuni = new StatistickiIzracuni();

        mUkupanBrojKoraka=(TextView) findViewById(R.id.ukbk);
        mUkupanBrojKoraka.setText(String.valueOf(statistickiIzracuni.getUkupanBrojKoraka()));





    }
}
