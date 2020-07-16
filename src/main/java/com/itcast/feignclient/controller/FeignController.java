package com.itcast.feignclient.controller;

import com.itcast.feignclient.pojo.ResponseModel;
import com.itcast.feignclient.service.FeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feign")
@Api(tags = "教育接口")
public class FeignController {
    @Resource
    private FeignService feignService;

    @GetMapping("/get")
    @ApiOperation(value = "媒体数据信息接口")
    public ResponseModel feignInfo() {
        return feignService.getMediaOutPut(10, 10);
    }
}
