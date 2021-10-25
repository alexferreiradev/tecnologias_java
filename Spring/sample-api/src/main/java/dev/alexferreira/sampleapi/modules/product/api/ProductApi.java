package dev.alexferreira.sampleapi.modules.product.api;

import dev.alexferreira.sampleapi.modules.product.dto.ProdutoDTO;
import dev.alexferreira.sampleapi.modules.product.service.ProductService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductApi {

	private final ProductService service;
	private final Logger logger;

	@Autowired
	public ProductApi(ProductService service, Logger logger) {
		this.service = service;
		this.logger = logger;
	}

	@GetMapping
	public ProdutoDTO getProduct() {
		logger.debug("GetProduct was requested");

		return service.getProduto();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void registerProduct(@RequestBody ProdutoDTO dto) {
		logger.debug("registerProduct was requested with {}", dto);

		service.saveProduct(dto);
	}
}
