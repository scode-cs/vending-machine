package com.scode.api.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private HttpStatus status;
    private String message;
}
