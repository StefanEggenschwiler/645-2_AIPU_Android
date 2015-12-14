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
import android.widget.ListView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import ch.hevs.aipu_2016_guide.adapter.ListOrganisersAdapter;
import ch.hevs.aipu_2016_guide.database.SQLModel;
import ch.hevs.aipu_2016_guide.database.SQLiteHelper;
import ch.hevs.aipu_2016_guide.object.Organiser;

/**
 * Created by Fabien on 04.12.2015.
 */
public class ShowOrganisers extends Fragment {

    ArrayList<Organiser> organisers = new ArrayList<Organiser>();
    View rootView;



    public ShowOrganisers() {
        // Empty constructor required for fragment subclasses

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.show_listview, container, false);

        ReadSQL();
        GenerateListView();


        return rootView;
    }

    private void ReadSQL(){
        SQLiteHelper dbHelper = new SQLiteHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String [] projection = {
                SQLModel.OrganiserEntry.KEY_ID,
                SQLModel.OrganiserEntry.KEY_NAME,
                SQLModel.OrganiserEntry.KEY_FIRSTNAME,
                SQLModel.OrganiserEntry.KEY_FUNCTION,
                SQLModel.OrganiserEntry.KEY_COMPANY,
                SQLModel.OrganiserEntry.KEY_PICTURE,
                SQLModel.OrganiserEntry.KEY_TIMESTAMP
        };

        //We want to find all the organisers in the database
        Cursor c = db.query(false, SQLModel.OrganiserEntry.Table_Organisers, projection, null, null, null, null, "Name ASC",null);

        c.moveToFirst();

        while(!c.isAfterLast()){
            InputStream inputStream  = new ByteArrayInputStream(c.getBlob(5));
            Bitmap picture = BitmapFactory.decodeStream(inputStream);
            Organiser organiser = new Organiser((Long.parseLong(c.getString(0))),c.getString(1),c.getString(2),c.getString(3),c.getString(4),picture);
            organisers.add(organiser);
            c.moveToNext();
        }

        db.close();

    }


    // With this method, we will display all the organisers in the database
    private void GenerateListView() {


        ListView listVieworganiers = (ListView) rootView.findViewById(R.id.listView_display);
        ListOrganisersAdapter ListAdapter = new ListOrganisersAdapter(getActivity(),organisers);
        listVieworganiers.setAdapter(ListAdapter);

    }

}
