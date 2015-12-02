package ch.hevs.aipu_2016_guide.database;

import android.app.ProgressDialog;
    import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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
public class RestSpeaker{
    ProgressDialog prgDialog;
    TextView errorMsg;

    public static final String REST_SPEAKERS_URL="https://aipu-conference-2016.appspot.com/resource/speakers/";











}
