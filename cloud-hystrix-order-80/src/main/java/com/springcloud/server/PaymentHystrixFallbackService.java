package com.springcloud.server;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixFallbackService implements PaymentHystrixService{


    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentHystrixFallbackService fall back  paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentHystrixFallbackService fall back  paymentInfo_TimeOut";
    }
}
