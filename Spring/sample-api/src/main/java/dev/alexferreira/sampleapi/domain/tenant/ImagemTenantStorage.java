package dev.alexferreira.sampleapi.domain.tenant;

public interface ImagemTenantStorage {

	String save(Tenant tenant, byte[] image);

	byte[] get(Tenant tenant);

}
