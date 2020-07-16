package com.itcast.feignclient.service;

import com.itcast.feignclient.pojo.ResponseModel;
import com.itcast.feignclient.service.follback.GatewayFeignServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "education-provider", fallback = GatewayFeignServiceFallBack.class)
public interface FeignService {
    @GetMapping("/education/mediaOutPut")
    @ResponseBody
    ResponseModel getMediaOutPut(@RequestParam Integer carouseSize, @RequestParam Integer categorySize);
}
