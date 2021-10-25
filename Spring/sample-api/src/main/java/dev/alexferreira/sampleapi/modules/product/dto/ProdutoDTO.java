package dev.alexferreira.sampleapi.modules.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoDTO {

	public Long id;
	public String name;

	@Override
	public String toString() {
		return "ProdutoDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
