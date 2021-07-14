package com.lezardrieux.back.front.model;

import java.util.List;

public class PagePlace {

    List<Place> items;
    int indexPage;
    int itemPerPage;
    boolean firstPage;
    boolean lastPage;
    int totalPage;
    private int[] type;

    public PagePlace(List<Place> items, int indexPage, int itemPerPage, boolean firstPage, boolean lastPage, int totalPage, int[] type) {
        this.items = items;
        this.indexPage = indexPage;
        this.itemPerPage = itemPerPage;
        this.firstPage = firstPage;
        this.lastPage = lastPage;
        this.totalPage = totalPage;
        this.type = type;
    }

    public List<Place> getItems() {
        return items;
    }

    public int getIndexPage() {
        return indexPage;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int[] getType() {
        return type;
    }
}
