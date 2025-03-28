package com.category.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class CategoryDto {

    private String id;
    @NotEmpty(message = "title is required!!")
    @Size(min=3,max=50, message = "title must be 3-50 range")
    private String title;

    private String desc;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a", timezone = "IST")
    private Date date;

    private String bannerImageUrl;
}
