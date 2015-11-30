package ch.hevs.aipu_2016_guide;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Fabien on 28.11.2015.
 */
public class ShowPartners extends Fragment {

    public ShowPartners() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.show_partners, container, false);

        return rootView;
    }

}
