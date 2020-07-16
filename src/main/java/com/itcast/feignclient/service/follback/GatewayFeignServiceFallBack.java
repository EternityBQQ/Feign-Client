package com.itcast.feignclient.service.follback;

import com.itcast.feignclient.pojo.ResponseModel;
import com.itcast.feignclient.service.FeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 熔断处理
 */
@Component
public class GatewayFeignServiceFallBack implements FeignService {
    private static final Logger LOG = LoggerFactory.getLogger(GatewayFeignServiceFallBack.class);
    public static final String ERR_MSG = "服务暂时不可用";

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public ResponseModel getMediaOutPut(Integer carouseSize, Integer categorySize) {
        ///////////////////// 调用其他服务替代或者跳转啥啥的
        LOG.error(applicationName);
        return ResponseModel.build(500, ERR_MSG);
    }
}
