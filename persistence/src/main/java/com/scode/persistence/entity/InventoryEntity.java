package com.scode.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT_INVENTORY")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INV_ID")
    private Long id;

//    @JoinColumn(name = "PRODUCT_ID", unique = true)
//    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "MAX_CAPACITY")
    private Long maxCapacity;

    @Column(name = "AVAILABLE_STOCK")
    private Long avlStock;

    @Column(name = "REFILL_PERCENTAGE")
    private Long refillPercentage;
}
