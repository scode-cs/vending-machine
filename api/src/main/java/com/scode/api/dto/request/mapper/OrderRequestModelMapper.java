package com.scode.api.dto.request.mapper;

import com.scode.api.dto.request.OrderRequest;
import com.scode.domain.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderRequestModelMapper {
    public OrderModel map(OrderRequest orderRequest);
}
