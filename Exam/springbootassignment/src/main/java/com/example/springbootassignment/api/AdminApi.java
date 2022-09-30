package com.example.springbootassignment.api;

import com.example.springbootassignment.entity.Product;
import com.example.springbootassignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminApi {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, path = "products")
    public Page<Product> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "limit", defaultValue = "10") int limit){
        return productService.findAllByActive(page, limit);
    }
    @RequestMapping(method = RequestMethod.GET, path = "products/{id}")
    public ResponseEntity<Product> findById(@PathVariable String id){
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalProduct.get());
    }
    @RequestMapping(method = RequestMethod.DELETE,path = "products/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        if (!productService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "products/{id}")
    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody Product updateProduct){
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Product existProduct = optionalProduct.get();

        existProduct.setCreateAt(updateProduct.getCreateAt());
        existProduct.setUpdateAt(LocalDateTime.now());
        existProduct.setName(updateProduct.getName());
        existProduct.setPrice(updateProduct.getPrice());
        existProduct.setDescription(updateProduct.getDescription());
        existProduct.setDetail(updateProduct.getDetail());
        existProduct.setStatus(updateProduct.getStatus());
        existProduct.setThumbnails(updateProduct.getThumbnails());
        existProduct.setCategory(updateProduct.getCategory());

        return ResponseEntity.ok(productService.save(existProduct));
    }
}
