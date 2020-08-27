package com.scode.service.product;

import com.scode.domain.ProductDomain;
import com.scode.domain.mapper.ProductEntityModelMapper;
import com.scode.domain.model.ProductModel;
import com.scode.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements ProductDomain {

    private final ProductRepository productRepository;
    private final ProductEntityModelMapper productEntityModelMapper;

    @Override
    public List<ProductModel> getAll() {
        log.info("Record fetched!");
        return productEntityModelMapper.mapAll(productRepository.findAll());
    }

    @Override
    public ProductModel get(Long productId) {
        return null;
    }

}
