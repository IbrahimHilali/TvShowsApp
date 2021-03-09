package com.ibrahimhilali.tvshow.filters;

import java.util.ArrayList;

public class NameFilter<T> extends BasicFilter<T> implements Filter {
    protected ArrayList<T> filtered;
    protected boolean active = false;

    public NameFilter(boolean active) {
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
        return this.active;
    }

    @Override
    public Object result() {
        return null;
    }
}
