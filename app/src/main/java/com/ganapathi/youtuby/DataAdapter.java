package com.ganapathi.youtuby;

/**
 * Created by Ganapathi on 11-03-2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {
    private ItemClickListener clickListener;
    private ArrayList<productversion> android;
    private ArrayList<productversion> arraylist;
    private Context context;
    private Lifecycle lifecycle;


    public DataAdapter(Context context, ArrayList<productversion> android, Lifecycle lifecycle) {
        this.android = android;
        this.context = context;
        this.arraylist = new ArrayList<productversion>();
        this.arraylist.addAll(android);
        this.lifecycle = lifecycle;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);

        lifecycle.addObserver(view.findViewById(R.id.youtube_player_view));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder viewHolder,final int i) {
        productversion productversion = android.get(i);
        viewHolder.tv_android.setText(productversion.getAndroid_version_name());
        viewHolder.cueVideo(productversion.getAndroid_imagePath());
    }

    @Override
    public int getItemCount() {
        return android== null ? 0 : android.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<productversion> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(arraylist);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (productversion item : arraylist) {
                    if (item.getAndroid_version_name().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            android.clear();
            android.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_android;
        public YouTubePlayerView youTubePlayerView;
        private YouTubePlayer youTubePlayer;
        private String currentVideoId;

        public ViewHolder(final View view) {
            super(view);
            tv_android = (TextView)view.findViewById(R.id.tv_android);
            youTubePlayerView = (YouTubePlayerView)view.findViewById(R.id.youtube_player_view);
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer initializedYouTubePlayer) {
                    youTubePlayer = initializedYouTubePlayer;
                    youTubePlayer.cueVideo(currentVideoId, 0);
                }
            });

            view.setOnClickListener(this);
        }

        void cueVideo(String videoId) {
            currentVideoId = videoId;

            if(youTubePlayer == null)
                return;

            youTubePlayer.cueVideo(videoId, 0);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

}
