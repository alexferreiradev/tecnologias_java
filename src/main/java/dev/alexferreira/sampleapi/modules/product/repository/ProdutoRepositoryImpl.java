package dev.alexferreira.sampleapi.modules.product.repository;

import dev.alexferreira.sampleapi.modules.product.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoRepositoryImpl implements ProductRepository {

	private final List<Product> products = new ArrayList<>();

	@Override
	public Product getProduct() {
		Product product = new Product();
		product.setId(1L);
		product.setName("Teste de api");

		return product;
	}

	@Override
	public void saveProduct(Product product) {
		products.add(product);
	}
}
