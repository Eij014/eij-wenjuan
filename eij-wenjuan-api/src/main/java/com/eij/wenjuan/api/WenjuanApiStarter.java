package com.eij.wenjuan.api;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-01
 */
@EijBootApplication(scanBasePackages = {"com.eij.wenjuan"})
@PropertySource(value = {"classpath:common.properties"})
@EnableAsync
public class WenjuanApiStarter {
    public static void main(String[] args) {
        SpringApplication.run(WenjuanApiStarter.class, args);
    }
}
