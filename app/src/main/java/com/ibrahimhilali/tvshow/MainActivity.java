package com.ibrahimhilali.tvshow;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ibrahimhilali.tvshow.api.ApiManager;
import com.ibrahimhilali.tvshow.helpers.RecyclerViewConfig;
import com.ibrahimhilali.tvshow.models.Show;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ApiManager manager;
    RecyclerView showsRecyclerView;
    SearchView searchView;
    ApiManager.Status status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new ApiManager(getApplicationContext());
        showsRecyclerView = findViewById(R.id.ShowsRecyclerView);
        status = actionAccordingStatus();
        manager.getAllShows(status);
        searchView = findViewById(R.id.SearchView);
        searchView.setOnQueryTextListener(processQuery());
    }

    private ApiManager.Status actionAccordingStatus() {
        return new ApiManager.Status() {
            @Override
            public void SearchedShowsLoaded(ArrayList<Show> shows, ArrayList<Integer> keys, ArrayList<Double> scores) {
                new RecyclerViewConfig().setConfig(showsRecyclerView, getApplicationContext(), shows, keys);

            }

            @Override
            public void ShowsLoaded(ArrayList<Show> shows, ArrayList<Integer> keys) {
                // new RecyclerViewConfig().setConfig(showsRecyclerView, getApplicationContext(), shows, keys);
            }

        };
    }


    private SearchView.OnQueryTextListener processQuery() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                manager.searchShowByName(query, status);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                manager.searchShowByName(newText, status);
                return true;
            }
        };
    }

}