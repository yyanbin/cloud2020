package com.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yanbin_vendor
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    /**
     * 负载均衡： 默认 轮询负载机制（算法：rest接口第几次请求数 % 集群服务数量 = 实际调用服务的下标，每次服务重启rest接口计数从1开始）
     */
//    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
