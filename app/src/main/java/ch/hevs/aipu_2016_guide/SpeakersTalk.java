package ch.hevs.aipu_2016_guide;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ch.hevs.aipu_2016_guide.adapter.ListSpeakersAdapter;
import ch.hevs.aipu_2016_guide.database.SQLModel;
import ch.hevs.aipu_2016_guide.database.SQLiteHelper;
import ch.hevs.aipu_2016_guide.object.Speaker;
import ch.hevs.aipu_2016_guide.object.Talk;

/**
 * Created by Fabien on 12.12.2015.
 */
public class SpeakersTalk extends Fragment {

    Intent intent;
    Talk talk;
    ArrayList<Speaker> speakers = new ArrayList<Speaker>();
    ListView lw_speakers;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.talk_speakers, container, false);



        intent = getActivity().getIntent();

        //get all the speakers of the talk
        speakers = getSpeakers(intent.getExtras().getInt("id"));

        lw_speakers = (ListView) rootView.findViewById(R.id.lw_talkspeakers);


        GenerateListView();

        return rootView;

    }

    //With this method we return all the speakers that will present the talk
    public ArrayList<Speaker> getSpeakers(int idtalk)
    {
        SQLiteHelper dbHelper = new SQLiteHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Speaker> speakers = new ArrayList<Speaker>();

        String [] projection = {
                SQLModel.TalkSpeakerEntry.KEY_IDTALK,
                SQLModel.TalkSpeakerEntry.KEY_IDSPEAKER
        };

        //search all the speakers according to the talk id
        Cursor c = db.query(false, SQLModel.TalkSpeakerEntry.Table_TalksSpeaker, projection, "IdTalk = '"+idtalk+"'", null, null, null, null,null);

        c.moveToFirst();

        //for each speaker in the database, we put the speaker in the array
        while(!c.isAfterLast()){

            Speaker speaker = getSpeakerbyId(Integer.parseInt(c.getString(1)));
            speakers.add(speaker);
            c.moveToNext();
        }

        db.close();

        return speakers;

    }

    //return all the speaker according to the id
    public Speaker getSpeakerbyId(int id)
    {
        SQLiteHelper dbHelper = new SQLiteHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String [] projection = {
                SQLModel.SpeakersEntry.KEY_ID,
                SQLModel.SpeakersEntry.KEY_NAME,
                SQLModel.SpeakersEntry.KEY_FIRSTNAME,
                SQLModel.SpeakersEntry.KEY_EMAIL,
                SQLModel.SpeakersEntry.KEY_FUNCTION,
                SQLModel.SpeakersEntry.KEY_COMPANY,
                SQLModel.SpeakersEntry.KEY_WEBSITE,
                SQLModel.SpeakersEntry.KEY_INFORMATIONS,
                SQLModel.SpeakersEntry.KEY_PICTURE,
                SQLModel.SpeakersEntry.KEY_TIMESTAMP
        };

        //We want to find all the speakers in the database
        Cursor c = db.query(false, SQLModel.SpeakersEntry.Table_Speaker, projection, "IdSpeaker = '"+id+"'", null, null, null, null,null);

        c.moveToFirst();

        //we create the new speaker
        InputStream inputStream  = new ByteArrayInputStream(c.getBlob(8));
        Bitmap picture = BitmapFactory.decodeStream(inputStream);
        Speaker speaker = new Speaker((Integer.parseInt(c.getString(0))),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),picture);
        db.close();

        return speaker;

    }

    // Generate the listview of all the speakers
    private void GenerateListView() {


        //find the listview in the layout and apply the adapter
        ListView listViewspeakers = (ListView) rootView.findViewById(R.id.lw_talkspeakers);
        ListSpeakersAdapter ListAdapter = new ListSpeakersAdapter(getActivity(),speakers);
        listViewspeakers.setAdapter(ListAdapter);

        listViewspeakers.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        listViewspeakers.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {

                //get the speaker informations according to the user choice
                Speaker speakerchoose = speakers.get(position);

                //convert the picture in a array of byte to pass in the intent
                byte[] byteArray = convertBitmaptobyte(speakerchoose.getPicture());

                //create an intent to display the speaker's details when we click on the speaker
                Intent intent = new Intent(getActivity(), DetailsSpeaker.class);
                intent.putExtra("firstname", speakerchoose.getFirstname());
                intent.putExtra("lastname", speakerchoose.getName());
                intent.putExtra("email", speakerchoose.getEmail());
                intent.putExtra("function", speakerchoose.getFunction());
                intent.putExtra("company", speakerchoose.getCompany());
                intent.putExtra("website", speakerchoose.getWebsite());
                intent.putExtra("informations", speakerchoose.getInformations());
                intent.putExtra("picture", byteArray);
                //based on item add info to intent
                startActivity(intent);

            }


        });


    }

    //convert the picture in a array of byte to pass in the intent
    public byte[] convertBitmaptobyte(Bitmap image){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();

    }
}
