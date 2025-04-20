package com.order.service.services;

import com.order.service.entities.OrderDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    public OrderDetails createOrder()
    {
        OrderDetails orderDetails=new OrderDetails();
        orderDetails.setOrderId(UUID.randomUUID().toString());
        orderDetails.setEmail("shri@gmail.com");
        orderDetails.setPhone("9756786777");
        orderDetails.setUserId("980");
        orderDetails.setCourseId(UUID.randomUUID().toString());
        //save to db

        return orderDetails;
    }

}
