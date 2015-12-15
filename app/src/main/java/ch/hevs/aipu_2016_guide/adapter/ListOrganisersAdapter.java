package ch.hevs.aipu_2016_guide.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ch.hevs.aipu_2016_guide.R;
import ch.hevs.aipu_2016_guide.object.Organiser;

/**
 * Created by Fabien on 04.12.2015.
 */
public class ListOrganisersAdapter extends BaseAdapter {

    private ArrayList<Organiser> OrganisersArray;
    private Activity context;

    public ListOrganisersAdapter(Activity context, ArrayList<Organiser> OrganisersArray){
        this.context = context;
        this.OrganisersArray = OrganisersArray;
    }

    @Override
    public int getCount(){
        return OrganisersArray.size();
    }



    @Override
    public Object getItem(int position) {
        return OrganisersArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return OrganisersArray.get(position).getIdOrganiser();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            // LayoutInflater class is used to instantiate layout XML file into its corresponding View objects.
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_organisers, null);
        }

        //get the picture from the array
        Bitmap picture = OrganisersArray.get(position).getPicture();

        TextView organiser_name = (TextView) convertView.findViewById(R.id.tw_name);
        ImageView organiser_picture = (ImageView) convertView.findViewById(R.id.iw_picture);

        organiser_name.setText(OrganisersArray.get(position).getName() + " " + (OrganisersArray.get(position).getFirstname()));
        organiser_picture.setImageBitmap(picture);

        return convertView;
    }

}
