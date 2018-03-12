package activity.physical.example.com.josip.physicalactivity.SqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Korisnik on 21.2.2018..
 */

public class SqlLiteTablice extends SQLiteOpenHelper {
    private static final String ime_baze="aktivnosti.db";
    private static final int verzija=1;
    public static final String tablica_korisnik="korisnik";
    public static final String id_stupca = "id";
    public static final String korisnik="email";
    public static final String sifra="pass";





    private static final String stvori_tablicu =
            "CREATE TABLE " + tablica_korisnik + " (" +
                    id_stupca + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    korisnik + " TEXT, " +
                    sifra + " TEXT"+
                    ")";




    public SqlLiteTablice(Context context){
        super(context,ime_baze,null,verzija);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL(stvori_tablicu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ime_baze);
          onCreate(sqLiteDatabase);
    }
}
