package com.rent.agri.model.dto;

import com.rent.agri.model.enums.PaymentMode;
import com.rent.agri.validation.annotation.ValidPaymentMode;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {

    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private double amount;

    @NotNull(message = "Payment mode is required")
    @ValidPaymentMode
    private PaymentMode paymentMode;
}
