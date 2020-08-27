package com.scode.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericProductResponseModel {
    private Long productId;
    private String productName;
    private String message;
}
