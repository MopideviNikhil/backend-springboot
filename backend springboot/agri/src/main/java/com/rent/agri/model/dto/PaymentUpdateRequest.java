package com.rent.agri.model.dto;

import com.rent.agri.model.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentUpdateRequest {

    @NotNull(message = "Payment ID is required")
    private Long paymentId;

    @NotNull(message = "Payment status is required")
    private PaymentStatus status;
}
