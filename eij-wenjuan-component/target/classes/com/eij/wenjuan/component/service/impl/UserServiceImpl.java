package com.eij.wenjuan.component.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.bean.entity.User;
import com.eij.wenjuan.component.dao.UserDao;
import com.eij.wenjuan.component.exception.ServiceException;
import com.eij.wenjuan.component.exception.WenjuanErrors;
import com.eij.wenjuan.component.service.UserService;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-03-13
 */
@Service
public class UserServiceImpl implements UserService {
    private static final String USER = "y754b67daad4e2c4935ecb0e3194cfbc";

    private static final String SECRET_KEY = "809c5e08b744480f8c6a349149e2b49b";

    private static final String USER_TOKEN_SUF = "==-";
    @Autowired
    private UserDao userDao;

    public String userLoginOrRegister(String username, String password, String loginOrRegister) {
        if (loginOrRegister.equals("login")) {
            List<User> userList = userDao.selectByUsername(username);
            if (CollectionUtils.isEmpty(userList)) {
                throw ServiceException.ofMessage(WenjuanErrors.NOT_FOUND, "用户名不存在！");
            }
            userList = userList.stream().filter(o -> o.getPassword().equals(password))
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(userList)) {
                throw ServiceException.ofMessage(WenjuanErrors.OPERATION_FAIL, "密码错误！");
            }
            return userList.get(0).getUserToken();
        } else if (loginOrRegister.equals("register")) {
            List<User> userList = userDao.selectByUsername(username);
            if (CollectionUtils.isNotEmpty(userList)) {
                throw ServiceException.ofMessage(WenjuanErrors.EXIST_ALREADY, "用户名已存在，请更换用户名");
            }
            String userToken = hmacsha256() + USER_TOKEN_SUF + username;
            User user = new User(username, password, userToken);
            user.setHeadUrl("");
            user.setPhoneNumber("");
            userDao.insert(user);
            return userToken;
        }
        return "";
    }

//    private String createUserToken(String value) throws UnsupportedEncodingException {
//        return value != null ? URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A")
//                .replace("%7E", "~") : null;
//    }

    public String hmacsha256() {
        String contentToBeSigned = "USER:" + USER + "\n"
                + "createTime:" + System.currentTimeMillis() / 1000 + "\n";
        try {
            byte[] byteKey = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(byteKey, "HmacSHA256");
            hmac.init(keySpec);
            byte[] byteSig = hmac.doFinal(contentToBeSigned.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.encodeBase64(byteSig));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            return "";
        }
    }
}
