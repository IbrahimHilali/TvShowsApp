package com.ibrahimhilali.tvshow.RecyclerViews;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
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
    private RecyclerView.OnItemTouchListener listener;

    public void setConfig(RecyclerView recyclerView, Context context, List<Show> shows, List<Integer> keys, final Events events) {
        this.context = context;
        this.showAdepter = new ShowAdepter(shows, keys, events);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        recyclerView.setAdapter(this.showAdepter);

    }

    public interface Events {
        void onItemClick(Integer id);

        void onLeftSwap();

        void onRightSwap();
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
        static final int MIN_DISTANCE = 50;

        private List<Show> shows;
        private List<Integer> keys;
        private Events events;
        private float x1, x2;

        ShowAdepter(List<Show> shows, List<Integer> keys, final Events events) {
            this.keys = keys;
            this.shows = shows;
            this.events = events;
        }

        @NonNull
        @Override
        public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ShowViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ShowViewHolder holder, int position) {
            holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getActionMasked()) {
                        case MotionEvent.ACTION_MOVE: {
                            x2 = event.getX();
                            if (Math.abs(x2 - x1) >= MIN_DISTANCE && x2 > x1) {
                                events.onRightSwap();
                            }
                            if (Math.abs(x1 - x2) >= MIN_DISTANCE && x2 < x1) {
                                events.onLeftSwap();
                            }
                            return false;
                        }
                        case MotionEvent.ACTION_UP: {
                            x2 = event.getX();
                            if (Math.abs(x2 - x1) >= MIN_DISTANCE && x2 > x1) {
                                events.onRightSwap();
                            }
                            if (Math.abs(x1 - x2) >= MIN_DISTANCE && x2 < x1) {
                                events.onLeftSwap();
                            }
                            if (Math.abs(x1 - x2) < MIN_DISTANCE) {
                                events.onItemClick(position);
                            }
                            return false;
                        }

                        case MotionEvent.ACTION_DOWN: {
                            x1 = event.getX();
                            return true;
                        }

                    }
                    return false;
                }
            });
            holder.bind(shows.get(position), keys.get(position));
        }

        @Override
        public int getItemCount() {
            return shows.size();
        }


    }


}
