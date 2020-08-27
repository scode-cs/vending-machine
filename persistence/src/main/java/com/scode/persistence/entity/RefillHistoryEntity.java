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
@Table(name = "PRODUCT_REFILL_HISTORY")
public class RefillHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REFILL_ID")
    private Long id;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "REFILL_AMOUNT")
    private Long refilledAmount;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REFILLED_AT")
    private Date refilledAt;

    @Column(name = "REFILLED_BY")
    private String refilledBy;
}
