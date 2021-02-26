package springcloudalibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Paymentcontroller {
    @Value("${server.port}")
    String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String echo(@PathVariable Integer id) {
        return serverPort +";Hello Nacos Discovery " + id;
    }
}
