package com.t2010a.productapi.api;

import com.t2010a.productapi.entity.Product;
import com.t2010a.productapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(path = "api/v1/products")
@RestController
@CrossOrigin("*")
public class ProductApi {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET,path = {"id"})
    public ResponseEntity<?> findById(@PathVariable String proId){
        Optional<Product> optionalProduct = productService.findById(proId);
        if (!optionalProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalProduct.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestBody Product product){
        if (!productService.findById(product.getProId()).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.save(product));
    }

    @RequestMapping(method = RequestMethod.DELETE,path = {"id"})
    public ResponseEntity<?> deleteById(@PathVariable String proId){
        if (!productService.findById(proId).isPresent()){
            ResponseEntity.badRequest().build();
        }
        productService.deleteById(proId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"id"})
    public ResponseEntity<Product> update(@PathVariable String proId, @RequestBody Product updateProduct){
        Optional<Product> optionalProduct = productService.findById(proId);
        if (!optionalProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Product existProduct = optionalProduct.get();

        existProduct.setCreatedAt(updateProduct.getCreatedAt());
        existProduct.setDeletedAt(updateProduct.getDeletedAt());
        existProduct.setUpdatedAt(updateProduct.getUpdatedAt());
        existProduct.setMakerDetail(updateProduct.getMakerDetail());
        existProduct.setProDescribe(updateProduct.getProDescribe());
        existProduct.setProDetail(updateProduct.getProDetail());
        existProduct.setProName(updateProduct.getProName());
        existProduct.setProPrice(updateProduct.getProPrice());
        existProduct.setProQty(updateProduct.getProQty());
        existProduct.setStatus(updateProduct.getStatus());
        existProduct.setUserUpdateDetail(updateProduct.getUserUpdateDetail());
        existProduct.setUserCreateDetail(updateProduct.getUserCreateDetail());
        existProduct.setUserDeleteDetail(updateProduct.getUserDeleteDetail());

        return ResponseEntity.ok(productService.save(existProduct));
    }
}
