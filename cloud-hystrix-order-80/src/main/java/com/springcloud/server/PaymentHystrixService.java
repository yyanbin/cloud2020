package com.springcloud.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * FeignClient: fallback 它的运行逻辑是:
 * 		当请求过来,首先还是通过Feign远程调用pay模块对应的方法
 *     但是如果pay模块报错,调用失败,那么就会调用PaymentHystrixFallbackService类的
 *     当前同名的方法,作为降级方法
 */
@Component
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT",fallback = PaymentHystrixFallbackService.class)
public interface PaymentHystrixService {
    @GetMapping(value = "/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
