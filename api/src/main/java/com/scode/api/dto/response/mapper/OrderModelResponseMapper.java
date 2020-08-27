package com.scode.api.dto.response.mapper;

import com.scode.api.dto.request.OrderRequest;
import com.scode.api.dto.response.OrderResponse;
import com.scode.domain.model.OrderModel;
import com.scode.domain.model.OrderReponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderModelResponseMapper {
    public OrderResponse map(OrderReponseModel orderReponseModel);
}
