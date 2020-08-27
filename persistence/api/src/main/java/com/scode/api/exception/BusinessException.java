package com.scode.api.exception;

import com.scode.api.dto.response.ExceptionResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private ExceptionResponse exceptionResponse;
}
