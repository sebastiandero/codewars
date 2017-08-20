package com.sebastiandero.kata;

import java.util.List;

// TODO: complete this object/class

public class PaginationHelper<I> {

    private List<I> items;
    private int     itemsPerPage;

    /**
     * The constructor takes in an array of items and a integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {
        this.items = collection;
        this.itemsPerPage = itemsPerPage > 0 ? itemsPerPage : 20;
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return items.size();
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        return (int) Math.ceil((float) items.size() / itemsPerPage);
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        if (pageIndex >= pageCount()) {
            return -1;
        }
        if (pageIndex == pageCount() - 1) {
            return items.size() % itemsPerPage;
        }
        return itemsPerPage;
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        int res = (int) Math.floor((float) itemIndex / itemsPerPage);
        return res >= 0 && pageCount() >= res && itemIndex < itemCount() ? res : -1;
    }
}