package com.ecart.paymentservice.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentsDaoImpl implements PaymentsDao {

    private NamedParameterJdbcTemplate mysqlJdbcTemplate;

    public PaymentsDaoImpl(NamedParameterJdbcTemplate mysqlJdbcTemplate) {
        this.mysqlJdbcTemplate = mysqlJdbcTemplate;
    }

    @Override
    public Boolean paymentsStatus(Long orderId) {
        String sql = "UPDATE ecart.orders SET status = 'PAID' WHERE order_id= :orderid";

        try {
            MapSqlParameterSource newparams = new MapSqlParameterSource().addValue("orderid", orderId);
            int val = mysqlJdbcTemplate.update(sql, newparams);
            if (val > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }
}
