package com.scode.api.handler;

import com.scode.api.dto.response.ExceptionResponse;
import com.scode.api.dto.response.GenericDetailedResponse;
import com.scode.api.exception.BusinessException;
import com.scode.domain.exception.DomainBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponse> exception(BusinessException exception) {
        return ResponseEntity.status(exception.getExceptionResponse().getStatus())
                .body(exception.getExceptionResponse());
    }

    @ExceptionHandler(DomainBusinessException.class)
    public ResponseEntity<ExceptionResponse> exception(DomainBusinessException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(ExceptionResponse.builder().status(HttpStatus.BAD_REQUEST)
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericDetailedResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectError> fieldErrors = bindingResult.getAllErrors();
        Map<String, Object> errorDetailsMap = getJsrValidationMessage(fieldErrors);

        return new GenericDetailedResponse(HttpStatus.BAD_REQUEST.value(), "Invalid input", errorDetailsMap);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleAllTechnicalException(IllegalArgumentException exception) {
        log.error("AllTechnicalException: " + exception.getMessage());
        return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    private Map<String, Object> getJsrValidationMessage(List<ObjectError> fieldErrors) {
        Map<String, Object> detailedResponseMap = new LinkedHashMap<>();
        fieldErrors.forEach(fieldError -> {
            if (fieldError instanceof FieldError) {
                String fieldName = ((FieldError) fieldError).getField();
                String defaultMessage = fieldError.getDefaultMessage();
                if (Objects.nonNull(fieldError.getCodes()) && fieldError.getCodes().length > 0) {
                    String fieldErrorCode = fieldError.getCodes()[fieldError.getCodes().length - 1];
                    switch (fieldErrorCode) {
                        case "Pattern":
                            detailedResponseMap.put(fieldName, fieldName + " must be in proper format");
                            break;
                        default:
                            detailedResponseMap.put(fieldName, fieldName + " " + defaultMessage);
                            break;
                    }
                } else {
                    detailedResponseMap.put(fieldName, fieldName + " " + defaultMessage);
                }
            } else {
                detailedResponseMap.put("Error", fieldError.getDefaultMessage());
            }
        });

        return detailedResponseMap;
    }
}
