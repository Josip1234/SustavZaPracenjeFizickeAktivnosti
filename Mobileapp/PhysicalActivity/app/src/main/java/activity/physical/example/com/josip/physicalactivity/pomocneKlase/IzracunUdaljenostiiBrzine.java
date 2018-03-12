package activity.physical.example.com.josip.physicalactivity.pomocneKlase;

/**
 * Created by Korisnik on 12.3.2018..
 */

public class IzracunUdaljenostiiBrzine {
    private double lat1;
    private double lon1;
    private long vrijeme1;
    private double lat2;
    private double lon2;
    private long vrijeme2;

    public IzracunUdaljenostiiBrzine(double lat1, double lon1, long vrijeme1, double lat2, double lon2, long vrijeme2, String unit) {
        this.lat1 = lat1;
        this.lon1 = lon1;
        this.vrijeme1 = vrijeme1;
        this.lat2 = lat2;
        this.lon2 = lon2;
        this.vrijeme2 = vrijeme2;
        this.unit = unit;
    }

    public long getVrijeme1() {
        return vrijeme1;
    }

    public void setVrijeme1(long vrijeme1) {
        this.vrijeme1 = vrijeme1;
    }

    public long getVrijeme2() {
        return vrijeme2;
    }

    public void setVrijeme2(long vrijeme2) {
        this.vrijeme2 = vrijeme2;
    }

    private String unit;

    public IzracunUdaljenostiiBrzine() {
    }



    public double getLat1() {
        return lat1;
    }

    public void setLat1(double lat1) {
        this.lat1 = lat1;
    }

    public double getLon1() {
        return lon1;
    }

    public void setLon1(double lon1) {
        this.lon1 = lon1;
    }

    public double getLat2() {
        return lat2;
    }

    public void setLat2(double lat2) {
        this.lat2 = lat2;
    }

    public double getLon2() {
        return lon2;
    }

    public void setLon2(double lon2) {
        this.lon2 = lon2;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    public  double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    public  double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    public double izracunajBrzinuUkm(double udaljenost){
        return udaljenost/(vrijemeIzmedjuTocaka(getVrijeme1(),getVrijeme2())*3600);
    }
    public double vrijemeIzmedjuTocaka(long vrijeme1,long vrijeme2){
        return (vrijeme2-vrijeme1)/1000;
    }

}
