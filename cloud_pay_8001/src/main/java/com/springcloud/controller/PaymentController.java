package com.springcloud.controller;

import com.springcloud.common.CommonResult;
import com.springcloud.entity.Payment;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yanbin_vendor
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 服务发现来获取该服务的信息
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int id = paymentService.create(payment);
        System.out.println("++++id:{}" + payment.getId());
        if (id > 0) {
            return new CommonResult(200, "数据插入成功！" + serverPort, payment.getId());
        } else {
            return new CommonResult(444, "数据插入失败！" + serverPort);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        if (paymentById != null) {
            return new CommonResult(200, "查询成功！" + serverPort, paymentById);
        } else {
            return new CommonResult(444, "没有查询到数据,查询id:" + id + "," + serverPort);
        }
    }

    @GetMapping("/payment/discover")
    public Object discoveryClient() {

        /**
         * eureka 上服务
         */
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("******element:" + element);
        }

        /**
         * eureka 上服务名称为：CLOUD-PAYMENT-SERVICE 的实例
         */
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLb() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String getPaymentFeignTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/payment/zipkin")
    public String getZipkin() {
        return "pay 8001 zipkin";
    }
}
