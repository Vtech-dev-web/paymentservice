package com.ecart.paymentservice.service;

import com.ecart.paymentservice.dao.PaymentsDao;
import com.ecart.paymentservice.model.PaymentsResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImpl implements PaymentsService{

    private PaymentsDao paymentsDao;

    public PaymentsServiceImpl(PaymentsDao paymentsDao){
        this.paymentsDao = paymentsDao;
    }

    @Override
    public PaymentsResponse paymentStatus(Long orderId) {

        PaymentsResponse paymentsResponse = new PaymentsResponse();

        Boolean response = paymentsDao.paymentsStatus(orderId);
        if(response) {
            paymentsResponse.setStatus("Success");
            paymentsResponse.setMessage(orderId +" Successfully Paid!. ");
            paymentsResponse.setStatusCode("103");
        }
        return paymentsResponse;
    }
}
