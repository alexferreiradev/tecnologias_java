package dev.alexferreira.sampleapi.modules.product.repository;

import dev.alexferreira.sampleapi.modules.product.model.ProdutoLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoLogRepository extends MongoRepository<ProdutoLog, String> {

}
