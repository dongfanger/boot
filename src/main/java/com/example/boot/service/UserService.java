package com.example.boot.service;


import com.example.boot.entity.request.PasswordReq;
import com.example.boot.entity.response.UserLoginResp;
import com.example.boot.entity.response.UserResp;
import com.example.boot.mbg.model.User;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpHeaders;

import java.util.List;

/**
 * UserService
 * Created by macro on 2019/4/19.
 */
public interface UserService {
    List<User> listAllUser();

    User updateUser(Long id, User user);

    int deleteUser(Long id);

    PageInfo<UserResp> listUser(String keyword, int pageNum, int pageSize);

    UserResp getUser(Long id);

    User getUserByUsername(String username);

    UserResp register(User user);

    String login(String username, String password);

    UserLoginResp getUserLoginResult(User user);

    Boolean updatePassword(HttpHeaders headers, PasswordReq passwordReq);

    String resetPassword(Long id);
}
