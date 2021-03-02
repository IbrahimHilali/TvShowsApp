package com.ibrahimhilali.tvshow.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Show {
    protected Integer id;
    protected String url;
    protected String name;
    protected String type;
    protected String language;
    protected List<String> genres;
    protected String status;
    protected Integer runtime;
    protected Date premiered;
    protected String officialSite;
    protected Schedule schedule;
    protected Rating rating;
    protected Integer weight;
    protected Network network;
    protected WebChannel webChannel;
    protected Map<String, String> externals;
    protected Map<String, String> image;
    protected String summary;
    protected Long updated;
    protected Link link;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Date getPremiered() {
        return premiered;
    }

    public void setPremiered(Date premiered) {
        this.premiered = premiered;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public WebChannel getWebChannel() {
        return webChannel;
    }

    public void setWebChannel(WebChannel webChannel) {
        this.webChannel = webChannel;
    }

    public Map<String, String> getExternals() {
        return externals;
    }

    public void setExternals(Map<String, String> externals) {
        this.externals = externals;
    }

    public Map<String, String> getImage() {
        return image;
    }

    public void setImage(Map<String, String> image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
