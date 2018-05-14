package com.zkane.controller;

import com.zkane.remote.ProducerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhaoqiang
 * @date: 2018/5/14
 */
@RestController
public class ConsumerController {

    @Autowired
    private ProducerRemote producerRemote;

    @GetMapping("/get")
    public String get() {
        return "consumer server " + producerRemote.getPort();
    }
}
