package com.rent.agri.service;

import com.rent.agri.model.dto.PaymentUpdateRequest;
import com.rent.agri.model.entity.Payment;

public interface PaymentService {
    Payment updatePaymentStatus(PaymentUpdateRequest request);
}
