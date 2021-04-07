package com.eij.wenjuan.api;

import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

import com.eij.wenjuan.api.filter.UserContextFilter;
import com.google.common.collect.Sets;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-01
 */
@EijBootApplication(scanBasePackages = {"com.eij.wenjuan"})
@PropertySource(value = {"classpath:common.properties"})
@EnableAsync
public class WenjuanApiStarter {

    @Bean
    public UserContextFilter.WhiteList whiteList() {
        return new UserContextFilter.WhiteList() {
            @Override
            protected Set<String> getPatternSet() {
                return Sets.newHashSet(
                        "/wenjuan/user/login",
                        "/wenjuan/upload/image"
                );
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(WenjuanApiStarter.class, args);
    }
}
