package com.javaproject.Ecommerce.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{
    List<OrderItem> findByOrders_Customer_Id(int id);

}
