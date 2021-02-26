package com.springcloudalibaba.feign;

import com.springcloud.common.CommonResult;
import com.springcloud.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回---PaymentFallbackService");

    }

    @Override
    public CommonResult<Payment> paymentSQL2(Long id) {
        return new CommonResult<>(444, "post服务降级返回---PaymentFallbackService");

    }
}
