package com.xxx.tcc;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DubboProviderApplication
 */
@EnableDubbo
@SpringBootApplication
@EnableTransactionManagement
@ImportResource({"classpath:tcc-transaction.xml"})
public class DubboTCCApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboTCCApplication.class, args);
    }
}
