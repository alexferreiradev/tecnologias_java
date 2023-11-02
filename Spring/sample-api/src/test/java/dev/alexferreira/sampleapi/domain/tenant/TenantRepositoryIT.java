package dev.alexferreira.sampleapi.domain.tenant;

import dev.alexferreira.sampleapi.common.fixture.DomainFixtures;
import dev.alexferreira.sampleapi.common.test.BaseRepositoryIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

class TenantRepositoryIT extends BaseRepositoryIT {

	@Autowired
	TenantRepository repository;

	@Test
	void shouldSaveInquilino() {
		Tenant tenantSaved = repository.save(DomainFixtures.createTenant());

		Tenant tenant = repository.findById(tenantSaved.getId()).get();
		assertNotNull(tenant);
	}

	@Test
	void shouldThrow_whenSaveInquilinoWithSameDocument() {
		Tenant tenantSaved = repository.saveAndFlush(DomainFixtures.createTenant());
		Tenant tenantNew = DomainFixtures.createTenant();
		tenantNew.setDocument(tenantSaved.getDocument());

		DataIntegrityViolationException violationException =
				assertThrows(DataIntegrityViolationException.class, () -> repository.saveAndFlush(tenantNew));

		String expectedExceptionMsg = String.format("Key (document)=(%s) already exists", tenantNew.getDocument());
		assertTrue(violationException.getMostSpecificCause().getMessage().contains(expectedExceptionMsg));

		Tenant tenant = repository.findById(tenantSaved.getId()).get();
		assertNotNull(tenant);
	}
}
