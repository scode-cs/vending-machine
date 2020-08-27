package com.scode.api.dto.request.mapper;

import com.scode.api.dto.request.OrderRequest;
import com.scode.domain.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderRequestModelMapper {
    public ProductModel mapToResponse(OrderRequest userModel);
}
