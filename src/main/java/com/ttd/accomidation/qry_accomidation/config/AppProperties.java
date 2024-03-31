package com.ttd.accomidation.qry_accomidation.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Setter
@Getter
public class AppProperties {
    private String[] emailRecipient;
    //private List<Integer> test;
    private String ttdAccUrl;

    private String cronExpression;
}
