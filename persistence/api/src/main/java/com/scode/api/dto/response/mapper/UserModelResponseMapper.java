package com.scode.api.dto.response.mapper;

import com.scode.api.dto.response.UserResponse;
import com.scode.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserModelResponseMapper {
    public List<UserResponse> mapAllToResponse(List<UserModel> userModel);
    public UserResponse mapToResponse(UserModel userModel);
}

