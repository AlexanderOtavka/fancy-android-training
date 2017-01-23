package edu.appdev.android.fancy;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Zander Otavka on 12/7/16.
 *
 * Async task to fetch a list of members from the appdev server.
 */

class FetchPeopleTask extends AsyncTask<Void, Void, ArrayList<ClubMember>> {

    private static final String URL = "http://www.cs.grinnell.edu/~pradhanp/android.json";

    @Override
    protected ArrayList<ClubMember> doInBackground(Void... params) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String bodyString = response.body().string();
            JSONObject result = new JSONObject(bodyString);
            JSONArray members = result.getJSONArray("members");
            if (members != null) {
                int len = members.length();
                ArrayList<ClubMember> list = new ArrayList<>(len);
                for (int i = 0; i < len; i++) {
                    JSONObject jsonMember = members.getJSONObject(i);
                    ClubMember member = new ClubMember();
                    member.name = jsonMember.getString("name");
                    member.role = jsonMember.getString("role");
                    list.add(i, member);
                }

                return list;
            } else {
                return null;
            }
        } catch (IOException | JSONException err) {
            err.printStackTrace();
            return null;
        }
    }
}
