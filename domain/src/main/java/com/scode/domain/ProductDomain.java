package com.scode.domain;

import com.scode.domain.model.ProductModel;

import java.util.List;

public interface ProductDomain {
    public List<ProductModel> getAll();
    public ProductModel get(Long productId);
}
