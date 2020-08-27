package com.scode.api.controller;

import com.scode.api.dto.request.OrderRequest;
import com.scode.api.dto.request.mapper.OrderRequestModelMapper;
import com.scode.api.dto.response.GenericProductResponse;
import com.scode.api.dto.response.OrderResponse;
import com.scode.api.dto.response.mapper.GenericProductModelResponseMapper;
import com.scode.api.dto.response.mapper.OrderModelResponseMapper;
import com.scode.domain.OrderDomain;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Api(tags = "Order Resources")
public class OrderController {

    private final OrderRequestModelMapper orderRequestModelMapper;
    private final GenericProductModelResponseMapper genericProductModelResponseMapper;
    private final OrderDomain orderDomain;

    @PostMapping("/v1")
    @ResponseStatus(HttpStatus.OK)
    public GenericProductResponse placeOrder(
            @RequestBody OrderRequest orderRequest) {
        return genericProductModelResponseMapper.map(orderDomain.placeOrder(orderRequestModelMapper.map(orderRequest)));
    }
}
