package com.eij.wenjuan.component.dao;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.eij.wenjuan.component.bean.entity.User;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-13
 */
@Lazy
@Repository
public class UserDao extends AbstractDao {

    private static final String TABLE_NAME = "user";

    private static final String SQL_INSERT = "insert into " + TABLE_NAME
            + " (`username`,`password`,`phone_num`,`head_url`,`create_time`,`user_token`)"
            + " values"
            + " (:username,:password,:phoneNum,:headUrl,:createTime, :userToken)";

    private static final String SQL_SELECT_BY_USERNAME = "select * from " + TABLE_NAME
            + " where username = :username";

    private static final RowMapper<User> ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

    public void insert(User user) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("username", user.getUsername());
        source.addValue("password", user.getPassword());
        source.addValue("phoneNum", user.getPhoneNumber());
        source.addValue("headUrl", user.getHeadUrl());
        source.addValue("createTime", System.currentTimeMillis() / 1000);
        source.addValue("userToken", user.getUserToken());
        getWriter().update(SQL_INSERT, source);
    }

    public List<User> selectByUsername(String username) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("username", username);
        return getReader().query(SQL_SELECT_BY_USERNAME, source, ROW_MAPPER);
    }

}
