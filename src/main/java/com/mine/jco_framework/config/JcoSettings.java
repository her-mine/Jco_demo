package com.mine.jco_framework.config;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author lynn
 * @date 2024/8/27/15:58
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JcoSettings implements Serializable {
    @Builder.Default
    private final String defaultKey = UUID.randomUUID().toString();
    @Builder.Default
    private String ashost = "";
    @Builder.Default
    private String sysnr = "";
    @Builder.Default
    private String client = "";
    @Builder.Default
    private String user = "";
    @Builder.Default
    private String passwd = "";
    @Builder.Default
    private String lang = "zh";
    @Builder.Default
    private String poolCapacity = "20";
    @Builder.Default
    private String peakLimit = "5";
}
