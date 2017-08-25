package activity.physical.example.com.josip.physicalactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ITMActivity extends AppCompatActivity {
    private static final String link="http://10.0.2.2:8080/physical/BMICalc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itm);
        WebView webView=(WebView) findViewById(R.id.webv);
        webView.loadUrl(link);
    }
}
