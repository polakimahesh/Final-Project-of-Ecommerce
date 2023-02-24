package com.javaproject.Ecommerce.Products;

import com.javaproject.Ecommerce.DTO.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    //get all the products
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
    //create the product
    @PostMapping("/register-product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        return  new ResponseEntity<>(productService.createProduct(productDto),HttpStatus.CREATED);
    }
    //get the product with specified id
    @GetMapping("/{id}")
    public  ResponseEntity<Product> getSingleProduct(@PathVariable int id){
        return  new ResponseEntity<>(productService.getSingleProduct(id),HttpStatus.OK);
    }
    //update the product with id by using post operation
    @PostMapping("/update-product")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductDto productDto){
        var product =productService.updateProduct(productDto);
        if(Boolean.TRUE.equals(product.get("isSuccess"))){
            return ResponseEntity.ok(product.get("message"));
        }else
            return ResponseEntity.badRequest().body(product.get("message"));
    }
    //delete the product with id by using post operation
    @PostMapping("/delete-product")
    public ResponseEntity<Object> deleteProduct(@RequestBody ProductDeleteId productDeleteId){
        var product =productService.deleteProduct(productDeleteId);
        if(Boolean.TRUE.equals(product.get("isSuccess"))){
            return ResponseEntity.ok(product.get("message"));
        }else
            return ResponseEntity.badRequest().body(product.get("message"));
    }
}
