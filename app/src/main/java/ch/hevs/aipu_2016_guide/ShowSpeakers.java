package ch.hevs.aipu_2016_guide;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;

import ch.hevs.aipu_2016_guide.adapter.ListSpeakersAdapter;
import ch.hevs.aipu_2016_guide.database.SQLModel;
import ch.hevs.aipu_2016_guide.database.SQLiteHelper;
import ch.hevs.aipu_2016_guide.object.Speaker;

/**
 * Created by Fabien on 28.11.2015.
 */
public class ShowSpeakers extends Fragment {

    ArrayList<Speaker> speakers = new ArrayList<Speaker>();
    View rootView;



    public ShowSpeakers() {
        // Empty constructor required for fragment subclasses

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.show_speakers, container, false);

        ReadSQL();
        GenerateListView();


        return rootView;
    }

    private void ReadSQL(){
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
        Cursor c = db.query(false, SQLModel.SpeakersEntry.Table_Speaker, projection, null, null, null, null, "Name ASC",null);

        c.moveToFirst();

        while(!c.isAfterLast()){
            InputStream inputStream  = new ByteArrayInputStream(c.getBlob(8));
            Bitmap picture = BitmapFactory.decodeStream(inputStream);
            Speaker speaker = new Speaker((Integer.parseInt(c.getString(0))),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),picture);
            speakers.add(speaker);
            c.moveToNext();
        }

        db.close();

    }

    // With this method, we will display all the patients in the database
    private void GenerateListView() {


        ListView listViewspeakers = (ListView) rootView.findViewById(R.id.listView_speakers);
        ListSpeakersAdapter ListAdapter = new ListSpeakersAdapter(getActivity(),speakers);
        listViewspeakers.setAdapter(ListAdapter);

        listViewspeakers.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {

                Speaker speakerchoose = speakers.get(position);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                speakerchoose.getPicture().compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

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


}
