package com.qlsvtc.statics;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {
    private String DBMASTERurl;

    private String DBCNTTurl;

    private String DBVTurl;

    private String DBuser;

    private String DBpass;




}