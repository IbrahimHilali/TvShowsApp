package com.ibrahimhilali.tvshow;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ibrahimhilali.tvshow.RecyclerViews.RecyclerViewConfig;
import com.ibrahimhilali.tvshow.api.ApiManager;
import com.ibrahimhilali.tvshow.api.Paginating;
import com.ibrahimhilali.tvshow.models.Show;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ApiManager manager;
    Paginating<Show> paginator;
    RecyclerView showsRecyclerView;
    SearchView searchView;
    ApiManager.Status status;
    RecyclerViewConfig.Events events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupApiManager();
        searchView.setOnQueryTextListener(processQuery());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.SearchView).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    protected void setupApiManager() {
        manager = new ApiManager(getApplicationContext());
        paginator = new Paginating(showsRecyclerView, getApplicationContext(), this.setRecycleViewItemLister());
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

    protected RecyclerViewConfig.Events setRecycleViewItemLister() {
        return new RecyclerViewConfig.Events() {
            @Override
            public void onItemClick(Integer id) {
                Toast.makeText(getApplicationContext(), "Click " + id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLeftSwap() {
                paginator.next();
                Toast.makeText(getApplicationContext(), "Page " + paginator.getCurrent(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightSwap() {
                paginator.previous();
                Toast.makeText(getApplicationContext(), "Page " + paginator.getCurrent(), Toast.LENGTH_SHORT).show();
            }

        };
    }

    private SearchView.OnQueryTextListener processQuery() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                manager.searchShowByName(query, status);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        };
    }


}