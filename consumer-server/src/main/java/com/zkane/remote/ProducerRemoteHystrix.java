package com.zkane.remote;

import org.springframework.stereotype.Component;

/**
 * @author: zhaoqiang
 * @date: 2018/5/14
 */
@Component
public class ProducerRemoteHystrix implements ProducerRemote {
    @Override
    public String getPort() {
        return "Producer Server 的服务调用失败";
    }
}
