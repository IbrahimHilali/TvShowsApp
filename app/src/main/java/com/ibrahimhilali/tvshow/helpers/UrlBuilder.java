package com.ibrahimhilali.tvshow.helpers;

import java.util.ArrayList;

public class UrlBuilder {
    final static String PARAMETER_SEPARATOR = "&";
    final static String URL_PARAMETER_SEPARATOR = "?";
    final static String PARAMETER_VALUE_SEPARATOR = "=";
    final static String PATH_SEPARATOR = "/";
    private String main;
    private String url;
    private ArrayList<String> parameters;
    private ArrayList<String> paths;

    public UrlBuilder() {
        parameters = new ArrayList<>();
        paths = new ArrayList<>();
        this.main = "";
        this.url = "";
    }

    public UrlBuilder(String api) {
        parameters = new ArrayList<>();
        paths = new ArrayList<>();
        setApi(api);
        this.url = api;
    }

    public void clear() {
        parameters = new ArrayList<>();
        paths = new ArrayList<>();
    }

    public void setApi(String api) {
        this.main = api;
    }

    public UrlBuilder addPath(String path) {
        paths.add(path);
        return this;
    }

    public UrlBuilder addParameters(String parameter, String value) {
        String param = parameter + PARAMETER_VALUE_SEPARATOR + value;
        parameters.add(param);
        return this;
    }


    public String build() {
        String inLineParameters = "";
        for (String path : paths) {
            this.url = String.format("%s%s%s", this.url, PATH_SEPARATOR, path);
        }
        if (!parameters.isEmpty()) {
            this.url += URL_PARAMETER_SEPARATOR;
            this.url = this.url + parameters.get(0);
            for (int i = 1; i < parameters.size(); i++) {
                this.url = String.format("%s%s%s", this.url, PARAMETER_SEPARATOR, parameters.get(i));
            }
        }
        return this.url;
    }


}
