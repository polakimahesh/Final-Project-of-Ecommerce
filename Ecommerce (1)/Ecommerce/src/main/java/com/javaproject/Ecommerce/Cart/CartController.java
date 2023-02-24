package com.javaproject.Ecommerce.Cart;

import com.javaproject.Ecommerce.Customer.Customer;
import com.javaproject.Ecommerce.DTO.CartDto;
import com.javaproject.Ecommerce.DTO.CartItemDto;
import com.javaproject.Ecommerce.DTO.CartUpdateItemDto;
import com.javaproject.Ecommerce.DTO.GetCartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemRepository cartItemRepository;

    //get all the carts
    @GetMapping("")
    public ResponseEntity<List<Cart>> getAllCarts(){
        return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
    }
    //create cart
    @PostMapping("/create-cart")
    public ResponseEntity<Object> createCart(@RequestBody CartDto cartDto){

        var cart = cartService.createCart(cartDto);
        if(Boolean.TRUE.equals(cart.get("isSuccess"))){
            return ResponseEntity.ok(cart.get("message"));
        }else
            return ResponseEntity.badRequest().body(cart.get("message"));
    }
    //create cart items
    @PostMapping("/create-cart-items")
    public ResponseEntity<Object> createCartItem(@RequestBody CartItemDto cartItemDto){
        return  new ResponseEntity<>(cartService.createCartItems(cartItemDto),HttpStatus.CREATED);
    }
    @PostMapping ("/get-all-cart-items")
    public ResponseEntity<Object> getAllCartItems(@RequestBody GetCartDto getCartDto){
       var cartItem =cartService.getAllCartItemsWithID(getCartDto);
        if(Boolean.TRUE.equals(cartItem.get("isSuccess"))){
            return ResponseEntity.ok(cartItem.get("message"));
        }else
            return ResponseEntity.badRequest().body(cartItem.get("message"));
    }
    @PostMapping("/update-cart-items")
    public ResponseEntity<Object> updateCartItems(@RequestBody CartUpdateItemDto cartUpdateItemDto){
        var cartItem=cartService.updateCartItems(cartUpdateItemDto);
        if(Boolean.TRUE.equals(cartItem.get("isSuccess"))){
            return ResponseEntity.ok(cartItem.get("message"));
        }else
            return ResponseEntity.badRequest().body(cartItem.get("message"));
    }

}
