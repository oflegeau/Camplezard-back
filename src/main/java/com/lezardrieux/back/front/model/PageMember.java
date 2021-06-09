package com.lezardrieux.back.front.model;

import java.util.List;

public class PageMember {

    List<MemberCard> items;
    int indexPage;
    int itemPerPage;
    boolean empty;
    boolean firstPage;
    boolean lastPage;
    boolean sort;
    long totalItem;
    int totalPage;

    public PageMember() {
    }

    public PageMember(List<MemberCard> items, int indexPage, int itemPerPage, boolean empty, boolean firstPage, boolean lastPage, boolean sort, long totalItem, int totalPage) {
        this.items = items;
        this.indexPage = indexPage;
        this.itemPerPage = itemPerPage;
        this.empty = empty;
        this.firstPage = firstPage;
        this.lastPage = lastPage;
        this.sort = sort;
        this.totalItem = totalItem;
        this.totalPage = totalPage;
    }

    public List<MemberCard> getItems() {
        return items;
    }

    public int getIndexPage() {
        return indexPage;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public boolean isSort() {
        return sort;
    }

    public long getTotalItem() {
        return totalItem;
    }

    public int getTotalPage() {
        return totalPage;
    }
}
