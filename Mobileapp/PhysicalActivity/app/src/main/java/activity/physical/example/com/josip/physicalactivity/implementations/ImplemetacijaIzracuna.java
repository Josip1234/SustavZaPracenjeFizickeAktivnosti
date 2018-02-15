package activity.physical.example.com.josip.physicalactivity.implementations;

import activity.physical.example.com.josip.physicalactivity.model.Izracun;

/**
 * Created by Korisnik on 15.2.2018..
 */

public class ImplemetacijaIzracuna implements Izracun {
    @Override
    public boolean izracunUkupneProsjecneBrzine() {

        return false;
    }

    @Override
    public boolean izracunUkupneUdaljenosti() {
        return false;
    }

    @Override
    public boolean izracunUkupnogBrojaKoraka() {
        return false;
    }
}
