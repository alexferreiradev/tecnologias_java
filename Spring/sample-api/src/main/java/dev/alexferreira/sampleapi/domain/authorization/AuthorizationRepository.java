package dev.alexferreira.sampleapi.domain.authorization;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorizationRepository extends MongoRepository<Authorization, String> {

}
