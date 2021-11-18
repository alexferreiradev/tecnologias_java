package dev.alexferreira.sampleapi.modules.product.service;

import dev.alexferreira.sampleapi.modules.product.dto.ProdutoDTO;
import dev.alexferreira.sampleapi.modules.product.model.ProdutoLog;

import java.util.List;

public interface ProductService {

	ProdutoDTO getProduto();

	void saveProduct(ProdutoDTO dto);

	List<ProdutoLog> getAllLogs();
}
