package test.test.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.test.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}