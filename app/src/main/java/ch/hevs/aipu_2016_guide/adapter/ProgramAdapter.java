package ch.hevs.aipu_2016_guide.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import ch.hevs.aipu_2016_guide.R;
import ch.hevs.aipu_2016_guide.DetailsTalk;
import ch.hevs.aipu_2016_guide.object.Talk;

/**
 * Created by Fabien on 11.12.2015.
 */
public class ProgramAdapter extends BaseExpandableListAdapter {

    Context context;
    private HashMap<String, List<Talk>> talks;
    private List<String> talks_date;


    /*set the constructor of the adapter
    Hashmap is the list of the talks divide according to the date
    Talks_date is the list of the date
     */
    public ProgramAdapter(Context context, HashMap<String, List<Talk>> talks, List<String> talks_date){
        this.context = context;
        this.talks = talks;
        this.talks_date = talks_date;
    }

    @Override
    public int getGroupCount() {
        return talks.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return talks.get(talks_date.get(groupPosition)).size();
    }


    //get the date of the talk according to his id
    @Override
    public Object getGroup(int groupPosition) {
        return talks_date.get(groupPosition);
    }

    //get the talk according to date of the talk and his id
    @Override
    public Talk getChild(int groupPosition, int childPosition) {
        return talks.get(talks_date.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /*the groupView display the dates of the talks
    groupPosition is the id of the date
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            // LayoutInflater class is used to instantiate layout XML file into its corresponding View objects.
            convertView = LayoutInflater.from(context).inflate(R.layout.parent_layout, null);
        }

        // find the date of all the talks
        String grouptitle = (String) getGroup(groupPosition);
        TextView title = (TextView) convertView.findViewById(R.id.tw_date);
        // set the date of all the talks
        title.setText(grouptitle);

        return convertView;
    }

    /*the childview display the informations of each talks
    groupPosition is the date of the talk
    childPosition is the id of the talk
     */
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            // LayoutInflater class is used to instantiate layout XML file into its corresponding View objects.
            convertView = LayoutInflater.from(context).inflate(R.layout.child_layout, null);
        }

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        //find the information of the talks according to the date and the id of the talk
        String child_title = getChild(groupPosition, childPosition).getTitle();
        String room_name = getChild(groupPosition,childPosition).getRoom().getName();
        String starthour = df.format(getChild(groupPosition,childPosition).getStartDate());

        //we only want to find the starthour of the talk
        starthour = starthour.substring(11,16);

        //find the textview of the layout
        TextView room = (TextView) convertView.findViewById(R.id.tw_room);
        TextView title = (TextView) convertView.findViewById(R.id.tw_title_talks);
        TextView hour = (TextView) convertView.findViewById(R.id.tw_beginHour);

        //set the informations of the talks in the textview of the expandablelistview
        title.setText(child_title);
        room.setText(room_name);
        hour.setText(starthour);

        //we set a clicklistener because we want to display the talk informations when we click on it
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), DetailsTalk.class);
                //find the id of the talk and put it in the intent
                intent.putExtra("id",getChild(groupPosition,childPosition).getIdTalk());
                context.startActivity(intent);

            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
