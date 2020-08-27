package com.scode.api.dto.response.mapper;

import com.scode.api.dto.response.GenericProductResponse;
import com.scode.domain.model.GenericProductResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenericProductModelResponseMapper {
    public GenericProductResponse map(GenericProductResponseModel genericProductResponseModel);
}
