package activity.physical.example.com.josip.physicalactivity.pomocneKlase;

import java.util.List;

/**
 * Created by Korisnik on 9.3.2018..
 */

public class StatistickiIzracuni {
    private int ukupanBrojKoraka;

    public int getUkupanBrojKoraka() {
        return ukupanBrojKoraka;
    }

    public void setUkupanBrojKoraka(int ukupanBrojKoraka) {
        this.ukupanBrojKoraka = ukupanBrojKoraka;
    }

   
    public void izracunajUkupanBrojKoraka(List<Integer> koraci){
        int sum=0;
        for (Integer korak: koraci
             ) {
            sum+=korak;

        }
        setUkupanBrojKoraka(sum);

    }
}
