package ch.hevs.aipu_2016_guide.database;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import ch.hevs.aipu_2016_guide.object.Speaker;


/**
 * Created by Arnaud on 30.11.2015.
 */
public class RestSpeaker extends AsyncTask<Void,Void,String>{
    ProgressDialog prgDialog;
    TextView errorMsg;
    ProgressBar progressBar;
    TextView responseView;

    public RestSpeaker(ProgressBar progressBar, TextView responseView)
    {
        this.progressBar=progressBar;
        this.responseView=responseView;
    }

    public static final String REST_SPEAKERS_URL="https://aipu-conference-2016.appspot.com/resource/speakers/";


    protected void onPreExecute()
    {
        progressBar.setVisibility(View.VISIBLE);
        responseView.setText("");

    }

    protected String doInBackground(Void... urls)
    {
        try{
            URL url=new URL(REST_SPEAKERS_URL);
            HttpsURLConnection urlConnection=(HttpsURLConnection) url.openConnection();
            try{
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder=new StringBuilder();
                String line;
                while ((line=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(line).append("\n");

                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally {
                urlConnection.disconnect();
            }
        }catch (Exception e)
        {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response)
    {
        if(response==null)
        {
            response="There was an error!";
        }
        progressBar.setVisibility(View.GONE);
        Log.i("Info", response);
        responseView.setText(response);
    }











}
