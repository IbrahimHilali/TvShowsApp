package com.ibrahimhilali.tvshow.api;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.ibrahimhilali.tvshow.RecyclerViews.RecyclerViewConfig;
import com.ibrahimhilali.tvshow.filters.CountryFilter;
import com.ibrahimhilali.tvshow.filters.DeltaFilters;
import com.ibrahimhilali.tvshow.filters.Filter;
import com.ibrahimhilali.tvshow.filters.RateFilter;
import com.ibrahimhilali.tvshow.filters.TypeFilter;
import com.ibrahimhilali.tvshow.models.Show;

import java.util.ArrayList;
import java.util.List;

import static com.ibrahimhilali.tvshow.RecyclerViews.RecyclerViewConfig.Events;

public class Paginating<T> {
    protected Context context;
    protected Integer count;
    protected Integer current;
    protected Integer countPerPage;
    protected ArrayList<T> items;
    protected RecyclerView recyclerView;
    protected ArrayList<T> itemsPerPage;
    protected RecyclerViewConfig config;
    protected ArrayList<Integer> keys;
    protected Events events;
    protected DeltaFilters filters;

    public Paginating(RecyclerView recyclerView, Context context, final Events events) {
        this.current = 1;
        this.countPerPage = 10;
        this.context = context;
        this.itemsPerPage = new ArrayList<>();
        this.keys = new ArrayList<>();
        this.recyclerView = recyclerView;
        this.config = new RecyclerViewConfig();
        this.events = events;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
        this.filters = setupFilters();
        this.count = (int) Math.ceil((double) this.items.size() / this.countPerPage);
        this.current = 1;
        loadPage();
    }

    /**
     * Delta Filter and  Filters
     */
    protected DeltaFilters setupFilters() {
        return new DeltaFilters() {
            @Override
            public List<Filter> filters() {
                return List.of(
                        new TypeFilter<Show>(true),
                        new CountryFilter<Show>(true),
                        new RateFilter<Show>(true)
                );
            }
        };
    }

    public void next() {
        this.current = (this.current + 1) % this.count;
        loadPage();
    }

    public void previous() {
        this.current = this.current - 1 % this.count;
        if (this.current <= 0) {
            this.current = this.count;
        }
        loadPage();
    }

    protected void loadPage() {
        this.itemsPerPage = new ArrayList<>();
        this.keys = new ArrayList<>();
        int start = (int) (this.current - 1) * this.countPerPage;
        for (int i = start; i <= start + this.countPerPage && i < this.items.size(); i++) {
            this.itemsPerPage.add(this.items.get(i));
            this.keys.add(i);
        }
        this.config.setConfig(this.recyclerView, context, (List<Show>) this.itemsPerPage, this.keys, this.events);

    }

    public Integer getCurrent() {
        return current;
    }
}
