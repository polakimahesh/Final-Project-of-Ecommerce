package com.javaproject.Ecommerce.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>  {
    Cart findByCustomer_Id(int id);


}
