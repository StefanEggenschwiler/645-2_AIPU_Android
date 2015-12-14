package ch.hevs.aipu_2016_guide.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ch.hevs.aipu_2016_guide.database.SQLModel.*;
import ch.hevs.aipu_2016_guide.object.Organiser;
import ch.hevs.aipu_2016_guide.object.Room;
import ch.hevs.aipu_2016_guide.object.Speaker;

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
        db.execSQL(NewsEntry.CREATE_TABLE_NEWS);
        db.execSQL(PartnerEntry.CREATE_TABLE_PARTNER);
        db.execSQL(OrganiserEntry.CREATE_TABLE_ORGANISER);
        db.execSQL(RoomsEntry.CREATE_TABLE_ROOMS);
        db.execSQL(SpeakersEntry.CREATE_TABLE_SPEAKER);
        db.execSQL(TalksEntry.CREATE_TABLE_TALKS);
        db.execSQL(TalkSpeakerEntry.CREATE_TABLE_TALKSPEAKER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+NewsEntry.CREATE_TABLE_NEWS);
        db.execSQL("DROP TABLE IF EXISTS "+PartnerEntry.CREATE_TABLE_PARTNER);
        db.execSQL("DROP TABLE IF EXISTS "+OrganiserEntry.CREATE_TABLE_ORGANISER);
        db.execSQL("DROP TABLE IF EXISTS "+RoomsEntry.CREATE_TABLE_ROOMS);
        db.execSQL("DROP TABLE IF EXISTS "+SpeakersEntry.CREATE_TABLE_SPEAKER);
        db.execSQL("DROP TABLE IF EXISTS "+TalksEntry.CREATE_TABLE_TALKS);
        db.execSQL("DROP TABLE IF EXISTS "+TalkSpeakerEntry.CREATE_TABLE_TALKSPEAKER);
        onCreate(db);
    }
    public void addSpeaker(Speaker speaker)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Name",speaker.getName());
        values.put("Firstname",speaker.getFirstname());
        values.put("Email", speaker.getEmail());
        values.put("Function", speaker.getFunction());
        values.put("Company", speaker.getCompany());
        values.put("Website", speaker.getWebsite());
        values.put("Informations", speaker.getInformations());
        values.put("Picture", speaker.getPicture().toString());
        values.put("Timestamp", speaker.getTimestamp().toString());

        sqLiteDatabase.insert("speaker", null, values);
    }
    public void addRoom(Room room)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("IdRoom",room.getIdRoom());
        values.put("Name",room.getName());
        values.put("Floor",room.getFloor());
        values.put("Timestamp",room.getTimestamp().toString());
        sqLiteDatabase.insert("room", null, values);
        Log.i("Test","Room added");
    }

    public Long countRoom()
    {
        String sql="SELECT COUNT(*) FROM room";
        SQLiteStatement statement=db.compileStatement(sql);
        long count=statement.simpleQueryForLong();
        return count;
    }

    public String getMaxDateRoom()
    {
        String sql="SELECT MAX(Timestamp) from room";
        SQLiteStatement statement=db.compileStatement(sql);
        return statement.simpleQueryForString();

    }
}
