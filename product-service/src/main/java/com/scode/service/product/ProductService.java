package com.scode.service.product;

import com.scode.domain.ProductDomain;
import com.scode.domain.mapper.ProductEntityModelMapper;
import com.scode.domain.model.ProductModel;
import com.scode.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductDomain {

    private final ProductRepository productRepository;
    private final ProductEntityModelMapper productEntityModelMapper;

    @Override
    public List<ProductModel> getAll() {
        return productEntityModelMapper.mapAll(productRepository.findAll());
    }

}
