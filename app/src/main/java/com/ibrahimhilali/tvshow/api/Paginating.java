package com.ibrahimhilali.tvshow.api;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.ibrahimhilali.tvshow.RecyclerViews.RecyclerViewConfig;
import com.ibrahimhilali.tvshow.helpers.UrlBuilder;
import com.ibrahimhilali.tvshow.models.Show;

import java.util.ArrayList;
import java.util.List;

public class Paginating<T> {
    protected ApiManager manager;
    protected Context context;
    protected Integer count;
    protected Integer current;
    protected Integer countPerPage;
    protected ArrayList<T> items;
    protected RecyclerView recyclerView;
    protected ArrayList<T> itemsPerPage;
    protected RecyclerViewConfig config;
    protected ArrayList<Integer> keys;

    public Paginating(RecyclerView recyclerView, Context context) {
        this.current = 1;
        this.countPerPage = 10;
        this.context = context;
        this.itemsPerPage = new ArrayList<>();
        this.keys = new ArrayList<>();
        this.recyclerView = recyclerView;
        this.config = new RecyclerViewConfig();
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
        this.count = this.items.size();
        loadPage();
    }

    public void next() {
        this.current = (this.current + 1) % this.count;
        loadPage();
    }

    public void previous() {
        this.current = (this.current - 1) % this.count;
        loadPage();
    }

    protected void loadPage() {
        this.itemsPerPage = new ArrayList<>();
        this.keys = new ArrayList<>();
        int start = (int) (this.current -1)* this.countPerPage;
        for (int i = start; i < this.countPerPage || i < this.items.size(); i++) {
            this.itemsPerPage.add(this.items.get(i));
            this.keys.add(i);
        }
        this.config.setConfig(this.recyclerView, context, (List<Show>) this.itemsPerPage, this.keys);

    }
}
