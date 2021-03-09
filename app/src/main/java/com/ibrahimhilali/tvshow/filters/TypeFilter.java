package com.ibrahimhilali.tvshow.filters;


import java.util.List;
import java.util.stream.Collectors;

public class TypeFilter<T> extends BasicFilter<T> implements Filter {
    private List<T> filtered;

    public TypeFilter(boolean active) {
        super(active);
    }

    @Override
    public String define() {
        return "Type";
    }

    @Override
    public void apply() {
        this.filtered = this.getItems()
                .stream()
                .filter(this.getPredicate())
                .collect(Collectors.toList());
    }

    @Override
    public boolean active() {
        return super.active;
    }

    @Override
    public List<T> result() {
        return this.filtered;
    }
}
