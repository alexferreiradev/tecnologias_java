package dev.alexferreira.sampleapi.modules.product.repository;

import dev.alexferreira.sampleapi.modules.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class CustomProdutoRepositoryImpl implements CustomProductRepository {

	private final EntityManager entityManager;

	@Autowired
	public CustomProdutoRepositoryImpl(EntityManager em) {
		entityManager = em;
	}

	@Override
	public Product getFirstProduct() {
		TypedQuery<Product> customQuery = entityManager.createQuery("select p from Product p", Product.class);

		return customQuery.getResultStream().findFirst().orElse(null);
	}
}
