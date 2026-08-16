package com.ecart.paymentservice.dao;

import com.ecart.paymentservice.model.PaymentsResponse;
import org.springframework.stereotype.Component;

@Component
public interface PaymentsDao {
    public Boolean paymentsStatus(Long orderId);
}
