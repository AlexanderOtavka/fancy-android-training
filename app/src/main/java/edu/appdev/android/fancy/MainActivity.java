package edu.appdev.android.fancy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String INTENT_IS_SIGNED_IN = "IS_SIGNED_IN";
    public static final String INTENT_USER_NAME = "USER_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!getIntent().getBooleanExtra(INTENT_IS_SIGNED_IN, false)) {
            startActivity(new Intent(getApplicationContext(), IntroActivity.class));
            finish(); // kill the current activity and remove it from the back stack
            return;
        }

        TextView textView = (TextView) findViewById(R.id.greeting_text);
        ListView clubMembersView = (ListView) findViewById(R.id.member_list);

        String name = getIntent().getStringExtra(INTENT_USER_NAME);
        textView.setText(getString(R.string.greeting_start) + " " + name +
                         getString(R.string.greeting_end));

        final ClubMembersAdapter clubMembersAdapter = new ClubMembersAdapter(this);
        clubMembersView.setAdapter(clubMembersAdapter);

        FetchPeopleTask task = new FetchPeopleTask() {
            @Override
            protected void onPostExecute(ArrayList<ClubMember> result) {
                clubMembersAdapter.clear();

                if (result != null) {
                    clubMembersAdapter.addAll(result);
                } else {
                    Log.e("MAIN", "error fetching people");
                }
            }
        };

        task.execute();
    }
}
