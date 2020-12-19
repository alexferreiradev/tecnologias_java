package dev.alexferreira.produto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProdutoService {

	@Inject
	EntityManager entityManager;

	public Produto getProduto(Integer id) {
		Query query = entityManager.createQuery("select p from Produto p");
		List resultList = query.getResultList();
		if (resultList.isEmpty()) return null;
		return (Produto) resultList.get(0);
	}

	@Transactional
	public Produto saveProduto() {
		Produto produto = new Produto();
		produto.setDescricao("Teste de desc");
		entityManager.persist(produto);
		return produto;
	}
}
