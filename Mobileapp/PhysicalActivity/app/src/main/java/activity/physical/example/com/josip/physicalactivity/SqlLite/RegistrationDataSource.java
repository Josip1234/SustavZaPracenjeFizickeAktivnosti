package activity.physical.example.com.josip.physicalactivity.SqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import activity.physical.example.com.josip.physicalactivity.MainActivity;
import activity.physical.example.com.josip.physicalactivity.model.Registration;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Korisnik on 21.2.2018..
 */
//pomocna klasa za unos podataka u sql lite bazu te stvaranje upita na bazu
public class RegistrationDataSource {
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;



    private static final String[] sviStupci={
             SqlLiteTablice.id_stupca,
            SqlLiteTablice.korisnik,
            SqlLiteTablice.sifra
    };
    public RegistrationDataSource(Context context){
        dbhelper=new SqlLiteTablice(context);
    }



    public void otvori(){
        database=dbhelper.getWritableDatabase();
    }
    public void citaj(){
        database=dbhelper.getReadableDatabase();
    }
    public void zatvori() {
        dbhelper.close();

    }
    //funkcija za pronalaženje korisnika
    public boolean pronadjiKorisnika(String username,String password){

        //stvori listu korisnika
        List<Registration> listaKorisnika=new ArrayList<Registration>();
        //upit na bazu selektiraj
        String[] result_col={SqlLiteTablice.korisnik,SqlLiteTablice.sifra};
        //uvjet za selekciju stupac u tablici
        String where = SqlLiteTablice.korisnik+"=?";
        //uvjet
        String[] where_args=new String[]{username};
        boolean found=false;

        //kursor za kretanje po bazi
         Cursor cursor = database.query(SqlLiteTablice.tablica_korisnik,result_col,where,where_args,null,null,null);
         if(cursor.getCount()>0){
             //dok je kursor veći od nule
             while (cursor.moveToNext()){
                 //miči kursor do sljedećeg podatka
                 //stvori objekt korisnika
                 Registration korisnik=new Registration();
                 int indeksKorisnika=cursor.getColumnIndex(SqlLiteTablice.korisnik);
                 korisnik.setEmail(cursor.getString(indeksKorisnika));
                 int indeksSIfre=cursor.getColumnIndex(SqlLiteTablice.sifra);
                 korisnik.setSifra(cursor.getString(indeksSIfre));
                 //dohvati korisnika i spremi kao objekt
                 System.out.println(korisnik.getEmail()+korisnik.getSifra());
                 //dodaj u listu korisničke podatke
                 listaKorisnika.add(new Registration(korisnik.getEmail(),korisnik.getSifra()));
             }
         }
         //za svaki element u listi
        for (Registration registration:listaKorisnika
             ) {
             //provjeri korisnicko ime
             if(username.equals(registration.getEmail())){
                 //ako korisnicko ime postoji provjeri sifru
                 if(password.equals(registration.getSifra())){
                      //ako je korisnicko ime ispravno i sifra je ispravna vrati true inače vrati false
                     found=true;
                 }else{
                     found=false;
                 }

             }else{
                 found=false;
                 //vrati već false ako nema korisnika u bazi
             }

        }

        return found;
    }
    //funkcija za dodavanje korisnika u sql lite
    public void dodajKorisnika(Registration registration){
        ContentValues values = new ContentValues();
        values.put(SqlLiteTablice.korisnik,registration.getEmail());
        values.put(SqlLiteTablice.sifra,registration.getSifra());
        database.insert(SqlLiteTablice.tablica_korisnik,null,values);
    }
    //funkcija za brisanje podataka iz baze
    public void izbrisiSve(){

        String DELETE_STATEMENT= "DELETE FROM" + SqlLiteTablice.tablica_korisnik;
        database.delete(SqlLiteTablice.tablica_korisnik,null,null);
        System.out.println("izbrisano");
    }
}
