package activity.physical.example.com.josip.physicalactivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   public void prijava(View v){
       EditText email;
       EditText sifra;
       TextView tv;
       String em,ps;
       email=(EditText) findViewById(R.id.email);
       sifra=(EditText) findViewById(R.id.sifra);
      tv = (TextView) findViewById(R.id.txt);
       em=email.getText().toString();
       ps=sifra.getText().toString();
       tv.setText(em+ps);
       Login login = new Login(em,ps);
       login.setPassword(ps);
       login.setUsername(em);

   }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    };


}