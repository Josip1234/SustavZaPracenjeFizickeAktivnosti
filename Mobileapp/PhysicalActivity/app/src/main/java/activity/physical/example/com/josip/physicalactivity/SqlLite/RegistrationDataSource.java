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

    /*public Registration stvori(Registration registration){
        ContentValues values = new ContentValues();
        values.put(SqlLiteTablice.korisnik,registration.getEmail());
        values.put(SqlLiteTablice.sifra,registration.getSifra());
        long umetniId=database.insert(SqlLiteTablice.tablica_korisnik,null,values);
        registration.setId(umetniId);
        return registration;
    }*/
    public boolean pronadjiKorisnika(String username,String password){
        JSONArray JSONpolje = new JSONArray();
        JSONObject JSONobjekt;

        List<Registration> listaKorisnika=new ArrayList<Registration>();
        String[] result_col={SqlLiteTablice.korisnik,SqlLiteTablice.sifra};
        String where = SqlLiteTablice.korisnik+"=?";
        String[] where_args=new String[]{username};
        boolean found=false;

        //String[] res={SqlLiteTablice.korisnik,SqlLiteTablice.sifra};
        //String queryDb="SELECT * FROM"+SqlLiteTablice.tablica_korisnik+";";//WHERE"+SqlLiteTablice.korisnik+"="+username+"AND"+SqlLiteTablice.sifra+"="+password;
        //Cursor cursor = database.query()
         Cursor cursor = database.query(SqlLiteTablice.tablica_korisnik,result_col,where,where_args,null,null,null);
         if(cursor.getCount()>0){
             while (cursor.moveToNext()){
                 Registration korisnik=new Registration();
                 int indeksKorisnika=cursor.getColumnIndex(SqlLiteTablice.korisnik);
                 korisnik.setEmail(cursor.getString(indeksKorisnika));
                 int indeksSIfre=cursor.getColumnIndex(SqlLiteTablice.sifra);
                 korisnik.setSifra(cursor.getString(indeksSIfre));
                 System.out.println(korisnik.getEmail()+korisnik.getSifra());
                 listaKorisnika.add(new Registration(korisnik.getEmail(),korisnik.getSifra()));
             }
         }
         //
       /* try {
            if(cursor!=null){
                cursor.moveToFirst();
                try {
                    System.out.println(new Registration(cursor.getString(0),cursor.getString(1)).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(cursor.getString(0).contentEquals(username)){
                    if(cursor.getString(1).contentEquals(password)){
                        found=true;
                    }else{
                        found=false;
                    }
                }else{
                    found=false;
                }
            }else{
                found=false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
        for (Registration registration:listaKorisnika
             ) {
             if(username.equals(registration.getEmail())){
                 if(password.equals(registration.getSifra())){

                     found=true;
                 }else{
                     found=false;
                 }

             }else{
                 found=false;
             }

        }

        return found;
    }
    public void dodajKorisnika(Registration registration){
        ContentValues values = new ContentValues();
        values.put(SqlLiteTablice.korisnik,registration.getEmail());
        values.put(SqlLiteTablice.sifra,registration.getSifra());
        database.insert(SqlLiteTablice.tablica_korisnik,null,values);
    }
    public void izbrisiSve(){

        String DELETE_STATEMENT= "DELETE FROM" + SqlLiteTablice.tablica_korisnik;
        database.delete(SqlLiteTablice.tablica_korisnik,null,null);
        System.out.println("izbrisano");
    }
}
