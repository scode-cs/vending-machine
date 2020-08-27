package com.scode.service.product;

import com.scode.domain.ProductDomain;
import com.scode.domain.mapper.ProductEntityModelMapper;
import com.scode.domain.model.ProductModel;
import com.scode.persistence.ProductPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements ProductDomain {

    private final ProductEntityModelMapper productEntityModelMapper;
    private final ProductPersistenceService productPersistenceService;

    @Override
    public List<ProductModel> getAll() {
        log.info("Record fetched!");
        return productEntityModelMapper.mapAll(productPersistenceService.findAll());
    }

    @Override
    public ProductModel get(Long productId) {
        return null;
    }

}
