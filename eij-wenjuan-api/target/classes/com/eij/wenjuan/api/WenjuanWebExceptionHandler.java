package com.eij.wenjuan.api;


import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.eij.wenjuan.api.response.WenjuanResponseMessage;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-13
 */
@RestControllerAdvice
public class WenjuanWebExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WenjuanWebExceptionHandler.class);


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public WenjuanResponseMessage handleException(HttpServletRequest request, RuntimeException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return WenjuanResponseMessage.failure(ex.getMessage());
    }


    @ExceptionHandler({DataAccessException.class, MetaDataAccessException.class, SQLException.class})
    @ResponseStatus(HttpStatus.OK)
    public WenjuanResponseMessage handleDaoException(HttpServletRequest request, Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return WenjuanResponseMessage.failure("sql operation error!");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public WenjuanResponseMessage internalServerError(HttpServletRequest request, Exception e) {
        LOGGER.error("internal server error!", e);
        return WenjuanResponseMessage.failure("internal server error!");
    }
}
