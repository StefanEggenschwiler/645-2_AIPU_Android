package ch.hevs.aipu_2016_guide.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.util.Log;


import java.io.ByteArrayOutputStream;
import java.sql.SQLData;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ch.hevs.aipu_2016_guide.database.SQLModel.*;
import ch.hevs.aipu_2016_guide.object.News;
import ch.hevs.aipu_2016_guide.object.Organiser;
import ch.hevs.aipu_2016_guide.object.Partner;
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
        Log.i("Adding room", "Room added");
    }

    public void addNews(News news)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("IdNews",news.getId());
        values.put("Author",news.getAuthor());
        values.put("Title",news.getTitle());
        values.put("Article",news.getArticle());
        values.put("CreationDate",news.getCreationDate().toString());
        values.put("Timestamp",news.getLastmodified().toString());
        sqLiteDatabase.insert("news",null,values);
        Log.i("Adding news","news added");
    }

    public void addPartner(Partner partner)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("IdPartner",partner.getId());
        values.put("Name",partner.getName());
        values.put("Description", partner.getDescription());
        values.put("Website", partner.getWebsite());




        values.put("Timestamp",partner.getLastmodified().toString());
        sqLiteDatabase.insert("partner", null, values);
        Log.i("Adding partner", "partner added");
    }

    public Long countNews()
    {
        String sql="SELECT COUNT(*) FROM news";
        SQLiteStatement statement=db.compileStatement(sql);
        long count=statement.simpleQueryForLong();
        return count;
    }

    public Long countRoom()
    {
        String sql="SELECT COUNT(*) FROM room";
        SQLiteStatement statement=db.compileStatement(sql);
        long count=statement.simpleQueryForLong();
        return count;
    }
    public Long countPartner()
    {
        String sql="SELECT COUNT(*) FROM partner";
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
    public String getMaxDateNews()
    {
        String sql="SELECT MAX(Timestamp) from news";
        SQLiteStatement statement=db.compileStatement(sql);
        return statement.simpleQueryForString();
    }
    public String getMaxDatePartner()
    {
        String sql="SELECT MAX(Timestamp) from partner";
        SQLiteStatement statement=db.compileStatement(sql);
        return statement.simpleQueryForString();
    }
}
