package com.springcloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.common.CommonResult;
import com.springcloud.entity.Payment;
import com.springcloudalibaba.feign.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping(produces = "application/json")
public class OrderController {

    @Value("${service-url.nacos-user-service}")
    private String serverURL;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentService paymentService;


    @GetMapping("order/{id}")
//    @SentinelResource(value = "fallback",fallback = "handelFallback")
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler")
    @SentinelResource(value = "fallback", blockHandler = "blockHandler",fallback = "handelFallback",
    exceptionsToIgnore = IllegalArgumentException.class)
    public CommonResult<Payment> fallBack(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(serverURL + "/paymentSQL/" + id, CommonResult.class);

        if (id == 4) {
            throw new IllegalArgumentException("参数非法");
        } else if (result.getData() == null) {
            throw new NullPointerException("没有对应的记录");
        }
        return result;
    }

    public CommonResult<Payment> handelFallback(@PathVariable Long id, Throwable throwable) {
        Payment payment = new Payment(id, null);
        return new CommonResult<>(444, "兜底异常：handelFallback:" + throwable.getMessage(), payment);
    }

    public CommonResult<Payment> blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id, null);
        return new CommonResult<>(445, "blockHandler:" + blockException.getMessage(), payment);
    }

//============OpenFeign
    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }

    @PostMapping(value = "/paymentSQL/getId")
    public CommonResult<Payment> paymentSQL2(@RequestParam(required = false) String id){
        return paymentService.paymentSQL2(Long.getLong(id));
    }

}
