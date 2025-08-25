package com.rent.agri.model.entity;

import java.time.LocalDateTime;

import com.rent.agri.model.enums.PaymentMode;
import com.rent.agri.model.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(nullable = false)
    private double price;

    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

   
    @PrePersist
    private void onPrePersist() {
        if (paymentStatus == null) {
            paymentStatus = PaymentStatus.PENDING;
        }
        if (time == null) {
            time = LocalDateTime.now();
        }
    }
}
