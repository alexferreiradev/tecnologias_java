package dev.alexferreira.sampleapi.domain.tenant.exception;

import dev.alexferreira.sampleapi.common.exception.DomainException;

public class TenantAlreadyExistsException extends DomainException {

	public TenantAlreadyExistsException() {
		super(TenantExceptionCode.DUPLICATED);
	}
}
