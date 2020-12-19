package dev.alexferreira.core;

import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerProducer {

	Logger logger = LoggerFactory.getLogger(EntityManagerProducer.class);

	@Produces
	public EntityManager getEntityManager() {
		logger.info("Iniciando a criacao da factory de persistencia");
		EntityManagerFactoryImpl entityManagerFactory = (EntityManagerFactoryImpl) Persistence.createEntityManagerFactory("jpa-example");
		logger.debug("Dados de config: {}", entityManagerFactory.getSessionFactory().getAllClassMetadata());
		return entityManagerFactory.createEntityManager();
	}
}
