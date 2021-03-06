package com.scode.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT_MASTER")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @JoinColumn(name = "PRODUCT_ID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private InventoryEntity inventoryEntity;
}
