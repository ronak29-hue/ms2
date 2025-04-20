package com.notification.service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderInfo {

    private String orderId;
    private Date date;
    private String price;
    private String emailId;
    private String userPhone;

}
