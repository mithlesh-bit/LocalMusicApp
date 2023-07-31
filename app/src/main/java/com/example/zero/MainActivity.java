package com.example.zero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import android.Manifest;
//import android.app.DownloadManager;
import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Environment;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.webkit.DownloadListener;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
import android.widget.Toast;

//import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
//import static android.content.pm.PackageManager.*;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView noMusicTextView;
    ArrayList<AudioModel> songList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycleView);
        noMusicTextView=findViewById(R.id.noSong);

        if (checkPermission()==false){
            requestPermission();
            return;
        }

        String[] projection={
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION

      };
        String selection=MediaStore.Audio.Media.IS_MUSIC +"!=0";

        Cursor cursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection, null,null);
      while (cursor.moveToNext()){
       AudioModel songData = new AudioModel(cursor.getString(1), cursor.getString(0),cursor.getString(2) );
       if(new File(songData.getPath()).exists())
        songList.add(songData);
      }
      if (songList.size()==0){
          noMusicTextView.setVisibility(View.VISIBLE);
      }else{
          recyclerView.setLayoutManager(new LinearLayoutManager(this));
          recyclerView.setAdapter(new musicListAdapter(songList,getApplicationContext()));
      }


    }


    boolean checkPermission(){
        int result=ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                if (result==PackageManager.PERMISSION_GRANTED){
                   return true;
                }else{
                    return false;
                }
    }
    void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(MainActivity.this,"Permission is required please Allow from setting",Toast.LENGTH_SHORT);
        }else
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
    }




}