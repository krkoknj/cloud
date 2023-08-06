package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.RequestOrder;
import com.example.orderservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {

    private final OrderService orderService;
    private final Environment env;

    @GetMapping("/health_check")
    public String status() {

        return String.format("It`s Working in Order Service on PORT %s",
                env.getProperty("local.server.port"));
    }

    @PostMapping("{userId}/orders")
    public ResponseEntity<ResponseOrder> createUser(@PathVariable String userId,
                                                    @RequestBody RequestOrder order) {
        OrderDto orderDto = order.toOrderDto();
        orderDto.setUserId(userId);
        OrderDto createdOrder = orderService.createOrder(orderDto);
        ResponseOrder responseOrder = createdOrder.toResponseOrder();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> createUser(@PathVariable String userId) {
        Iterable<OrderEntity> ordersByUserId = orderService.getOrdersByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();

        ordersByUserId.forEach(r -> result.add(r.toResponseOrder()));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
