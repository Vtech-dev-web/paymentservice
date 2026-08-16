package com.ecart.paymentservice.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentsDaoImplTest {

    @Mock
    private NamedParameterJdbcTemplate mysqlJdbcTemplate;

    @InjectMocks
    private PaymentsDaoImpl paymentsDao;

    @Test
    void paymentsStatus_ShouldReturnTrue_WhenOrderIsUpdated() {

        Long orderId = 101L;

        when(mysqlJdbcTemplate.update(
                anyString(),
                any(MapSqlParameterSource.class)
        )).thenReturn(1);

        Boolean result = paymentsDao.paymentsStatus(orderId);

        assertTrue(result);

        verify(mysqlJdbcTemplate, times(1))
                .update(
                        anyString(),
                        any(MapSqlParameterSource.class)
                );
    }

    @Test
    void paymentsStatus_ShouldReturnFalse_WhenNoOrderIsUpdated() {

        Long orderId = 101L;

        when(mysqlJdbcTemplate.update(
                anyString(),
                any(MapSqlParameterSource.class)
        )).thenReturn(0);

        Boolean result = paymentsDao.paymentsStatus(orderId);

        assertFalse(result);

        verify(mysqlJdbcTemplate, times(1))
                .update(
                        anyString(),
                        any(MapSqlParameterSource.class)
                );
    }

    @Test
    void paymentsStatus_ShouldReturnFalse_WhenDatabaseThrowsException() {

        Long orderId = 101L;

        when(mysqlJdbcTemplate.update(
                anyString(),
                any(MapSqlParameterSource.class)
        )).thenThrow(new RuntimeException("Database connection failed"));

        Boolean result = paymentsDao.paymentsStatus(orderId);

        assertFalse(result);

        verify(mysqlJdbcTemplate, times(1))
                .update(
                        anyString(),
                        any(MapSqlParameterSource.class)
                );
    }
}