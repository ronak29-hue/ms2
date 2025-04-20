package com.notification.service.dto;

import lombok.Data;

@Data
public class OrderDetails {

    private String orderId;
    private String email;
    private String userId;
    private String phone;
    private boolean paymentStatus=false;
    private boolean orderStatus=false;
    private String courseId;
}
