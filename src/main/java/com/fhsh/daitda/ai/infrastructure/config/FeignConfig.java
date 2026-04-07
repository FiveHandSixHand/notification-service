package com.fhsh.daitda.ai.infrastructure.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.fhsh.daitda.ai.infrastructure.external")
public class FeignConfig {
}
