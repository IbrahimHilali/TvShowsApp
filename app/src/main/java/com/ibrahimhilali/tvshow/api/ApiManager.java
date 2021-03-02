package com.ibrahimhilali.tvshow.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.ibrahimhilali.tvshow.helpers.HTTPHelpers;
import com.ibrahimhilali.tvshow.helpers.UrlBuilder;
import com.ibrahimhilali.tvshow.models.Show;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ApiManager {
    private static final String TAG = "ApiManager";

    private Context context;
    private HTTPHelpers helper;
    private UrlBuilder urlBuilder;

    public interface Status {
        void SearchedShowsLoaded(ArrayList<Show> shows, ArrayList<Integer> keys, ArrayList<Double> scores);

        void ShowsLoaded(ArrayList<Show> shows, ArrayList<Integer> keys);
    }

    public ApiManager(Context context) {
        this.context = context;
        this.urlBuilder = new UrlBuilder(Paths.TV_SHOW_API_MAIN);
        this.helper = new HTTPHelpers(context);
    }

    public void searchShowByName(String name, final Status status) {
        String url = this.urlBuilder
                .addPath(Paths.SEARCH)
                .addPath(Paths.SHOWS)
                .addParameters("q", name)
                .build();
        this.helper.get(url, new HTTPHelpers.DataStatus() {
            @Override
            public void DataNotFound(VolleyError error) {
                Log.e(TAG, error.getMessage());
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void DataLoaded(JSONArray response) {

                ArrayList<Show> shows = new ArrayList<>();
                ArrayList<Integer> keys = new ArrayList<>();
                ArrayList<Double> scores = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    Show show = null;
                    try {
                        scores.add(response.getJSONObject(i).getDouble("score"));
                        JSONObject json = response.getJSONObject(i).getJSONObject("show");

                        show = new Gson().fromJson(json.toString(), Show.class);
                    } catch (JSONException e) {
                        Log.v(TAG, e.getMessage());
                        e.printStackTrace();
                    }
                    keys.add(i);
                    shows.add(show);
                }
                status.SearchedShowsLoaded(shows, keys, scores);
            }
        });

    }

    public void getAllShows(final Status status) {
        String url = this.urlBuilder
                .addPath(Paths.SHOWS)
                .build();
        this.helper.get(url, new HTTPHelpers.DataStatus() {
            @Override
            public void DataNotFound(VolleyError error) {
                Log.e(TAG, error.getMessage() + "Data not Found");
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void DataLoaded(JSONArray response) {

                ArrayList<Show> shows = new ArrayList<>();
                ArrayList<Integer> keys = new ArrayList<>();
                for (int i = 0; i < response.length() || i < 50; i++) {
                    Show show = null;
                    try {
                        JSONObject json = response.getJSONObject(i);
                        show = new Gson().fromJson(json.toString(), Show.class);
                    } catch (JSONException e) {
                        Log.v(TAG, e.getMessage());
                        e.printStackTrace();
                    }catch (Exception e){
                        Log.e(TAG, e.getMessage());
                        e.printStackTrace();
                    }
                    keys.add(i);
                    shows.add(show);
                }
                status.ShowsLoaded(shows, keys);
            }
        });
    }
}

