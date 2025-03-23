package com.service.vedio.vedio_service.dto;



import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private String id;
    private String title;
    private String shortDesc;
    private String lDesc;
    private double price;
    private boolean live=false;
    private double discount;
    private Date createDate;
    private String bannerName;

   private String categoryId;


        // when we click on the link and we can directly open the open no need to pass the api url everytime
    public String getBannerUrl()
    {
        return "http://localhost:9097/api/v1/courses/"+id+"/banners";
    }
}
