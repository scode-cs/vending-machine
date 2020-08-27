package com.scode.api.dto.request.mapper;


import com.scode.api.dto.RefillRequest;
import com.scode.domain.model.RefillModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RefillRequestModelMapper {
    public RefillModel map(RefillRequest refillRequest);
}
