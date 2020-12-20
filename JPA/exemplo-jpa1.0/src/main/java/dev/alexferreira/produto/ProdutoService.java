package dev.alexferreira.produto;

import dev.alexferreira.produto.model.Item;
import dev.alexferreira.produto.model.Produto;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManagerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@RequestScoped
public class ProdutoService {

	@Inject
	EntityManager entityManager;

	/**
	 * @deprecated Por que eu quero
	 *
	 * @param id
	 * @return
	 *
	 */
	@Deprecated
	public Produto getProduto(Integer id) {
//		entityManager.clear();
//		Query query = entityManager.createQuery("select p from Produto p join fetch p.itemList where p.id = :id");
//		Query query = entityManager.createQuery("select p from Produto p where p.id = :id");
//		query.setParameter("id", id);
//		Produto produto = (Produto) query.getSingleResult();
//		Produto produto = entityManager.find(Produto.class, id);
//		Session session = entityManager.unwrap(Session.class);
//		Criteria criteria = session.createCriteria(Produto.class);
//		List list = criteria.list();
		Produto produto = entityManager.getReference(Produto.class, id);
		return produto;
//		if (resultList.isEmpty()) return null;
//		return (Produto) resultList.get(0);
	}

	public Produto saveProduto() {
		entityManager.getTransaction().begin();
		Item item = new Item();
		Produto produto = new Produto();
		item.setDescricao("Teste de desc2");
		produto.setDescricao("Teste de desc2");
//		entityManager.persist(produto);
//		entityManager.flush();
//		produto.setItemList(Collections.singletonList(item));
		item.setProduto(produto);
		entityManager.persist(item);
//		entityManager.refresh(produto);
		entityManager.getTransaction().commit();

		return produto;
	}

	public List<Item> getItemPorProduto(Integer id) {
		Query query = entityManager.createQuery("select i from Item i where i.produto.id = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public Produto getOtherSession(Integer id) {
//		Query query = entityManager.createQuery("select p from Produto p join fetch p.itemList where p.id = :id");
//		Query query = entityManager.createQuery("select p from Produto p where p.id = :id");
//		query.setParameter("id", id);
//		Object produto = query.getSingleResult();
		Produto produto = entityManager.getReference(Produto.class, id);
//		Produto produto = entityManager.find(Produto.class, id);
		return (Produto) produto;
	}
}
