package com.scode.api.dto.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericDetailedResponse {
    private Integer status;
    private String message;
    private Map<String, Object> responseDetails;
}
