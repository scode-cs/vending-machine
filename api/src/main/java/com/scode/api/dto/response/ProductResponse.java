package com.scode.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private Date createdAt;
    private String createdBy;
}
