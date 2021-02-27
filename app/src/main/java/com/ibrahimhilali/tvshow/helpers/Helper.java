package com.ibrahimhilali.tvshow.helpers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ibrahimhilali.tvshow.APIPaths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Helper {
    private RequestQueue queue;
    private Context context;
    public static final String TAG = "RequestHelper";

    public Helper(Context context) {
        this.context = context;

    }

    public interface DataStatus {
        public void DataIsLoaded();

        public void DataNotFound(VolleyError error);

        public void DataIsFound(ArrayList<JSONObject> object, ArrayList<Integer> keys);
    }

    public void searchByName(String tvShowName, final DataStatus dataStatus) {
        String url = APIPaths.TV_SHOW_API_MAIN + APIPaths.TV_SHOW_API_SEARCH + tvShowName;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {


                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<JSONObject> shows = new ArrayList<>();
                        ArrayList<Integer> keys = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i).getJSONObject("show");
                                shows.add(jsonObject);
                                keys.add(jsonObject.getInt("id"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        dataStatus.DataIsFound(shows, keys);
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dataStatus.DataNotFound(error);
                    }

                });
        RequestsQueue.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }

    public void getAllShows(final DataStatus dataStatus) {
        String url = APIPaths.TV_SHOW_API_MAIN + APIPaths.GET_SHOWS;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<JSONObject> shows = new ArrayList<>();
                        ArrayList<Integer> keys = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                shows.add(jsonObject);
                                keys.add(jsonObject.getInt("id"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        dataStatus.DataIsFound(shows, keys);
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dataStatus.DataNotFound(error);
                    }

                });
        RequestsQueue.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }
}
