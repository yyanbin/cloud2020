package com.springcloudalibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.common.CommonResult;

public class CustomerBlockHandler {

    public CommonResult handleException(BlockException blockException){
        return new CommonResult(444,blockException.getClass().getCanonicalName()+"  客户自定义，global--handleException---1");
    }

    public CommonResult handleException2(BlockException blockException){
        return new CommonResult(444,blockException.getClass().getCanonicalName()+"  客户自定义，global--handleException----2");
    }
}
