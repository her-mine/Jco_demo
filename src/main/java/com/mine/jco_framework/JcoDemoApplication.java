package com.mine.jco_framework;

import com.mine.jco_framework.config.JcoDataProvider;
import com.mine.jco_framework.config.JcoSettings;
import com.mine.jco_framework.config.SapConnProperties;
import com.mine.jco_framework.utils.SapUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JcoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JcoDemoApplication.class, args);
    }

    @Bean
    public SapUtils sapUtils(SapConnProperties sapConnProperties) {
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

        JcoDataProvider.registerInEnvironment();
        SapUtils sapUtils = new SapUtils(settings);
        return sapUtils;
    }

}
