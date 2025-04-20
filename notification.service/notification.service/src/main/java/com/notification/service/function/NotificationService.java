package com.notification.service.function;

import com.notification.service.dto.OrderInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class NotificationService {

    //with the help of spring cloud function

    @Bean  //we can fire localhost:9902/testing it will give the o/p
    public Supplier<String> testing()
    {
        return ()->"this is testing";
    }

    @Bean
    public Function<String,String> sayHello()
    {
        return (message)-> "hello!!!!!!!"+message;
    }

    @Bean
    public Function<OrderInfo,String> orderNotification()
    {
        //logic to send notofication
        return orderInfo -> {
            //send notofication
            sendNotification(orderInfo);
            System.out.println(orderInfo.getOrderId());
            System.out.println(orderInfo.getEmailId());
            System.out.println(orderInfo.getUserPhone());
            return orderInfo.getUserPhone();
        };
    }

    private void sendNotification(OrderInfo orderInfo)
    {

    }

}
