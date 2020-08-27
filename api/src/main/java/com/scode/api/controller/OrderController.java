package com.scode.api.controller;

import com.scode.api.dto.request.OrderRequest;
import com.scode.api.dto.request.mapper.OrderRequestModelMapper;
import com.scode.api.dto.response.OrderResponse;
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
    private final OrderModelResponseMapper orderModelResponseMapper;
    private final OrderDomain orderDomain;

    @PostMapping("/v1")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse placeOrder(
            @RequestHeader(required = false, name = "Authorization") String auth,
            @RequestBody OrderRequest orderRequest) {
        return orderModelResponseMapper.map(orderDomain.placeOrder(orderRequestModelMapper.map(orderRequest)));
    }
}
