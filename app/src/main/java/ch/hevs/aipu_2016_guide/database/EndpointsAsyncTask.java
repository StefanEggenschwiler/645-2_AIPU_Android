package ch.hevs.aipu_2016_guide.database;


import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.hevs.connected.backend.employeeApi.EmployeeApi;
import ch.hevs.connected.backend.employeeApi.model.Employee;
import ch.hevs.connected.backend.employeeApi.model.Phone;

public class EndpointsAsyncTask extends AsyncTask<Void, Void, List<Employee>> {
    private static SpeakerApi SpeakerApi = null;
    private static final String TAG = EndpointsAsyncTask.class.getName();
    private Speaker speaker;

    EndpointsAsyncTask(){}

    EndpointsAsyncTask(Speaker speaker){
        this.speaker = speaker;
    }

    @Override
    protected List<Speaker> doInBackground(Void... params) {

        if(SpeakerApi == null){
            // Only do this once
            EmployeeApi.Builder builder = new EmployeeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
		    // - after deploying change the address to your deployed server https://<your-name>.appspot.com
                    .setRootUrl("https://aipu-2016-conference.appspot.com/resource/speakers/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            SpeakerApi = builder.build();
        }

	//you would never do this two tasks in the same call - just for demonstration!
        try{
            //insert a new speaker
            if(speaker != null){
                speakerApi.insert(speaker).execute();
                Log.i(TAG, "insert speaker");
            }
		
	    //get all employees
            return speakerApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Employee>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Speaker> result){

        if(result != null) {
            for (Speaker speaker : result) {
                Log.i(TAG, "First name: " + speaker.getFirstname() + " Last name: "
                        + speaker.getLastname());
            }
        }
    }
}