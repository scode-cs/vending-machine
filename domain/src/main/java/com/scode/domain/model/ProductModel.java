package com.scode.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductModel {
    private Long id;
    private String name;
    private Date createdAt;
    private String createdBy;
}
