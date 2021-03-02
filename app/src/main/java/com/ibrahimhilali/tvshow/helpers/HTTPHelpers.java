package com.ibrahimhilali.tvshow.helpers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class HTTPHelpers {
    private Context context;
    public static final String TAG = "RequestHelper";

    public HTTPHelpers(Context context) {
        this.context = context;
    }

    public interface DataStatus {
        public void DataNotFound(VolleyError error);

        public void DataLoaded(JSONArray response);
    }

    public void get(String url, final DataStatus dataStatus) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {


                    @Override
                    public void onResponse(JSONArray response) {
                        dataStatus.DataLoaded(response);
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
