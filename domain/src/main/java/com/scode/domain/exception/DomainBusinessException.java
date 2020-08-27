package com.scode.domain.exception;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainBusinessException extends RuntimeException {
    private String message;
}
