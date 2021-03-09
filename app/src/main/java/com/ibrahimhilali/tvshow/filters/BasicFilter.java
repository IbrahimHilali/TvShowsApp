package com.ibrahimhilali.tvshow.filters;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BasicFilter<T> {
    protected ArrayList<T> filtered;
    protected boolean active = false;
    protected List<T> items;
    private Predicate<T> predicate;

    BasicFilter(boolean active) {
        this.active = active;
    }

    BasicFilter() {
    }

    public ArrayList<T> getFiltered() {
        return filtered;
    }

    public void setFiltered(ArrayList<T> filtered) {
        this.filtered = filtered;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Predicate<T> getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getClass().toString();
    }
}
