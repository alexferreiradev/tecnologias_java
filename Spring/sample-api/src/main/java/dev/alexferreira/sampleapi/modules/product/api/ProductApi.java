package dev.alexferreira.sampleapi.modules.product.api;

import dev.alexferreira.sampleapi.modules.product.dto.*;
import dev.alexferreira.sampleapi.modules.product.model.*;
import dev.alexferreira.sampleapi.modules.product.service.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/products", consumes = {MediaType.APPLICATION_JSON_VALUE})
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

	@GetMapping("/logs")
	public List<ProdutoLog> getLogs() {
		logger.debug("getAllLogs was requested");

		return service.getAllLogs();
	}
}
