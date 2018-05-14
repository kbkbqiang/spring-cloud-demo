package com.zkane.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: zhaoqiang
 * @date: 2018/5/14
 */
@Component
@FeignClient(value = "producer-server", fallback = ProducerRemoteHystrix.class)
public interface ProducerRemote {
    @GetMapping("/get")
    String getPort();
}
