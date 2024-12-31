package io.reactivestax.aop;

import io.reactivestax.order.domain.OrderDetails;
import io.reactivestax.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@ComponentScan("io.reactivestax")
public class MainAOP {

    private static final Logger log = LogManager.getLogger(MainAOP.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainAOP.class);
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aop-spring-beans.xml");
        OrderService service = context.getBean(OrderService.class);

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderType("Cash");
        orderDetails.setOrderAmount(200.0D);
        service.createOrder(orderDetails);

    }
}
