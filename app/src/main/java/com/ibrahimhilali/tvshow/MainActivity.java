package com.ibrahimhilali.tvshow;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ibrahimhilali.tvshow.api.ApiManager;
import com.ibrahimhilali.tvshow.RecyclerViews.RecyclerViewConfig;
import com.ibrahimhilali.tvshow.api.Paginating;
import com.ibrahimhilali.tvshow.models.Show;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ApiManager manager;
    Paginating<Show> paginator;
    RecyclerView showsRecyclerView;
    SearchView searchView;
    ApiManager.Status status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupApiManager();
        searchView.setOnQueryTextListener(processQuery());
    }

    protected void setupApiManager() {
        manager = new ApiManager(getApplicationContext());
        paginator = new Paginating<>(showsRecyclerView, getApplicationContext());
        status = actionAccordingStatus();
        manager.getAllShows(status);
    }

    protected void findViews() {
        showsRecyclerView = findViewById(R.id.ShowsRecyclerView);
        searchView = findViewById(R.id.SearchView);
    }

    private ApiManager.Status actionAccordingStatus() {
        return new ApiManager.Status() {
            @Override
            public void SearchedShowsLoaded(ArrayList<Show> shows, ArrayList<Integer> keys, ArrayList<Double> scores) {
                paginator.setItems(shows);
            }

            @Override
            public void ShowsLoaded(ArrayList<Show> shows, ArrayList<Integer> keys) {
                paginator.setItems(shows);
            }

        };
    }

    private SearchView.OnQueryTextListener processQuery() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                manager.searchShowByName(query, status);
                finish();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
    }

}