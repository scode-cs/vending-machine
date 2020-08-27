package com.scode.domain.mapper;

import com.scode.domain.model.ProductModel;
import com.scode.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductEntityModelMapper {
    public List<ProductModel> mapAll(List<ProductEntity> productEntities);
    public ProductModel map(ProductEntity productEntitie);
}
