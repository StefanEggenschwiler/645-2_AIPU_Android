package ch.hevs.aipu_2016_guide.syncronisation;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;

import ch.hevs.aipu_2016_guide.object.News;
import ch.hevs.aipu_2016_guide.object.Room;

/**
 * Created by Stefan Eggenschwiler on 11-Dec-15.
 */
public class NewsSyncro extends AsyncTask<String, Void, List<News>> {
    @Override
    protected List<News> doInBackground(String... params) {
        // Initialize Gson
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyyMMdd'T'HHmmssZ").create();
        Type listType = new TypeToken<List<News>>() {}.getType();

        HttpURLConnection urlConnection = null;

        if(params.length > 0) {
            try {
                urlConnection = (HttpURLConnection) new URL(params[0]).openConnection();

                InputStream in = new BufferedInputStream(
                        urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                String json = "";
                while ((line = reader.readLine()) != null) {
                    json += line;
                }
                List<News> news = gson.fromJson(json, listType);


                return news;

            } catch (MalformedURLException e) {
                // URL is invalid
                // TODO: Handle exception.
            } catch (SocketTimeoutException e) {
                // data retrieval or connection timed out
                // TODO: Handle exception.
            } catch (IOException e) {
                // could not read response body
                // TODO: Handle exception.
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
        return null;
    }
}