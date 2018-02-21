package activity.physical.example.com.josip.physicalactivity.model;

/**
 * Created by Josip on 3.8.2017..
 */

public class Registration {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String OIB;
    private String ime;
    private String prezime;
    private String spol;
    private String datumr;
    private String email;
    private String sifra;



    public Registration() {


    }
    public Registration(String username,String password) {
        this.email=username;
        this.sifra=password;
    }
    public Registration(String oib, String ime, String prezime, String username, String password) {
        this.OIB=oib;
        this.ime=ime;
        this.prezime=prezime;
        this.email=username;
        this.sifra=password;
    }


    public Registration(String OIB,String ime,String prezime,String spol,String datumr,String email,String sifra){
        this.OIB=OIB;
        this.ime=ime;
        this.prezime=prezime;
        this.spol=spol;
        this.datumr=datumr;

        this.email=email;
        this.sifra=sifra;

    }




    public void setOIB(String OIB) {
        this.OIB = OIB;
    }
    public void setIme(String ime) {

            this.ime = ime;

    }
    public void setPrezime(String prezime) {

            this.prezime = prezime;

    }
    public void setSpol(String spol) {
        this.spol = spol;
    }
    public void setDatumr(String datumr) {
        this.datumr = datumr;
    }


    public void setEmail(String email) {
        this.email = email;
    }
    public void setSifra(String password) {

            this.sifra = password;

    }


    public String getOIB() {
        return OIB;
    }
    public String getIme() {

        return ime;
    }
    public String getPrezime(){
        return prezime; //new String(prezime.getBytes ("iso-8859-1"), "UTF-8");
    }
    public String getSpol() {
        return spol;
    }
    public String getDatumr() {
        return datumr;
    }


    public String getEmail() {
        return email;
    }
    public String getSifra() {
        return sifra;
    }


    @Override
    public String toString(){

        return "Registration OIB:"+OIB+",ime:"+ime+",prezime:"+prezime+",spol:"+spol+",datumr:"+datumr+",Email:"+email+",sifra:"+sifra+"";

    }



}


