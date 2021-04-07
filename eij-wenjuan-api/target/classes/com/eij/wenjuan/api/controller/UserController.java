package com.eij.wenjuan.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eij.wenjuan.api.request.UserLoginRequest;
import com.eij.wenjuan.api.response.WenjuanResponseMessage;
import com.eij.wenjuan.component.service.UserService;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-13
 */
@RestController
@RequestMapping(path = {"/wenjuan/user"})
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public WenjuanResponseMessage<String> getWenjuanList(@RequestBody UserLoginRequest userLoginRequest) {
        return WenjuanResponseMessage.success(userService.userLoginOrRegister(userLoginRequest.getUsername(),
                                               userLoginRequest.getPassword(),
                                               userLoginRequest.getLoginOrRegister()));
    }
}
