package com.ibrahimhilali.tvshow.filters;

import java.util.List;

public abstract class DeltaFilters {
    private List<Filter> filters;
    private List<Object> items;

    public DeltaFilters() {
        this.items = items;
    }

    public abstract List<Filter> filters();

    public void setFilters(List<Object> items) {
        this.items = items;
    }
}
