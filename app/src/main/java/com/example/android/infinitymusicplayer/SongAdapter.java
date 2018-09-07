package com.example.android.infinitymusicplayer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.song_list_item_layout, parent, false);
        }

        Song currentSong = getItem(position);

        TextView songName = (TextView) listItemView.findViewById(R.id.textviewSongName);
        songName.setText(currentSong.getSongName());

        TextView artistName = (TextView) listItemView.findViewById(R.id.textViewArtistName);
        artistName.setText(currentSong.getArtistName());

        TextView albumName = (TextView) listItemView.findViewById(R.id.textviewAlbumName);
        albumName.setText(currentSong.getAlbumName());

        return listItemView;
    }

    public SongAdapter(Activity context, ArrayList<Song> song){
        super(context, 0, song);
    }

}
