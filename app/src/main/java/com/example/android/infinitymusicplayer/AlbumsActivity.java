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
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class AlbumsActivity extends AppCompatActivity {

    ArrayList<String> albums = new ArrayList<String>();
    GridView gridView;
    AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findAlbums();
        fillGridView();

        //count number of albums and display the total
        int albumCount = albums.size();
        TextView count = (TextView) findViewById(R.id.textViewAlbumCount);
        count.setText(getString(R.string.number_of_albums, albumCount));

        Button button = (Button) findViewById(R.id.buttonNowPlaying);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlbumsActivity.this, NowPlaying.class);
                startActivity(intent);
            }
        });
    }

    // find albums on storage and populate the ArrayList
    public void findAlbums(){
        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null, null, null,null);

        if (songCursor != null && songCursor.moveToFirst()) {
            int songAlbum = songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);

            do{
                String album = songCursor.getString(songAlbum);

                //Add instances of objects to the ArrayList
                albums.add(album);
            }while(songCursor.moveToNext());

            //use HashSet to remove duplicates
            HashSet<String> hashSet = new HashSet<>();
            hashSet.addAll(albums);
            albums.clear();
            albums.addAll(hashSet);
        }
    }

    //use AlbumAdapter to populate gridView with content
    public void fillGridView(){
        albumAdapter= new AlbumAdapter(this, albums);
        sortList();
        gridView = (GridView) findViewById(R.id.gridViewAlbums);
        gridView.setAdapter(albumAdapter);
    }
    //sort the albums in the ArrayList alphabetically with respect to artist name
    private void sortList() {
        Collections.sort(albums, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
