package ch.hevs.aipu_2016_guide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by Fabien on 04.12.2015.
 */
public class DetailsPartner extends AppCompatActivity {

    Intent intent;
    TextView tw_partner_name, tw_partner_website, tw_partner_informations;
    ImageView iw_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_partner);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();

        InputStream inputStream  = new ByteArrayInputStream(intent.getByteArrayExtra("picture"));
        Bitmap picture = BitmapFactory.decodeStream(inputStream);

        tw_partner_name = (TextView) findViewById(R.id.tw_partnername);
        tw_partner_website = (TextView) findViewById(R.id.tw_partnerwebsite);
        tw_partner_informations = (TextView) findViewById(R.id.tw_partnersinformations);
        iw_picture = (ImageView) findViewById(R.id.iw_picture_partner);



        tw_partner_name.setText(intent.getExtras().getString("name"));
        tw_partner_website.setText(intent.getExtras().getString("website"));
        tw_partner_informations.setText(intent.getExtras().getString("informations"));
        iw_picture.setImageBitmap(picture);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

}
