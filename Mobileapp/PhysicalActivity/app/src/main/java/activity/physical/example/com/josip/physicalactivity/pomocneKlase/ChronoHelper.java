package activity.physical.example.com.josip.physicalactivity.pomocneKlase;

import android.os.SystemClock;
import android.widget.Chronometer;


/**
 * Created by Korisnik on 5.3.2018..
 */

public class ChronoHelper {
   private long vrijemeZaustavljanja;
    private Chronometer chronometer;
    public String vrijeme="";

    public ChronoHelper() {
    }

    public ChronoHelper( Chronometer chronometer) {
        this.vrijemeZaustavljanja = 0;
        this.chronometer = chronometer;
        this.vrijeme = "";
    }

    public long getVrijemeZaustavljanja() {
        return vrijemeZaustavljanja;
    }

    public void setVrijemeZaustavljanja(long vrijemeZaustavljanja) {
        this.vrijemeZaustavljanja = vrijemeZaustavljanja;
    }

    public Chronometer getChronometer() {
        return chronometer;
    }

    public void setChronometer(Chronometer chronometer) {
        this.chronometer = chronometer;
    }

    public String getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(String vrijeme) {
        this.vrijeme = vrijeme;
    }
    public void resetc() {
        getChronometer().setBase(SystemClock.elapsedRealtime());
        setVrijemeZaustavljanja(0);
        getChronometer().stop();
    }
    public void startcr() {
        getChronometer().setBase(SystemClock.elapsedRealtime() + getVrijemeZaustavljanja());
        getChronometer().start();
    }

    public String stopcr() {
        String time = "";
        setVrijemeZaustavljanja(getChronometer().getBase() - SystemClock.elapsedRealtime());
        getChronometer().setBase(SystemClock.elapsedRealtime());

        getChronometer().stop();
        return time;

    }


    public String getTime() {

        setVrijeme(getChronometer().getText().toString());
        return getVrijeme();

    }
    public String dohvatiRealnoVrijeme(){
        return String.valueOf(Math.abs(getVrijemeZaustavljanja()));
    }

}
