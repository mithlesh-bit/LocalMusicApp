package com.example.zero;

import android.content.Context;
import android.content.Intent;
//import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import androidx.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class musicListAdapter extends RecyclerView.Adapter<musicListAdapter.ViewHolder> {


    ArrayList<AudioModel> songList;
    Context context;

    public musicListAdapter(ArrayList<AudioModel> songList, Context context) {
        this.songList = songList;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new musicListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(   musicListAdapter.ViewHolder holder,int position){
    AudioModel songData=songList.get(position);
    holder.titleTextView.setText(songData.getTitle());
/*
    holder.layout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //navigate to another activity
            MymedeaPlayer.getInstance().reset();
            MymedeaPlayer.currentIndex=position;
            Intent intent= new Intent(context,MusicPlayerActivity.class);
            intent.putExtra("LIST",songList);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
    });*/

    holder.layout.setOnClickListener(v->{
        MymedeaPlayer.getInstance().reset();
        MymedeaPlayer.currentIndex=position;
        Intent intent= new Intent(context,MusicPlayerActivity.class);
        intent.putExtra("LIST",songList);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    });

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

    TextView titleTextView;
    ImageView iconImageView;
    RelativeLayout layout;
        public ViewHolder( View itemView) {
            super(itemView);
        titleTextView = itemView.findViewById(R.id.title_text);
        iconImageView = itemView.findViewById(R.id.icon_View);
        layout = itemView.findViewById(R.id.layout);

        }
    }
}
