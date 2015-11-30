package ch.hevs.aipu_2016_guide;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsSpeaker extends AppCompatActivity {

    Intent intent;
    TextView tw_speaker_name, tw_speaker_email, tw_speaker_function, tw_speaker_website, tw_speaker_informations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_speaker);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();

        tw_speaker_name = (TextView) findViewById(R.id.tw_speakername);
        tw_speaker_email = (TextView) findViewById(R.id.tw_speakeremail);
        tw_speaker_function = (TextView) findViewById(R.id.tw_speakerfunction);
        tw_speaker_website = (TextView) findViewById(R.id.tw_speakerwebsite);
        tw_speaker_informations = (TextView) findViewById(R.id.tw_speakersinformations);

        tw_speaker_name.setText(intent.getExtras().getString("firstname") + " " + intent.getExtras().getString("lastname"));
        tw_speaker_email.setText(intent.getExtras().getString("email"));
        tw_speaker_function.setText(intent.getExtras().getString("function"));
        tw_speaker_website.setText(intent.getExtras().getString("website"));
        tw_speaker_informations.setText(intent.getExtras().getString("informations"));
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

}
