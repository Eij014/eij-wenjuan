package com.eij.wenjuan.component.utils.jdbc;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-08
 */
@Configuration
public class DataSourceConfig {
    @Bean
    public static DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://192.168.43.178:3306/eij-wenjuan?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        dataSourceBuilder.username("eij");
        dataSourceBuilder.password("waibi014");
        return dataSourceBuilder.build();
    }
}