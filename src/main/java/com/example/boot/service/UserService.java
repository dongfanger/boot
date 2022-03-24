package com.example.boot.service;


import com.example.boot.entity.response.UserResp;
import com.example.boot.mbg.model.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * UserService
 * Created by macro on 2019/4/19.
 */
public interface UserService {
    List<User> listAllUser();

//    int createUser(User user);

    User updateUser(Long id, User user);

    int deleteUser(Long id);

    PageInfo<UserResp> listUser(String keyword, int pageNum, int pageSize);

    UserResp getUser(Long id);

//    /**
//     * 根据用户名获取后台管理员
//     */
//    User getUserByUsername(String username);
//
    /**
     * 注册功能
     */
    UserResp register(User user);

//    /**
//     * 登录功能
//     *
//     * @param username 用户名
//     * @param password 密码
//     * @return 生成的JWT的token
//     */
//    String login(String username, String password);
//
//
//    UserLoginResp getUserLoginResult(User user);
//
//    Boolean updatePassword(HttpHeaders headers, PasswordReq passwordReq);
//
//    String resetPassword(Long id);
}
