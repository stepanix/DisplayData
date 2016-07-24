package com.henry.displaydata.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.henry.displaydata.fragment.PersonDetailFragment;
import com.henry.displaydata.R;
import com.henry.displaydata.model.Person;
import com.henry.displaydata.model.PersonDetail;

/**
 * An activity representing a single Person detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link PersonListActivity}.
 */
public class PersonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);



        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        if (savedInstanceState == null)
        {
            Intent i = getIntent();
            Person person =  i.getExtras().getParcelable("person");
            PersonDetail detail =  i.getExtras().getParcelable("detail");
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(PersonDetailFragment.FAV_COLOR,detail.getFavouriteColour());
            arguments.putInt(PersonDetailFragment.AGE,detail.getAge());
            arguments.putString(PersonDetailFragment.FIRST_NAME,person.getFirstName());
            arguments.putString(PersonDetailFragment.LAST_NAME,person.getLastName());

            PersonDetailFragment fragment = new PersonDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.person_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, PersonListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
