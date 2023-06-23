package dev.alexferreira.sampleapi.modules.product.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoDTO {

	public Long id;
	public String name;

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		ProdutoDTO that = (ProdutoDTO) o;
		return Objects.equals(id, that.id) && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "ProdutoDTO{" + "id=" + id + ", name='" + name + '\'' + '}';
	}
}
