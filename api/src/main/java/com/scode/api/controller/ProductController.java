package com.scode.api.controller;

import com.scode.api.dto.response.ProductResponse;
import com.scode.api.dto.response.mapper.ProductModelResponseMapper;
import com.scode.domain.ProductDomain;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Api(tags = "Product Resources")
public class ProductController {

    private final ProductDomain productDomain;
    private final ProductModelResponseMapper productModelResponseMapper;

    @GetMapping("/v1")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAll(
            @RequestHeader(required = false, name = "Authorization") String auth) {
        System.out.println(auth);
        return productModelResponseMapper.mapAllToResponse(productDomain.getAll());
    }


//    @GetMapping("/{productId}")
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "", authorizations = { @Authorization(value="JWT Token") })
//    public ProductResponse getUser(
//            @PathVariable("productId") Long productId) {
//        return productModelResponseMapper.mapToResponse(productDomain.get(productId));
//    }
}
