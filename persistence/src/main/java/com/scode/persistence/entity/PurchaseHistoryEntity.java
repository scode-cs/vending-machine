package com.scode.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT_PURCHASE_HISTORY")
public class PurchaseHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURC_ID")
    private Long id;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PURCHASE_AMOUNT")
    private Long purchasedAmount;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PURCHASED_AT")
    private Date purchasedAt;

    @Column(name = "PURCHASED_BY")
    private String purchasedBy;

}
