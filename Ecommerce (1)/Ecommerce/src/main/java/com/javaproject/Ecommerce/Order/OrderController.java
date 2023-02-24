package com.javaproject.Ecommerce.Order;

import com.javaproject.Ecommerce.DTO.GetOrderDto;
import com.javaproject.Ecommerce.DTO.OrderDto;
import com.javaproject.Ecommerce.DTO.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderservice;
    //get all the orders
    @GetMapping("")
    public ResponseEntity<List<Orders>> getAllOrders(){
        return  new ResponseEntity<>(orderservice.getAllOrders(), HttpStatus.OK);
    }
    //create the orders
    @PostMapping("")
    public ResponseEntity<Orders> createOrder(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderservice.createOrder(orderDto),HttpStatus.CREATED);
    }
    @PostMapping("/create-order-items")
    public ResponseEntity<Object> createOrderItems(@RequestBody OrderItemDto orderItemDto){
        return  new ResponseEntity<>(orderservice.createOrderItems(orderItemDto),HttpStatus.CREATED);
    }
    //get all the order items with customer id
    @PostMapping("/get-order-items")
    public ResponseEntity<Object> getAllOrderItemsWithCustomerId(@RequestBody GetOrderDto getOrderDto){
        var orderItem=orderservice.getAllOrderItemsWithCustomerId(getOrderDto);
        if(Boolean.TRUE.equals(orderItem.get("isSuccess"))){
            return ResponseEntity.ok(orderItem.get("message"));
        }else
            return ResponseEntity.badRequest().body(orderItem.get("message"));
    }


}
