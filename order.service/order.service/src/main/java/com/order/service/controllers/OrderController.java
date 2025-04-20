package com.order.service.controllers;

import com.order.service.entities.OrderDetails;
import com.order.service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder()
    {
        OrderDetails order=this.orderService.createOrder();
        //send notification to notification service so that notification sernd the email and msg to user
        OrderCreatedNotification(order);
        return ResponseEntity.ok("order created");
    }

    @Autowired
    private StreamBridge streamBridge;

    private  void OrderCreatedNotification(OrderDetails orderDetails)
    {
        //logic to send notification to NS
        //add two dependencies- cloud stream and rabbit dependency

        boolean send=streamBridge.send("orderCreatedEvent-out-0",orderDetails);

        if(send)
        {
            System.out.println("success Event");
        }
        else
        {
            System.out.println("Fail Event");
        }

    }
}
