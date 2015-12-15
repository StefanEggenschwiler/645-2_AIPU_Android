package ch.hevs.aipu_2016_guide;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ch.hevs.aipu_2016_guide.adapter.ProgramAdapter;
import ch.hevs.aipu_2016_guide.database.SQLModel;
import ch.hevs.aipu_2016_guide.database.SQLiteHelper;
import ch.hevs.aipu_2016_guide.object.Room;
import ch.hevs.aipu_2016_guide.object.Talk;

/**
 * Created by Fabien on 11.12.2015.
 */
public class ShowProgram extends Fragment {


    //the hashmap contains all the talks divide by date
    HashMap<String, List<Talk>> talks = new HashMap<String, List<Talk>>();
    //talks_date contains all the dates of the conference
    List<String> talks_date = new ArrayList<String>();
    ExpandableListView exp_list;
    ProgramAdapter adapter;
    View rootView;



    public ShowProgram() {
        // Empty constructor required for fragment subclasses

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.program, container, false);
        exp_list = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
        exp_list.setClickable(true);

        //fill the hashmap with the talks, so we can use it in the adapter later
        FillHashMap();

        adapter = new ProgramAdapter(getActivity(),talks,talks_date);
        exp_list.setAdapter(adapter);


        return rootView;
    }

    public void FillHashMap()
    {

        //create a list of the dates of the conference
        List<Talk> talklist;
        talks_date.add("06/06/2016");
        talks_date.add("07/06/2016");
        talks_date.add("08/06/2016");
        talks_date.add("09/06/2016");

        //for each date, we want to retrieve all the talks that happened in this date
        for(int i = 0 ; i<talks_date.size() ; i++) {

            talklist = new ArrayList<Talk>();
            talklist = GetTalksbyDate(talks_date.get(i));
            talks.put(talks_date.get(i), talklist);

        }


    }


    //Get all the talks according to the date
    public List<Talk> GetTalksbyDate(String date)
    {

        SQLiteHelper dbHelper = new SQLiteHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //create the format of our date, in this case it will be like this : 08/06/2016 18:00:00
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Room room;
        Date startDate = new Date();
        Date endDate = new Date();
        List<Talk> talks = new ArrayList<Talk>();

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

        //We want to find all the talks according to the date
        Cursor c = db.query(false, SQLModel.TalksEntry.Table_Talks, projection, "StartDate LIKE '%"+date+"%'", null, null, null, "StartDate ASC",null);

        c.moveToFirst();

        //for each talk that we found, we put the talk in the array
        while(!c.isAfterLast()){

            try {
                //convert the startDate and the endDate that in format String to the format Date according to our SimpleDateFormat
                startDate = sdf.parse(c.getString(3));
                endDate = sdf.parse(c.getString(4));
            } catch (ParseException e) {
                //Handle exception here, most of the time you will just log it.
                e.printStackTrace();
            }

            //get the room of the talk
            room = searchRoombyId(Integer.parseInt(c.getString(5)));
            Talk talk = new Talk((Integer.parseInt(c.getString(0))),c.getString(1),c.getString(2),startDate,endDate,room,c.getString(6));
            talks.add(talk);
            c.moveToNext();
        }

        db.close();

        return talks;

    }

    //get the room according to the id in parameter
    public Room searchRoombyId(int id)
    {
        SQLiteHelper dbHelper = new SQLiteHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String [] projection = {
                SQLModel.RoomsEntry.KEY_ID,
                SQLModel.RoomsEntry.KEY_NAME,
                SQLModel.RoomsEntry.KEY_FLOOR,
                SQLModel.RoomsEntry.KEY_TIMESTAMP
        };

        //We want to find the room of the talk
        Cursor c = db.query(false, SQLModel.RoomsEntry.Table_Rooms, projection, "IdRoom = '"+id+"'", null, null, null, null,null);

        c.moveToFirst();

        Room room = new Room((Integer.parseInt(c.getString(0))),c.getString(1),c.getString(2));

        db.close();

        return room;
    }



}
