package com.scode.domain;

import com.scode.domain.model.ProductModel;
import com.scode.domain.model.UserModel;

import java.util.List;

public interface ProductDomain {
    public List<ProductModel> getAll();
}
