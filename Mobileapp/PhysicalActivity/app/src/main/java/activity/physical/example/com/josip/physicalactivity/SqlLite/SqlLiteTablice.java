package activity.physical.example.com.josip.physicalactivity.SqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Korisnik on 21.2.2018..
 */

public class SqlLiteTablice extends SQLiteOpenHelper {
    private static final String ime_baze="korisnici.db";
    private static final int verzija=1;
    public static final String tablica_korisnik="korisnik";
    public static final String id_stupca = "id";
    public static final String korisnik="email";
    public static final String sifra="pass";

    private static final int verzijaHodanja=1;
    private static final String tablica_walking="walking.db";
    public static final String brojStupca="brojStupca";
    public static final String udaljenost="udaljenost";
    public static final String vrijemeAktivnosti="vrijemeAktivnosti";
    public static final String koraci="koraci";
    public static final String adresa="adresa";
    public static final String longituda="longituda";
    public static final String latituda="latituda";
    public static final String brzinaUkm="brzinaUkm";
    public static final String datumIvrijeme="datumIvrijeme";

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
