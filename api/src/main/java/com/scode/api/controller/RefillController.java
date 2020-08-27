package com.scode.api.controller;

import com.scode.api.dto.RefillRequest;
import com.scode.api.dto.request.mapper.RefillRequestModelMapper;
import com.scode.api.dto.response.GenericProductResponse;
import com.scode.api.dto.response.mapper.GenericProductModelResponseMapper;
import com.scode.domain.RefillDomain;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/refill")
@RequiredArgsConstructor
@Api(tags = "Refill Resources")
public class RefillController {

    private final RefillDomain refillDomain;
    private final RefillRequestModelMapper refillRequestModelMapper;
    private final GenericProductModelResponseMapper genericProductModelResponseMapper;

    @PutMapping("/v1")
    @ResponseStatus(HttpStatus.OK)
    public GenericProductResponse placeOrder(@RequestBody RefillRequest refillRequest) {
        return genericProductModelResponseMapper.map(refillDomain.refillOrder(refillRequestModelMapper.map(refillRequest)));
    }
}
