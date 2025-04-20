package com.notification.service.function;

import com.notification.service.dto.OrderDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class OrderRelatedNotificationFunctions {

    @Bean
    public Function<OrderDetails,String> orderEventReceiver()
    {
        return (orderDetails -> {
            //process
            System.out.println("Sending notification to user");
            logicToSendEmailAndMessageToUser(orderDetails.getEmail(),orderDetails.getPhone());
            return "order notification send to user";//this will use as send the details or acknowledgement in out binding to the other exchange [order service]
        });
    }

    private void logicToSendEmailAndMessageToUser(String email, String phone)
    {
        System.out.println("Sending email to "+email);
        System.out.println("Sending SMS to "+phone);
        System.out.println("Successfully Send");
        System.out.println("--------------------------------------");
    }
}
