package com.example.ead.service;

import com.example.ead.entity.Product;
import com.example.ead.entity.Sale;
import com.example.ead.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;


    public Iterable<Sale> findAll(){
        return saleRepository.findAll();
    }

    public Optional<Sale> findById(String id){
        return saleRepository.findById(id);
    }
}
