package activity.physical.example.com.josip.physicalactivity.SqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public void zatvori(){

    }
}
