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
import ch.hevs.aipu_2016_guide.object.Partner;

/**
 * Created by Fabien on 04.12.2015.
 */
public class ListPartnersAdapter extends BaseAdapter {

    private ArrayList<Partner> PartnersArray;
    private Activity context;

    public ListPartnersAdapter(Activity context, ArrayList<Partner> PartnersArray){
        this.context = context;
        this.PartnersArray = PartnersArray;
    }

    @Override
    public int getCount(){
        return PartnersArray.size();
    }



    @Override
    public Object getItem(int position) {
        return PartnersArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return PartnersArray.get(position).getIdPartner();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            // LayoutInflater class is used to instantiate layout XML file into its corresponding View objects.
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_layout, null);
        }

        Bitmap picture = PartnersArray.get(position).getImage();

        TextView partner_name = (TextView) convertView.findViewById(R.id.organiser_name);
        ImageView partner_picture = (ImageView) convertView.findViewById(R.id.iw_picture_partner);

        partner_name.setText(PartnersArray.get(position).getName());
        partner_picture.setImageBitmap(picture);



        return convertView;
    }

}
