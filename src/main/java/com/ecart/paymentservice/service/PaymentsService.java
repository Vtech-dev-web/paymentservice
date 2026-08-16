package com.ecart.paymentservice.service;

import com.ecart.paymentservice.model.PaymentsResponse;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnCheckpointRestore;
import org.springframework.stereotype.Component;

@Component
public interface PaymentsService {
    public PaymentsResponse paymentStatus(Long orderId);

}
