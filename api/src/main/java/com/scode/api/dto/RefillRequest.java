package com.scode.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefillRequest {
    private Long productId;
    private Long refillQuantity;
}
