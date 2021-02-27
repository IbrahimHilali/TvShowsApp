package com.ibrahimhilali.tvshow;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.ibrahimhilali.tvshow.helpers.Helper;
import com.ibrahimhilali.tvshow.helpers.RecyclerViewConfig;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Helper helper;
    RecyclerView showsRecyclerView;
    SearchView searchView;
    Helper.DataStatus status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new Helper(getApplicationContext());
        showsRecyclerView = findViewById(R.id.ShowsRecyclerView);
        status = FillRecyclerViewWithTVShows();
        helper.getAllShows(status);
        searchView = findViewById(R.id.SearchView);
        searchView.setOnQueryTextListener(processQuery());
    }

    private Helper.DataStatus FillRecyclerViewWithTVShows() {
        return new Helper.DataStatus() {
            @Override
            public void DataIsLoaded() {

            }

            @Override
            public void DataNotFound(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void DataIsFound(ArrayList<JSONObject> shows, ArrayList<Integer> keys) {
                new RecyclerViewConfig().setConfig(showsRecyclerView, getApplicationContext(), shows, keys);
            }
        };
    }


    private SearchView.OnQueryTextListener processQuery() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                helper.searchByName(query, status);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                helper.searchByName(newText, status);
                return true;
            }
        };
    }

}