package buihuuthanh.buihuuthanh_csw.service;

import buihuuthanh.buihuuthanh_csw.entity.Product;
import buihuuthanh.buihuuthanh_csw.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(String id){
        return productRepository.findById(id);
    }
    public Product save(Product product){
        return productRepository.save(product);
    }
}
