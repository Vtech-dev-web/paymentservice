package com.ecart.paymentservice.service;

import com.ecart.paymentservice.dao.PaymentsDao;
import com.ecart.paymentservice.model.PaymentsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentsServiceImplTest {

    @Mock
    private PaymentsDao paymentsDao;

    @InjectMocks
    private PaymentsServiceImpl paymentsService;

    @Test
    void paymentStatus_ShouldReturnSuccess_WhenPaymentIsSuccessful() {

        Long orderId = 101L;

        when(paymentsDao.paymentsStatus(orderId))
                .thenReturn(true);

        PaymentsResponse response =
                paymentsService.paymentStatus(orderId);

        assertNotNull(response);

        assertEquals("Success", response.getStatus());
        assertEquals(
                "101 Successfully Paid!. ",
                response.getMessage()
        );
        assertEquals("103", response.getStatusCode());

        verify(paymentsDao, times(1))
                .paymentsStatus(orderId);
    }

    @Test
    void paymentStatus_ShouldReturnEmptyResponse_WhenPaymentFails() {

        Long orderId = 101L;

        when(paymentsDao.paymentsStatus(orderId))
                .thenReturn(false);

        PaymentsResponse response =
                paymentsService.paymentStatus(orderId);

        assertNotNull(response);

        assertNull(response.getStatus());
        assertNull(response.getMessage());
        assertNull(response.getStatusCode());

        verify(paymentsDao, times(1))
                .paymentsStatus(orderId);
    }

    @Test
    void paymentStatus_ShouldThrowException_WhenDaoThrowsException() {

        Long orderId = 101L;

        when(paymentsDao.paymentsStatus(orderId))
                .thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> paymentsService.paymentStatus(orderId)
        );

        assertEquals("Database error", exception.getMessage());

        verify(paymentsDao, times(1))
                .paymentsStatus(orderId);
    }
}