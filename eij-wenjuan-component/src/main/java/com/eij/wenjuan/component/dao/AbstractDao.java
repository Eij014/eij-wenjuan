package com.eij.wenjuan.component.dao;

import java.io.Serializable;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.eij.wenjuan.component.utils.jdbc.DataSourceConfig;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-08
 */
public abstract class AbstractDao implements Serializable {

    private NamedParameterJdbcTemplate reader = new NamedParameterJdbcTemplate(DataSourceConfig.getDataSource());

    private NamedParameterJdbcTemplate writer = new NamedParameterJdbcTemplate(DataSourceConfig.getDataSource());

    protected NamedParameterJdbcTemplate getReader() {
        return reader;
    }

    protected NamedParameterJdbcTemplate getWriter() {
        return writer;
    }

}
