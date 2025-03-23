package com.service.course.dto;

import java.util.List;

public class CustomPageResponse <T>{

    private int pageNumber;
    private int pageSize;
    private int totalElement;
    private boolean last;
    private int totalPages;

    private List<T> content;
   /* private List<CategoryDto> content;*/

    public CustomPageResponse() {
    }

    public CustomPageResponse(List<T> content, int totalPages, boolean last, int totalElement, int pageSize, int pageNumber) {
        this.content = content;
        this.totalPages = totalPages;
        this.last = last;
        this.totalElement = totalElement;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}

