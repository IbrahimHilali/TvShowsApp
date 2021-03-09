package com.ibrahimhilali.tvshow.filters;

public interface Filter {
    String define();

    void apply();

    boolean active();

    Object result();
}
