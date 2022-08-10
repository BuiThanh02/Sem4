package buihuuthanh.buihuuthanh_csw.repository;

import buihuuthanh.buihuuthanh_csw.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
