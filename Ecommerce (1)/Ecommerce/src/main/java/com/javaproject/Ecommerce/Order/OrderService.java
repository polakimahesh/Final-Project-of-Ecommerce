package com.javaproject.Ecommerce.Order;

import com.javaproject.Ecommerce.Cart.Cart;
import com.javaproject.Ecommerce.Cart.CartRepository;
import com.javaproject.Ecommerce.Customer.Customer;
import com.javaproject.Ecommerce.Customer.CustomerRepository;
import com.javaproject.Ecommerce.DTO.GetOrderDto;
import com.javaproject.Ecommerce.DTO.OrderDto;
import com.javaproject.Ecommerce.DTO.OrderItemDto;
import com.javaproject.Ecommerce.DTO.OrderItemResponseDto;
import com.javaproject.Ecommerce.Products.Product;
import com.javaproject.Ecommerce.Products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Orders> getAllOrders(){
        return  orderRepository.findAll();
    }

    public Orders createOrder(OrderDto orderDto){
        Customer customer = customerRepository.findById(orderDto.getCustomerId()).orElse(null);
        Orders orders = new Orders();
        orders.setAddress(orderDto.getAddress());
        orders.setCreatedAt(orders.getCreatedAt());
        orders.setCustomer(customer);
        orderRepository.save(orders);
        return orders;
    }

    public OrderItem createOrderItems(OrderItemDto orderItemDto){
        Orders orders=orderRepository.findById(orderItemDto.getOrderId()).orElse(null);
        Product product=productRepository.findById(orderItemDto.getProductId()).orElse(null);
        OrderItem orderItem =new OrderItem();
        orderItem.setOrders(orders);
        orderItem.setItemName(product.getName());
        orderItem.setItemPrice(product.getPrice());
        orderItem.setItemQuantity(product.getQuantity());
        orderItem.setDescription(product.getDescription());
        orderItem.setTotalPrice(product.getTotalPrice());
        orderItemRepository.save(orderItem);
        return orderItem;
    }

    public HashMap<String,Object> getAllOrderItemsWithCustomerId(GetOrderDto getOrderDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        Orders orders =orderRepository.findById(getOrderDto.getOrderId()).orElse(null);
        if(orders==null){
            response1.put("message","order not found with id "+getOrderDto.getOrderId());
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else {
            List<OrderItem> orderItems=orderItemRepository.findByOrders_Customer_Id(orders.getId());
            List<OrderItemResponseDto> orderItemRList =new ArrayList<>();
            double grandTotal=0.0;
            for(OrderItem orderItem:orderItems){
                OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
                orderItemResponseDto.setOrderItemId(orderItem.getId());
                orderItemResponseDto.setItemName(orderItem.getItemName());
                orderItemResponseDto.setItemPrice(orderItem.getItemPrice());
                orderItemResponseDto.setItemQuantity(orderItem.getItemQuantity());
                orderItemResponseDto.setDescription(orderItem.getDescription());
                orderItemResponseDto.setTotalPrice(orderItem.getTotalPrice());
                orderItemRList.add(orderItemResponseDto);
                grandTotal+=orderItemResponseDto.getTotalPrice();
            }
            getOrderDto.setOrderId(orders.getId());
            getOrderDto.setOrderItems(orderItemRList);
            getOrderDto.setGrantTotal(grandTotal);
            response.put("isSuccess",true);
            response.put("message",getOrderDto);
            return response;
        }
    }

}
