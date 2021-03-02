package com.ibrahimhilali.tvshow.models;

import java.util.Map;

public class Network {
    protected Long id;
    protected String name;
    protected Map<String, String> country;

    Network() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getCountry() {
        return country;
    }

    public void setCountry(Map<String, String> country) {
        this.country = country;
    }
}
