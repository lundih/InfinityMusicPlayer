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
import java.util.HashSet;

public class ArtistsActivity extends AppCompatActivity {

    ArrayList<String> artists = new ArrayList<String>();
    ListView listView;
    ArtistAdapter artistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findArtists();
        fillListView();

        //count number of artists and display the total
        int artistCount = artists.size();
        TextView count = (TextView) findViewById(R.id.textViewArtistCount);
        count.setText(getString(R.string.number_of_artists, artistCount));

        Button button = (Button) findViewById(R.id.buttonNowPlaying);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArtistsActivity.this, NowPlaying.class);
                startActivity(intent);
            }
        });
    }

    // find artists on storage and populate the ArrayList
    public void findArtists(){
        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null, null, null,null);

        if (songCursor != null && songCursor.moveToFirst()) {
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

            do{
                String artist = songCursor.getString(songArtist);
                artists.add(artist);
            }while(songCursor.moveToNext());

            //use HashSet to remove duplicates
            HashSet<String> hashSet = new HashSet<>();
            hashSet.addAll(artists);
            artists.clear();
            artists.addAll(hashSet);
        }
    }

    //use ArtistsAdapter to populate ListView with content
    public void fillListView(){
        artistAdapter= new ArtistAdapter(this, artists);
        sortList();
        listView  = (ListView) findViewById(R.id.listViewArtists);
        listView.setAdapter(artistAdapter);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //sort the artists in the ArrayList alphabetically with respect to artist name
    private void sortList() {
     Collections.sort(artists, new Comparator<String>() {
         @Override
         public int compare(String o1, String o2) {
             return o1.compareTo(o2);
         }
     });
    }
}
