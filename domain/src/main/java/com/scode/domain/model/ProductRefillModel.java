package com.scode.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRefillModel {
    private Long id;
    private String name;
    private Long avlQuantity;
    private Long maxCapacity;
}
