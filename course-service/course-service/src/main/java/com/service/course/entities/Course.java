package com.service.course.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="courses")
//lombok data auto generate the getter setter and constructor , tostring
@Data
public class Course {

    @Id
    private String id;
    private String title;
    private String shortDesc;
    private String lDesc;
    private double price;
    private boolean live = false;
    private double discount;
    private Date createDate;
    //image field
    private String bannerName;
    private String bannerContentType;

    private String categoryId;

}