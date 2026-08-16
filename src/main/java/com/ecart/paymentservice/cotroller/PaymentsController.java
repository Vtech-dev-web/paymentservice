package com.ecart.paymentservice.cotroller;

import com.ecart.paymentservice.model.PaymentsResponse;
import com.ecart.paymentservice.service.PaymentsService;
import com.ecart.paymentservice.service.PaymentsServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/api")
public class PaymentsController {

    private PaymentsService paymentsService;

    PaymentsController(PaymentsService paymentsService){
        this.paymentsService=paymentsService;
    }

    @PostMapping("/payments/{orderId}")
    public PaymentsResponse payment(@PathVariable Long orderId){

        PaymentsResponse paymentsResponse = paymentsService.paymentStatus(orderId);

        return paymentsResponse;
    }
}
