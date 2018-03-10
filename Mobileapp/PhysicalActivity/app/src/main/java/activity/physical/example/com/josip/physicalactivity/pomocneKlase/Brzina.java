package activity.physical.example.com.josip.physicalactivity.pomocneKlase;

/**
 * Created by Korisnik on 10.3.2018..
 */

public class Brzina {

    private double lon1;
    private double lat1;
    private double lon2;
    private double lat2;

    public Brzina(double lon1, double lat1, double lon2, double lat2) {
        this.lon1 = lon1;
        this.lat1 = lat1;
        this.lon2 = lon2;
        this.lat2 = lat2;
    }

    public double getLon1() {
        return lon1;
    }

    public void setLon1(double lon1) {
        this.lon1 = lon1;
    }

    public double getLat1() {
        return lat1;
    }

    public void setLat1(double lat1) {
        this.lat1 = lat1;
    }

    public double getLon2() {
        return lon2;
    }

    public void setLon2(double lon2) {
        this.lon2 = lon2;
    }

    public double getLat2() {
        return lat2;
    }

    public void setLat2(double lat2) {
        this.lat2 = lat2;
    }
    public double geografska_udaljenost(double lat1,double lon1,double lat2,double lon2){
        //pretvaranje stupnjeva u radijane
        setLat1(lat1);
        setLat2(lat2);
        setLon1(lon1);
        setLon2(lon2);

        lat1=getLat1()*Math.PI/180.0;
        lon1=getLon1()*Math.PI/180.0;

        lat2=getLat2()*Math.PI/180.0;
        lon2=getLon2()*Math.PI/180.0;

        //radijus zemlje u metrima

        double r=6378100;
        //P
        double rho1=r*Math.cos(lat1);
        double z1=r*Math.sin(lat1);
        double x1=rho1*Math.cos(lon1);
        double y1=rho1*Math.sin(lon1);
        //Q
        double rho2=r*Math.cos(lat2);
        double z2=r*Math.sin(lat2);
        double x2=rho2*Math.cos(lon2);
        double y2=rho2*Math.sin(lon2);

        //Dot product
        double dot=(x1*x2+y1*y2+z1+z2);
        double cos_theta=dot/(r*r);

        double theta=Math.acos(cos_theta);
        //udaljenost u metrima
        return r*theta;


    }

    public Brzina() {
    }

    public double izracunajBrzinu(double udaljenost, long vrijeme){
        double udaljenostUMetrima=geografska_udaljenost(getLat1(),getLon1(),getLat2(),getLon2());
        double brzinaUmetrimaUSekundi=udaljenostUMetrima/vrijeme;
        double brzinaUkilometrima=udaljenost/vrijeme;
        double brzinaUKilometrimaNaSat=(brzinaUmetrimaUSekundi*3600.0)/1000.0;
        return brzinaUkilometrima;
    }

}
