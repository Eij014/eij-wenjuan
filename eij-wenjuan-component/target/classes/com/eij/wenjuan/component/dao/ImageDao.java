package com.eij.wenjuan.component.dao;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.eij.wenjuan.component.bean.Image;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-02-08
 */
@Lazy
@Repository
public class ImageDao extends AbstractDao {
    private static final String TABLE_NAME = "image";

    private static final String SQL_INSERT = "insert into " + TABLE_NAME
            + " (`image_key`, `image_url`)"
            + " values"
            + " (:imageKey,:imageUrl)";

    private static final String SQL_SELECT = "select * from " + TABLE_NAME;

    private static final RowMapper<Image> ROW_MAPPER = new BeanPropertyRowMapper<>(Image.class);

    public void insert(Image image) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("imageKey", image.getImageKey());
        source.addValue("imageUrl", image.getImageUrl());
        getWriter().update(SQL_INSERT, source);
    }

    public List<Image> selectAll() {
        return getReader().query(SQL_SELECT, ROW_MAPPER);
    }
}
