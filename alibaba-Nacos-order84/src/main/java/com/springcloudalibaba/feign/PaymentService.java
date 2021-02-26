package com.springcloudalibaba.feign;

import com.springcloud.common.CommonResult;
import com.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "nacos-payment-provider" ,fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);

    @PostMapping(value = "/paymentSQL/getId")
    public CommonResult<Payment> paymentSQL2(@RequestParam("id") Long id);
}
