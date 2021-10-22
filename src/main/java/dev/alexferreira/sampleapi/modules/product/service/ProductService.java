package dev.alexferreira.sampleapi.modules.product.service;

import dev.alexferreira.sampleapi.modules.product.dto.ProdutoDTO;

public interface ProductService {

	ProdutoDTO getProduto();

	void saveProduct(ProdutoDTO dto);
}
