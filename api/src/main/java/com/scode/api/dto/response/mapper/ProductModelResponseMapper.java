package com.scode.api.dto.response.mapper;

import com.scode.api.dto.response.ProductResponse;
import com.scode.domain.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductModelResponseMapper {
    public List<ProductResponse> mapAllToResponse(List<ProductModel> userModel);
    public ProductModel mapToResponse(ProductModel userModel);
}

