package dev.alexferreira.sampleapi.infrastructure.repository.mongo;

import dev.alexferreira.sampleapi.domain.authorization.Authorization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRepository extends MongoRepository<Authorization, String> {

}
