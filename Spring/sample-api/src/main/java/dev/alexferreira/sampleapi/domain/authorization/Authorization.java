package dev.alexferreira.sampleapi.domain.authorization;

import dev.alexferreira.sampleapi.domain.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Objects;

@Document
public class Authorization {

	@Id
	public String id;

	@Field
	public User userAuthorized;
	@Field
	public String doorType;
	@Field
	public String doorDescription;

	@Field
	public Instant createdAt;

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Authorization that = (Authorization) o;
		return Objects.equals(id, that.id) && Objects.equals(userAuthorized, that.userAuthorized) && Objects.equals(
			doorType,
			that.doorType
		) && Objects.equals(doorDescription, that.doorDescription) && Objects.equals(createdAt, that.createdAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userAuthorized, doorType, doorDescription, createdAt);
	}

	@Override
	public String toString() {
		return "Authorization{" + "id='" + id + '\'' + ", userAuthorized=" + userAuthorized + ", doorType='" + doorType
			+ '\'' + ", doorDescription='" + doorDescription + '\'' + ", createdAt=" + createdAt + '}';
	}
}
