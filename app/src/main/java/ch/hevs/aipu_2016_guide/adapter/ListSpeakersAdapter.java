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
import ch.hevs.aipu_2016_guide.object.Speaker;

/**
 * Created by Fabien on 29.11.2015.
 */
public class ListSpeakersAdapter extends BaseAdapter {

    private ArrayList<Speaker> SpeakersArray;
    private Activity context;

    public ListSpeakersAdapter(Activity context, ArrayList<Speaker> SpeakersArray){
        this.context = context;
        this.SpeakersArray = SpeakersArray;
    }

    @Override
    public int getCount(){
        return SpeakersArray.size();
    }



    @Override
    public Object getItem(int position) {
        return SpeakersArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return SpeakersArray.get(position).getIdSpeaker();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            // LayoutInflater class is used to instantiate layout XML file into its corresponding View objects.
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_organisers, null);
        }

        //get the picture from the array
        Bitmap picture = SpeakersArray.get(position).getPicture();

        TextView speaker_name = (TextView) convertView.findViewById(R.id.tw_name);
        TextView speaker_function = (TextView) convertView.findViewById(R.id.tw_function);
        ImageView speaker_picture = (ImageView) convertView.findViewById(R.id.iw_picture);

        speaker_name.setText(SpeakersArray.get(position).getName() + " " + (SpeakersArray.get(position).getFirstname()));
        speaker_function.setText(SpeakersArray.get(position).getFunction() + " " + (SpeakersArray.get(position).getCompany()));
        speaker_picture.setImageBitmap(picture);



        return convertView;
    }

}
