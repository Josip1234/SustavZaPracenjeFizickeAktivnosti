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


    public boolean provjeriJesuLiSveTockePopunjene(double lat1, double lon1, double lat2, double lon2){

        if(lat1!=0.00 || lat1!=00.00){
            if(lon1!=0.00 || lon1!=00.00){
                if(lat2!=0.00 || lat2!=00.00){
                    if(lon2!=0.00 || lon2!=00.00){
                          return true;
                    }else{
                        return false;
                    }

                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }





    }

    public  double distance(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371; // In kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }



    public double izracunajBrzinuUkm(double udaljenost){
        return udaljenost/(vrijemeIzmedjuTocaka(getVrijeme1(),getVrijeme2()));
    }
    public double vrijemeIzmedjuTocaka(long vrijeme1,long vrijeme2){
        if(vrijeme2>vrijeme1){
            return (vrijeme2-vrijeme1)/1000;
        }else{
            return (vrijeme1-vrijeme2)/1000;
        }

    }

}
