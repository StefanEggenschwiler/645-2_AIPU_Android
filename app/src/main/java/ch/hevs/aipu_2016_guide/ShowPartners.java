package ch.hevs.aipu_2016_guide;

import android.app.Fragment;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import ch.hevs.aipu_2016_guide.adapter.ListPartnersAdapter;
import ch.hevs.aipu_2016_guide.database.SQLModel;
import ch.hevs.aipu_2016_guide.database.SQLiteHelper;
import ch.hevs.aipu_2016_guide.object.Partner;

/**
 * Created by Fabien on 28.11.2015.
 */
public class ShowPartners extends Fragment {

    ArrayList<Partner> partners = new ArrayList<Partner>();
    View rootView;



    public ShowPartners() {
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
                SQLModel.PartnerEntry.KEY_ID,
                SQLModel.PartnerEntry.KEY_NAME,
                SQLModel.PartnerEntry.KEY_DESCRIPTION,
                SQLModel.PartnerEntry.KEY_WEBSITE,
                SQLModel.PartnerEntry.KEY_LOGO,
                SQLModel.PartnerEntry.KEY_TIMESTAMP
        };

        //We want to find all the speakers in the database
        Cursor c = db.query(false, SQLModel.PartnerEntry.Table_Partner, projection, null, null, null, null, "Name ASC",null);

        c.moveToFirst();

        while(!c.isAfterLast()){
            InputStream inputStream  = new ByteArrayInputStream(c.getBlob(4));
            Bitmap picture = BitmapFactory.decodeStream(inputStream);
            Partner partner = new Partner((Integer.parseInt(c.getString(0))),c.getString(1),c.getString(2),c.getString(3),picture);
            partners.add(partner);
            c.moveToNext();
        }

        db.close();

    }

    // With this method, we will display all the speakers in the database
    private void GenerateListView() {


        ListView listViewpartners = (ListView) rootView.findViewById(R.id.listView_display);
        ListPartnersAdapter ListAdapter = new ListPartnersAdapter(getActivity(),partners);
        listViewpartners.setAdapter(ListAdapter);

        listViewpartners.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {

                Partner partnerchoose = partners.get(position);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                partnerchoose.getImage().compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent intent = new Intent(getActivity(), DetailsPartner.class);
                intent.putExtra("name", partnerchoose.getName());
                intent.putExtra("informations", partnerchoose.getDescription());
                intent.putExtra("website", partnerchoose.getWebsite());
                intent.putExtra("picture", byteArray);
                //based on item add info to intent
                startActivity(intent);

            }


        });


    }

}
