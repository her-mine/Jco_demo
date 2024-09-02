package com.mine.jco_framework.controller;

import com.mine.jco_framework.service.IndexService;
import com.sap.conn.jco.JCoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lynn
 * @date 2024/8/27/16:25
 */
@Slf4j
@Validated
@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;

    @PostMapping("/conn")
    public String demoController() throws JCoException {
        return indexService.demoService();
    }
}
