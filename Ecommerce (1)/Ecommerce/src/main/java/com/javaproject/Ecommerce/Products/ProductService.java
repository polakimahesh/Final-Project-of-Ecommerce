package com.javaproject.Ecommerce.Products;


import com.javaproject.Ecommerce.DTO.ProductDto;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
     private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product createProduct(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());
        product.setTotalPrice((product.getPrice()* product.getQuantity()));
        productRepository.save(product);
        return  product;
    }
    public Product getSingleProduct(int id){
        return productRepository.findById(id).orElse(null);
    }

    public HashMap<String,Object> updateProduct(ProductDto productDto){
        HashMap<String,Object> response = new HashMap<>();
        var product = productRepository.findById(productDto.getProductId()).orElse(null);
        if(product==null){
            HashMap<String,Object> response1= new HashMap<>();
            response1.put("message","incorrect customer id "+productDto.getProductId()+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else{
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setQuantity(product.getQuantity());
            product.setDescription(productDto.getDescription());
            response.put("isSuccess",true);
            response.put("message",product);
            productRepository.save(product);
            return  response;
        }
    }
    public HashMap<String,Object> deleteProduct(ProductDeleteId productDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        var product= productRepository.findById(productDto.getProductId()).orElse(null);
        if(product==null){
            response1.put("message","incorrect product id "+productDto.getProductId()+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }
        response1.put("message","product deleted successfully! "+productDto.getProductId());
        productRepository.deleteById(productDto.getProductId());
        response.put("isSuccess",true);
        response.put("message",response1);
        return  response;
    }

}
