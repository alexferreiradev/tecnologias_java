package dev.alexferreira.sampleapi.modules.product.repository;

import dev.alexferreira.sampleapi.modules.product.model.Product;

public interface ProductRepository {

	Product getProduct();

	void saveProduct(Product product);
}
