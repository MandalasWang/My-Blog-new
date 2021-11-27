package com.site.blog.my.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: boyuan1024shop
 * @description: //TODO 功能描述
 * @author: Mr.Wang
 * @create: 2021-09-11 15:40
 **/
@ConfigurationProperties(prefix = "aliyun.oss")
@Configuration
@Data
public class OSSConfig {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketname;
}
