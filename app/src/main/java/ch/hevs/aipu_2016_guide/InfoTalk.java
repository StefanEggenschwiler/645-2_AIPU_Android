package ch.hevs.aipu_2016_guide;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.hevs.aipu_2016_guide.database.SQLModel;
import ch.hevs.aipu_2016_guide.database.SQLiteHelper;
import ch.hevs.aipu_2016_guide.object.Room;
import ch.hevs.aipu_2016_guide.object.Talk;

/**
 * Created by Fabien on 11.12.2015.
 */
public class InfoTalk extends Fragment {

    Intent intent;
    Talk talk;
    TextView tw_name, tw_room, tw_hour, tw_information;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    View rootView;

    public InfoTalk() {
        // Empty constructor required for fragment subclasses

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.info_talk, container, false);


        intent = getActivity().getIntent();
        talk = getTalkbyId(intent.getExtras().getInt("id"));

        //get the starthour and endhour by substring the entire date and only get the hour
        String starthour = df.format(talk.getStartDate()).substring(11, 16);
        String endhour = df.format(talk.getEndDate()).substring(11,16);

        //search all the textview of the layout
        tw_name = (TextView) rootView.findViewById(R.id.tw_talkname);
        tw_room = (TextView) rootView.findViewById(R.id.tw_talkroom);
        tw_hour = (TextView) rootView.findViewById(R.id.tw_talkhour);
        tw_information = (TextView) rootView.findViewById(R.id.tw_talkinformatiions);
        tw_hour = (TextView) rootView.findViewById(R.id.tw_talkhour);

        //set the text
        tw_name.setText(talk.getTitle());
        tw_room.setText("Room :" + talk.getRoom().getName());
        tw_information.setText(talk.getDescription());
        tw_hour.setText(starthour + " - " + endhour);

        return rootView;

    }


    public Talk getTalkbyId(int id)
    {

        SQLiteHelper dbHelper = new SQLiteHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Date startDate = new Date();
        Date endDate = new Date();
        Room room;
        Talk talk;

        String [] projection = {
                SQLModel.TalksEntry.KEY_ID,
                SQLModel.TalksEntry.KEY_TITLE,
                SQLModel.TalksEntry.KEY_DESCRIPTION,
                SQLModel.TalksEntry.KEY_STARTDATE,
                SQLModel.TalksEntry.KEY_ENDDATE,
                SQLModel.TalksEntry.KEY_IDROOM,
                SQLModel.TalksEntry.KEY_KEYWORDS,
                SQLModel.TalksEntry.KEY_TIMESTAMP
        };


        //We want to find the talk according to the id that we have passed in parameter
        Cursor c = db.query(false, SQLModel.TalksEntry.Table_Talks, projection, "IdTalk = '"+id+"'", null, null, null, null,null);

        c.moveToFirst();

        try {
            startDate = df.parse(c.getString(3));
            endDate = df.parse(c.getString(4));
        } catch (ParseException e) {
            //Handle exception here, most of the time you will just log it.
            e.printStackTrace();
        }

        //we want to find the entire room related to the talk
        room = getRoombyId(Integer.parseInt(c.getString(5)));
        talk = new Talk((Integer.parseInt(c.getString(0))),c.getString(1),c.getString(2),startDate,endDate,room,c.getString(6));

        db.close();

        return talk;

    }

    public Room getRoombyId(int id)
    {
        SQLiteHelper dbHelper = new SQLiteHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String [] projection = {
                SQLModel.RoomsEntry.KEY_ID,
                SQLModel.RoomsEntry.KEY_NAME,
                SQLModel.RoomsEntry.KEY_FLOOR,
                SQLModel.RoomsEntry.KEY_TIMESTAMP
        };

        //We want to find the room according to the id that we have passed in parameter
        Cursor c = db.query(false, SQLModel.RoomsEntry.Table_Rooms, projection, "IdRoom = '"+id+"'", null, null, null, null,null);

        c.moveToFirst();

        Room room = new Room((Integer.parseInt(c.getString(0))),c.getString(1),c.getString(2));

        db.close();

        return room;
    }





}
