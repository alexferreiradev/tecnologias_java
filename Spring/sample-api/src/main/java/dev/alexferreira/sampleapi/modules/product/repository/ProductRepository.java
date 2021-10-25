package dev.alexferreira.sampleapi.modules.product.repository;

import dev.alexferreira.sampleapi.modules.product.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
