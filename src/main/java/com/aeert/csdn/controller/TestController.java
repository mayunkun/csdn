package com.aeert.csdn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author l'amour solitaire
 * @Description TODO
 * @Date 2020/11/18 下午2:12
 **/
@Slf4j
@RestController
@RequestMapping("/csdn/")
public class TestController {

    @RequestMapping("/console")
    public void console(@RequestParam String param) {
        log.info("系统时间{} 输入参数{}", System.currentTimeMillis(), param);
    }
}
