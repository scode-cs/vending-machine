package com.scode.api.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericProductResponse {
    private Long productId;
    private String productName;
    private String message;
}
