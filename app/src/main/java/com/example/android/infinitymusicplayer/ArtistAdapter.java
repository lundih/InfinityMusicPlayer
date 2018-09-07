package com.example.android.infinitymusicplayer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArtistAdapter extends ArrayAdapter<String> {

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.artist_list_item_layout, parent, false);
        }

        String currentArtist = getItem(position);

        TextView artistName = (TextView) listItemView.findViewById(R.id.textViewArtistName);
        artistName.setText(currentArtist);

        return listItemView;
    }

    public ArtistAdapter(Activity context, ArrayList<String> artist){
        super(context, 0, artist);
    }

}
