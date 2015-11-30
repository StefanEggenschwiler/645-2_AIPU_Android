package ch.hevs.aipu_2016_guide;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

import ch.hevs.aipu_2016_guide.adapter.ListSpeakersAdapter;
import ch.hevs.aipu_2016_guide.object.Speaker;

/**
 * Created by Fabien on 28.11.2015.
 */
public class ShowSpeakers extends Fragment {

    ArrayList<Speaker> speakers = new ArrayList<Speaker>();

    public ShowSpeakers() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.show_speakers, container, false);
        Speaker speaker = new Speaker(1,"Emery", "Fabien", "fab@gmail.com", "CEO WWF", "www.wwf.ch", "An Asshole");
        Speaker speaker2 = new Speaker(2,"Arsic", "Sasa", "sasa@gmail.com", "Scrum Master", "www.arsicSM.ch", "Funny");
        speakers.add(speaker);
        speakers.add(speaker2);

        ListView listViewspeakers = (ListView) rootView.findViewById(R.id.listView_speakers);
        ListSpeakersAdapter ListAdapter = new ListSpeakersAdapter(getActivity(),speakers);
        listViewspeakers.setAdapter(ListAdapter);

        listViewspeakers.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {

               // ItemClicked item = adapter.getItem(position);

                Intent intent = new Intent(getActivity(), DetailsSpeaker.class);
                Speaker speakerchoose = speakers.get(position);
                intent.putExtra("firstname", speakerchoose.getFirstname());
                intent.putExtra("lastname", speakerchoose.getName());
                intent.putExtra("email", speakerchoose.getEmail());
                intent.putExtra("function", speakerchoose.getFunction());
                intent.putExtra("website", speakerchoose.getWebsite());
                intent.putExtra("informations", speakerchoose.getInformations());
                //based on item add info to intent
                startActivity(intent);

            }




        });

        return rootView;
    }


}
