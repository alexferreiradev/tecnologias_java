package dev.alexferreira.sampleapi.domain.authorization;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document
public class Authorization {

	@Id
	public String id;

	@Field
	public TenantAuthorized tenantAuthorized;
	@Field
	public String doorType;
	@Field
	public String doorDescription;

	@Field
	public Instant createdAt;
}
