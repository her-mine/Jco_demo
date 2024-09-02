package com.mine.jco_framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lynn
 * @date 2024/8/27/15:59
 */
@ConfigurationProperties(prefix = "ldx.jco")
@Data
@Component
public class SapConnProperties {
    private String ashost;
    private String sysnr;
    private String client;
    private String user;
    private String passwd;
    private String lang;
    private String poolCapacity;
    private String peakLimit;
}
