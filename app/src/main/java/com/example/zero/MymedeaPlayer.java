package com.example.zero;
import android.media.MediaPlayer;


public class MymedeaPlayer {
    static  MediaPlayer instance;

    public static MediaPlayer getInstance() {
        if (instance == null) {
            instance=new MediaPlayer();
        }
         return instance;
    }
    public static int currentIndex=-1;
}
