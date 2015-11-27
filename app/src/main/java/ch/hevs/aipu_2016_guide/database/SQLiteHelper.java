package ch.hevs.aipu_2016_guide.database;

import android.content.Context;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Arnaud on 27.11.2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper{
    private SQLiteDatabase db;

    //Info about database
    private static final String DATABASE_NAME = "apu2016.db";
    private static final int DATABASE_VERSION = 1;
    private static SQLiteHelper instance;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }
    public static SQLiteHelper getInstance(Context context){
        if(instance == null){
            instance = new SQLiteHelper(context.getApplicationContext());
        }

        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
