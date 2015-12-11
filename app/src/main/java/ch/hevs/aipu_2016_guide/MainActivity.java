package ch.hevs.aipu_2016_guide;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import ch.hevs.aipu_2016_guide.database.SQLiteHelper;
import ch.hevs.aipu_2016_guide.database.RequestWebService;
import ch.hevs.aipu_2016_guide.object.Speaker;

/**
 * Created by Fabien on 27.11.2015.
 */

//http://developer.android.com/training/implementing-navigation/nav-drawer.html
public class MainActivity extends AppCompatActivity  {

    private String[] items_menu;
    private CharSequence mTitle;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private TextView responseView;
    private ProgressBar progressBar;
    private List<Speaker> itemsList;

    private class RequestSpeakersServiceTask extends AsyncTask<Void,Void,Void>{
        private ProgressDialog dialog=new ProgressDialog(MainActivity.this);


            @Override
            protected void onPreExecute() {
                //dialog.setMessage("Please wait..");
                //dialog.show();
            }

            @Override
            protected Void doInBackground(Void... unused) {
                // The ItemService would contain the method showed
                // in the previous paragraph
                RequestWebService itemService = new RequestWebService();
                try {
                    itemsList = itemService.findAllItems();
                } catch (Throwable e) {
                    // handle exceptions
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {

                // setListAdapter must not be called at doInBackground()
                // since it would be executed in separate Thread
                for(Speaker speaker:itemsList)
                {
                    Log.i("Test",speaker.getFirstname());
                   // dialog.setMessage(speaker.getFirstname());
                   // dialog.show();
                }

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Create of database
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        RequestSpeakersServiceTask task=new RequestSpeakersServiceTask();
        task.execute();





        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        items_menu = getResources().getStringArray(R.array.items_menu);
        mTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_list_item_1, items_menu));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //Set the default fragment as the welcome page
        selectItem(0);


    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment;
        switch(position){
            case 0 : fragment = new WelcomePage();
                break;
            case 2 : fragment = new ShowSpeakers();
                break;
            case 3 : fragment = new ShowPartners();
                break;
            case 4 : fragment = new ShowOrganisers();
                break;
            default : fragment = new WelcomePage();
                break;
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(items_menu[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return mDrawerToggle.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

}