package com.ehr.admin.config;

import com.ehr.admin.feign.FiegnExceptionHandling.CustomFeignErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalFeignConfig {

    @Bean
    public ErrorDecoder globalErrorDecoder() {
        return new CustomFeignErrorDecoder();
    }
}
