package com.springcloudalibaba.controller;

import com.springcloud.common.CommonResult;
import com.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(produces = "application/json")
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    private static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"112312312312312"));
        hashMap.put(2L,new Payment(2L,"22222222223434343"));
        hashMap.put(3L,new Payment(3L,"333333333312312"));
    }

    @GetMapping(value = "paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable Long id){
        System.out.println("****9003");
        Payment payment = hashMap.get(id);
        return new CommonResult<>(200,"from mysql "+serverPort,payment);
    }


    @PostMapping(value = "paymentSQL/getId")
    public CommonResult<Payment> paymentSQL2(@RequestParam(required = false) Long id){
        System.out.println("****post:9003");
        Payment payment = hashMap.get(id);
        return new CommonResult<>(200,"from mysql "+serverPort,payment);
    }
}
