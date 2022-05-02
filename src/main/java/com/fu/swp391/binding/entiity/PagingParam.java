package com.fu.swp391.binding.entiity;

import java.util.ArrayList;
import java.util.List;

public class PagingParam {
    int totalPage;
    int totalElement;
    int perPage;
    int pageIndex;
    List<Integer> pageList = new ArrayList<>();
    int totalElementInCurrentPage;

    public int getTotalElementInCurrentPage() {
        return totalElementInCurrentPage;
    }

    public void setTotalElementInCurrentPage(int totalElementInCurrentPage) {
        this.totalElementInCurrentPage = totalElementInCurrentPage;
    }

    public PagingParam(int totalPage, int totalElement, int perPage, int pageIndex) {
        this.totalPage = totalPage;
        this.totalElement = totalElement;
        this.perPage = perPage;
        this.pageIndex = pageIndex;
        for (int i=1; i <= totalPage;++i){
            System.out.println(i);
            this.pageList.add(i);
        }
    }

    public PagingParam(int perPage) {
        this.perPage = perPage;
    }

    public List<Integer> getPageList() {
        return pageList;
    }

    public void setPageList(List<Integer> pageList) {
        this.pageList = pageList;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
}
