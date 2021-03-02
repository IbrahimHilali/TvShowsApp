package com.ibrahimhilali.tvshow.RecyclerViews;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ibrahimhilali.tvshow.R;
import com.ibrahimhilali.tvshow.models.Show;

import java.util.List;


public class RecyclerViewConfig {
    private Context context;
    private ShowAdepter showAdepter;


    public void setConfig(RecyclerView recyclerView, Context context, List<Show> shows, List<Integer> keys) {
        this.context = context;
        this.showAdepter = new ShowAdepter(shows, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        recyclerView.setAdapter(this.showAdepter);

    }


    class ShowViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mContent;

        public Integer key;

        public ShowViewHolder(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(context).inflate(R.layout.show_view_item, parent, false));
            mTitle = itemView.findViewById(R.id.show_view_item_title);
            mContent = itemView.findViewById(R.id.show_view_item_summary);

        }

        public void bind(Show show, int key) {
            mTitle.setText(show.getName());
            mContent.setText(Html.fromHtml(show.getSummary(), Html.FROM_HTML_MODE_COMPACT).toString());
            this.key = key;
        }

    }

    class ShowAdepter extends RecyclerView.Adapter<ShowViewHolder> {
        private List<Show> shows;
        private List<Integer> keys;

        ShowAdepter(List<Show> shows, List<Integer> keys) {
            this.keys = keys;
            this.shows = shows;
        }

        @NonNull
        @Override
        public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ShowViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ShowViewHolder holder, int position) {
            holder.bind(shows.get(position), keys.get(position));
        }
        
        @Override
        public int getItemCount() {
            return shows.size();
        }
    }


}
