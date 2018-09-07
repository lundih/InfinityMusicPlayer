package com.example.android.infinitymusicplayer;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SongsActivity extends AppCompatActivity {

    ArrayList<Song> songs = new ArrayList<Song>();
    ListView listView;
    SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findMusic();
        fillListView();

        //count number of artists and display the total
        int songCount = songs.size();
        TextView count = (TextView) findViewById(R.id.textViewSongCount);
        count.setText(getString(R.string.number_of_songs, songCount));

        Button button = (Button) findViewById(R.id.buttonNowPlaying);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongsActivity.this, NowPlaying.class);
                startActivity(intent);
            }
        });
    }

    // find music on storage and populate the ArrayList
    public void findMusic(){
        ContentResolver contentResolver =getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null, null, null,null);

        if (songCursor != null && songCursor.moveToFirst()) {
            int songName = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songAlbum = songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);

            do{
                String name = songCursor.getString(songName);
                String artist = songCursor.getString(songArtist);
                String album = songCursor.getString(songAlbum);
                //Add instances of objects to the ArrayList
                songs.add(new Song(name, artist, album));
            }while(songCursor.moveToNext());
        }
    }

    //use song adapter to populate ListView with content
    public void fillListView(){
        songAdapter = new SongAdapter(this, songs);
        sortList();
        listView  = (ListView) findViewById(R.id.listViewSongs);
        listView.setAdapter(songAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent  = new Intent(SongsActivity.this, NowPlaying.class);
            startActivity(intent);
            }
        });
    }

    //sort the songs in the ArrayList alphabetically with respect to song name
    private void sortList(){
        Collections.sort(songs, new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                return o1.getSongName().compareTo(o2.getSongName());
            }
        });
        songAdapter.notifyDataSetChanged();
    }

    //choose what the back button on the action bar does
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
