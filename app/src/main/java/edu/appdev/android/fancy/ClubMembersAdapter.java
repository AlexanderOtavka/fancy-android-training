package edu.appdev.android.fancy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Adapt the JSONArray of club members into a list view.
 */
class ClubMembersAdapter extends ArrayAdapter<ClubMember> {
    ClubMembersAdapter(Context context) {
        super(context, 0, new ArrayList<ClubMember>());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ClubMember member = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_club_member, parent, false);
        }

        TextView nameView = (TextView) convertView.findViewById(R.id.name);
        TextView roleView = (TextView) convertView.findViewById(R.id.role);

        if (member != null) {
            nameView.setText(member.name);
            roleView.setText(member.role);
        } else {
            nameView.setText("");
            roleView.setText("");
        }

        return convertView;
    }
}
