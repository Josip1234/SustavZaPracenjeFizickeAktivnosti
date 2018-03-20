package activity.physical.example.com.josip.physicalactivity.SqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Korisnik on 21.2.2018..
 */
//klasa za kreiranje tablica, beze
public class SqlLiteTablice extends SQLiteOpenHelper {
    //napšravi naziv baze i verziju baze
    private static final String ime_baze="aktivnosti.db";
    private static final int verzija=1;
    //napravi tablicu pod nazivom
    public static final String tablica_korisnik="korisnik";
    //napravi stupce
    public static final String id_stupca = "id";
    public static final String korisnik="email";
    public static final String sifra="pass";


    //upit za stvaranje tablice
    private static final String stvori_tablicu =
            "CREATE TABLE " + tablica_korisnik + " (" +
                    id_stupca + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    korisnik + " TEXT, " +
                    sifra + " TEXT"+
                    ")";



    //pozivanje sql lite-a pomoću ostalih klasa
    public SqlLiteTablice(Context context){
        super(context,ime_baze,null,verzija);
    }
    //stvori bazu
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL(stvori_tablicu);

    }
    //ako se tablica nadograđuje, izbriši postojeću bazu
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ime_baze);
          onCreate(sqLiteDatabase);
    }
}
