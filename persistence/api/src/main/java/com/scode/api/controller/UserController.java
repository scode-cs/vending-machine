package com.scode.api.controller;

import com.scode.api.dto.response.UserResponse;
import com.scode.api.dto.response.mapper.UserModelResponseMapper;
import com.scode.domain.ProductDomain;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Api(tags = "User Resources")
public class UserController {

    private final ProductDomain productDomain;
    private final UserModelResponseMapper userModelResponseMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAll(
            @RequestHeader(required = false, name = "Authorization") String auth) {
        System.out.println(auth);
        return userModelResponseMapper.mapAllToResponse(productDomain.getAll());
    }

//    @GetMapping("/{userId}")
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "", authorizations = { @Authorization(value="JWT Token") })
//    public UserResponse getUser(
//            @PathVariable("userId") Integer userId) {
//        return userModelResponseMapper.mapToResponse(productDomain.getUser(userId));
//    }
}
