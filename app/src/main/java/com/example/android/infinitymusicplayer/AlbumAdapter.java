package com.example.android.infinitymusicplayer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumAdapter extends ArrayAdapter<String> {

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridItemView = convertView;
        if (gridItemView == null){
            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.album_grid_item_layout, parent, false);
        }

        String currentAlbum = getItem(position);

        TextView artistName = (TextView) gridItemView.findViewById(R.id.textViewAlbumName);
        artistName.setText(currentAlbum);

        return gridItemView;
    }

    public AlbumAdapter(Activity context, ArrayList<String> album){
        super(context, 0, album);
    }

}
