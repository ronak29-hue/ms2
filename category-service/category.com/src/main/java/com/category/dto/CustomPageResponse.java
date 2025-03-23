package com.category.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomPageResponse <T> {

    private int pageNumber;
    private int pageSize;
    private int totalElement;
    private boolean last;
    private int totalPages;

    private List<T> content;
    /* private List<CategoryDto> content;*/
}