package com.mine.jco_framework;

import com.mine.jco_framework.config.JcoSettings;
import com.mine.jco_framework.config.SapConnProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JcoFrameworkApplicationTests {
    @Autowired
    private SapConnProperties sapConnProperties;

    @Test
    void contextLoads() {
        JcoSettings settings = JcoSettings.builder()
                .ashost(sapConnProperties.getAshost())
                .sysnr(sapConnProperties.getSysnr())
                .client(sapConnProperties.getClient())
                .user(sapConnProperties.getUser())
                .passwd(sapConnProperties.getPasswd())
                .lang(sapConnProperties.getLang())
                .poolCapacity(sapConnProperties.getPoolCapacity())
                .peakLimit(sapConnProperties.getPeakLimit())
                .build();

        System.out.println(settings);
    }

}
