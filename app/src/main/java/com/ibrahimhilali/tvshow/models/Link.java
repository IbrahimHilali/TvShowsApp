package com.ibrahimhilali.tvshow.models;

public class Link {
    protected String self;
    protected String previousepisode;

    Link() {

    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getPreviousepisode() {
        return previousepisode;
    }

    public void setPreviousepisode(String previousepisode) {
        this.previousepisode = previousepisode;
    }
}
