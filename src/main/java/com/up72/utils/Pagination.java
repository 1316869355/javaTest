package com.up72.utils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/2.
 */
public class Pagination implements Serializable {
    private static final int DEFAULT_SLIDERS_COUNT = 7;
    private static final long serialVersionUID = 2683375341262436695L;
    private int limit;
    private int pageNumber;
    private int totalCount;

    public Pagination() {
    }

    public Pagination(int pageNumber, int limit, int totalCount) {
        this.limit = limit;
        this.totalCount = totalCount;
        this.pageNumber = this.computePageNo(pageNumber);
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public int getLimit() {
        return this.limit;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public boolean isFirstPage() {
        return this.pageNumber <= 1;
    }

    public boolean isLastPage() {
        return this.pageNumber >= this.getTotalPages();
    }

    public int getPrePage() {
        return this.isHasPrePage()?this.pageNumber - 1:this.pageNumber;
    }

    public int getNextPage() {
        return this.isHasNextPage()?this.pageNumber + 1:this.pageNumber;
    }

    public boolean isDisabledPage(int pageNumber) {
        return pageNumber < 1 || pageNumber > this.getTotalPages() || pageNumber == this.pageNumber;
    }

    public boolean isHasPrePage() {
        return this.pageNumber - 1 >= 1;
    }

    public boolean isHasNextPage() {
        return this.pageNumber + 1 <= this.getTotalPages();
    }

    public int getStartRow() {
        return this.getLimit() > 0 && this.totalCount > 0?(this.pageNumber > 0?(this.pageNumber - 1) * this.getLimit() + 1:0):0;
    }

    public int getEndRow() {
        return this.pageNumber > 0?Math.min(this.limit * this.pageNumber, this.getTotalCount()):0;
    }

    public int getOffset() {
        return this.pageNumber > 0?(this.pageNumber - 1) * this.getLimit():0;
    }

    public int getTotalPages() {
        if(this.totalCount <= 0) {
            return 0;
        } else if(this.limit <= 0) {
            return 0;
        } else {
            int count = this.totalCount / this.limit;
            if(this.totalCount % this.limit > 0) {
                ++count;
            }

            return count;
        }
    }

    protected int computePageNo(int pageNumber) {
        return computePageNumber(pageNumber, this.limit, this.totalCount);
    }

    public Integer[] getSlider() {
        return this.slider(7);
    }

    public Integer[] slider(int slidersCount) {
        return generateLinkPageNumbers(this.getPageNumber(), this.getTotalPages(), slidersCount);
    }

    private static int computeLastPage(int totalItems, int pageSize) {
        if(pageSize <= 0) {
            return 1;
        } else {
            int result = totalItems % pageSize == 0?totalItems / pageSize:totalItems / pageSize + 1;
            if(result <= 1) {
                result = 1;
            }

            return result;
        }
    }

    private static int computePageNumber(int pageNumber, int pageSize, int totalItems) {
        return pageNumber <= 1?1:(2147483647 != pageNumber && pageNumber <= computeLastPage(totalItems, pageSize)?pageNumber:computeLastPage(totalItems, pageSize));
    }

    private static Integer[] generateLinkPageNumbers(int currentPageNumber, int lastPageNumber, int count) {
        int avg = count / 2;
        int startPageNumber = currentPageNumber - avg;
        if(startPageNumber <= 0) {
            startPageNumber = 1;
        }

        int endPageNumber = startPageNumber + count - 1;
        if(endPageNumber > lastPageNumber) {
            endPageNumber = lastPageNumber;
        }

        if(endPageNumber - startPageNumber < count) {
            startPageNumber = endPageNumber - count;
            if(startPageNumber <= 0) {
                startPageNumber = 1;
            }
        }

        ArrayList result = new ArrayList();

        for(int i = startPageNumber; i <= endPageNumber; ++i) {
            result.add(new Integer(i));
        }

        return (Integer[])result.toArray(new Integer[result.size()]);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pagination");
        sb.append("{pageNumber=").append(this.pageNumber);
        sb.append(", limit=").append(this.limit);
        sb.append(", totalCount=").append(this.totalCount);
        sb.append('}');
        return sb.toString();
    }
}
