package dev.alexferreira.sampleapi.domain.tenant.exception;

import dev.alexferreira.sampleapi.common.exception.DomainExceptionCode;

public enum TenantExceptionCode implements DomainExceptionCode {

	DUPLICATED("TEN001", "Tenant already registered"),
	;

	private final String code;
	private final String message;

	TenantExceptionCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public String message() {
		return message;
	}
}
