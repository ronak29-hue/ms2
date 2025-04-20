package com.category.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="categories")
@Data
public class Category {

    @Id
    private String id;
    private String title;

    @Column(name = "description")
    private String desc;
    private Date date;

    @Column(name = "banner_url")
    private String bannerImageUrl;

}
