package dev.alexferreira.sampleapi.modules.product.api;

import dev.alexferreira.sampleapi.modules.product.dto.ProdutoDTO;
import dev.alexferreira.sampleapi.modules.product.service.ProductService;
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

	private ProductService service;

	@Autowired
	public ProductApi(ProductService service) {
		this.service = service;
	}

	@GetMapping
	public ProdutoDTO getProduct() {
		return service.getProduto();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void registerProduct(@RequestBody ProdutoDTO dto) {
		service.saveProduct(dto);
	}
}
