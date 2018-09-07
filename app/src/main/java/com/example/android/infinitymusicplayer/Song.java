package com.example.android.infinitymusicplayer;

public class Song {

    private String mSongName;
    private String mArtistName;
    private String mAlbumName;

    public String getSongName() {
        return mSongName;
    }

    public String getArtistName(){
        return  mArtistName;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public Song(String songName, String artistName, String albumName){
        mSongName = songName;
        mArtistName = artistName;
        mAlbumName = albumName;
    }
}
