package com.ibrahimhilali.tvshow.filters;

import java.util.List;

public class RateFilter<T> extends BasicFilter<T> implements Filter {

    public RateFilter(boolean active) {
        this.active = active;
    }

    @Override
    public String define() {
        return null;
    }

    @Override
    public void apply() {

    }

    @Override
    public boolean active() {
        return false;
    }

    @Override
    public List<T> result() {
        return null;
    }
}
