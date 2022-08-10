package buihuuthanh.buihuuthanh_csw.api;

import buihuuthanh.buihuuthanh_csw.entity.Product;
import buihuuthanh.buihuuthanh_csw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping(path = "api/v1/products")
@RestController
@CrossOrigin("*")
public class ProductApi {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        if (!productService.findById(product.getId()).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.save(product));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Product> sellProduct(@PathVariable String id, @RequestBody Product updateProduct){
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Product existProduct = optionalProduct.get();

        existProduct.setName(updateProduct.getName());
        existProduct.setPrice(updateProduct.getPrice());
        existProduct.setQuantity(updateProduct.getQuantity());

        return ResponseEntity.ok(productService.save(existProduct));
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<Product> findById(@PathVariable String id){
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalProduct.get());
    }
}
