package dev.alexferreira.sampleapi.modules.product.service;

import dev.alexferreira.sampleapi.modules.product.dto.ProdutoDTO;
import dev.alexferreira.sampleapi.modules.product.dto.mapper.ProductMapper;
import dev.alexferreira.sampleapi.modules.product.model.Product;
import dev.alexferreira.sampleapi.modules.product.model.ProdutoLog;
import dev.alexferreira.sampleapi.modules.product.repository.CustomProductRepository;
import dev.alexferreira.sampleapi.modules.product.repository.ProductRepository;
import dev.alexferreira.sampleapi.modules.product.repository.ProdutoLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;
	private final CustomProductRepository customProductRepository;
	private final ProductMapper mapper;
	private final ProdutoLogRepository logRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository repository, CustomProductRepository customProductRepository, ProductMapper mapper, ProdutoLogRepository logRepository) {
		this.repository = repository;
		this.customProductRepository = customProductRepository;
		this.mapper = mapper;
		this.logRepository = logRepository;
	}

	@Override
	public ProdutoDTO getProduto() {
		return mapper.toDTO(customProductRepository.getFirstProduct());
	}

	@Transactional
	@Override
	public void saveProduct(ProdutoDTO dto) {
		validateOrThrow(dto);
		Product product = mapper.toEntity(dto);
		repository.save(product);

		logRepository.insert(ProdutoLog.createFrom(this, String.format("salvo produto: %s", product.getName())));
	}

	@Override
	public List<ProdutoLog> getAllLogs() {
		return logRepository.findAll(Sort.by(Sort.Direction.DESC, "data"));
	}

	private void validateOrThrow(ProdutoDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("Dto null");
		}
		if (Optional.ofNullable(dto.name).map(String::isEmpty).orElse(false)) {
			throw new IllegalArgumentException("Name não pode ser nulo ou vazia");
		}
	}
}
