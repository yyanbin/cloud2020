package com.springcloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.common.CommonResult;
import com.springcloud.entity.Payment;
import com.springcloudalibaba.myhandler.CustomerBlockHandler;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(produces= MediaType.APPLICATION_JSON_VALUE)
public class SentinelController {

    @GetMapping("/testA")
    public String testA()  {
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "*********testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "*********testB";
    }

    @GetMapping("/testD")
    public String testD()  {
        try {
            System.out.println("****test***RT");
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "*********testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(required = false,value = "p1") String p1,
                             @RequestParam(required = false,value = "p2") String p2){
        return "****testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException blockException){
        return  "****deal_testHotKey";
    }


    @GetMapping(value = "/byResource",produces= MediaType.APPLICATION_JSON_VALUE)
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"aaa",new Payment(20L,"删除"));
    }

    public CommonResult handleException(BlockException blockException){
        return new CommonResult(444,blockException.getClass().getCanonicalName()+"  服务不可用");
    }

    @RequestMapping(value = "/byUrl",method= RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"byUrl",new Payment(20L,"测试"));
    }


    @RequestMapping(value = "/byCustomize",method= RequestMethod.GET)
    @SentinelResource(value = "byCustomize",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handleException")
    public CommonResult byCustomize(){
        return new CommonResult(200,"byCustomize",new Payment(20L,"自定义"));
    }
}
