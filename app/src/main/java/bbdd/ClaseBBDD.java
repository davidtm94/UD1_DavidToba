package bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.david.ud1_davidtoba.UD1_DavidToba;

import java.io.File;

/**
 * Created by david on 1/2/15.
 */
public class ClaseBBDD extends SQLiteOpenHelper {

    public final static String NOME_BD="DATOS";
    public final static int VERSION_BD=1;

    public ClaseBBDD(Context context){
        super(context, NOME_BD, null, VERSION_BD);
    }
    public ClaseBBDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, NOME_BD, factory, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
