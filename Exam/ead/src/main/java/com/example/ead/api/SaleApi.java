package com.example.ead.api;

import com.example.ead.entity.Product;
import com.example.ead.entity.Sale;
import com.example.ead.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(path = "api/v1/sales")
@RestController
@CrossOrigin("*")
public class SaleApi {
    @Autowired
    private SaleService saleService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Sale>> findAll(){
        return ResponseEntity.ok(saleService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<Sale> findById(@PathVariable String id){
        Optional<Sale> optionalSale = saleService.findById(id);
        if (!optionalSale.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalSale.get());
    }
}
