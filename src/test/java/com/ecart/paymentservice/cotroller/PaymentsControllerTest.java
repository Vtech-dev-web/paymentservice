package com.ecart.paymentservice.cotroller;

import com.ecart.paymentservice.model.PaymentsResponse;
import com.ecart.paymentservice.service.PaymentsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentsControllerTest {

    @Mock
    private PaymentsService paymentsService;

    @InjectMocks
    private PaymentsController paymentsController;

    @Test
    void payment_ShouldReturnPaymentsResponse() {

        Long orderId = 101L;

        PaymentsResponse expectedResponse = new PaymentsResponse();

        when(paymentsService.paymentStatus(orderId))
                .thenReturn(expectedResponse);

        PaymentsResponse actualResponse =
                paymentsController.payment(orderId);

        assertNotNull(actualResponse);
        assertSame(expectedResponse, actualResponse);

        verify(paymentsService, times(1))
                .paymentStatus(orderId);
    }

    @Test
    void payment_ShouldReturnNull_WhenServiceReturnsNull() {

        Long orderId = 101L;

        when(paymentsService.paymentStatus(orderId))
                .thenReturn(null);

        PaymentsResponse actualResponse =
                paymentsController.payment(orderId);

        assertNull(actualResponse);

        verify(paymentsService, times(1))
                .paymentStatus(orderId);
    }

    @Test
    void payment_ShouldThrowException_WhenServiceThrowsException() {

        Long orderId = 101L;

        when(paymentsService.paymentStatus(orderId))
                .thenThrow(new RuntimeException("Payment service failed"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> paymentsController.payment(orderId)
        );

        assertEquals("Payment service failed", exception.getMessage());

        verify(paymentsService, times(1))
                .paymentStatus(orderId);
    }
}