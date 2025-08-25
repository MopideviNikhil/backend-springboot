package com.rent.agri.service.impl;

import org.springframework.stereotype.Service;

import com.rent.agri.model.dto.PaymentUpdateRequest;
import com.rent.agri.model.entity.Payment;
import com.rent.agri.repository.PaymentRepository;
import com.rent.agri.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment updatePaymentStatus(PaymentUpdateRequest request) {
        Payment payment = paymentRepository.findById(request.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Payment not found with ID " + request.getPaymentId()));

        payment.setPaymentStatus(request.getStatus());
        return paymentRepository.save(payment);
    }
}
