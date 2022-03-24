package com.example.boot.controller;

import com.example.boot.common.api.CommonPage;
import com.example.boot.common.api.CommonResult;
import com.example.boot.entity.response.UserResp;
import com.example.boot.mbg.model.User;
import com.example.boot.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * UserController
 * Created by macro on 2019/4/19.
 */
@Api(tags = "UserController", value = "用户管理")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ApiOperation("获取所有用户列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUserList() {
        return new ResponseEntity<>(userService.listAllUser(), HttpStatus.OK);
    }

    @ApiOperation("添加用户")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        UserResp userResp = userService.register(user);
        if (userResp == null) {
            return new ResponseEntity<>(CommonResult.failed("用户已存在"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userResp, HttpStatus.CREATED);
    }

    @ApiOperation("更新指定id用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user, BindingResult result) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(CommonResult.failed("操作失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("删除指定id的用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        int count = userService.deleteUser(id);
        if (count == 1) {
            return new ResponseEntity<>(CommonResult.success(null), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(CommonResult.failed("操作失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("分页查询用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('console:user:list')")
    public ResponseEntity<?> listUser(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                      @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "perPage", defaultValue = "10") Integer pageSize) {
        PageInfo<UserResp> userResultList = userService.listUser(keyword, pageNum, pageSize);

        return new ResponseEntity<>(CommonPage.restPage(userResultList), HttpStatus.OK);
    }

    @ApiOperation("获取指定id的用户详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> user(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
//
//    @ApiOperation(value = "登录以后返回token")
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<?> login(@RequestBody UserLoginReq userLoginReq, BindingResult result) {
//        String username = userLoginReq.getUsername();
//        String token = userService.login(username, userLoginReq.getPassword());
//        if (token == null) {
//            return new ResponseEntity<>(CommonResult.validateFailed("用户名或密码错误"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        User user = userService.getUserByUsername(username);
//        UserLoginResp userLoginResp = userService.getUserLoginResult(user);
//        userLoginResp.setToken(token);
//
//        return new ResponseEntity<>(userLoginResp, HttpStatus.OK);
//    }
//
//
//    @ApiOperation("修改密码")
//    @RequestMapping(value = "/passwords/set", method = RequestMethod.PUT)
//    public ResponseEntity<?> updatePassword(@RequestHeader HttpHeaders headers,
//                                            @RequestBody PasswordReq passwordReq) {
//        Boolean isOldPasswordRight = userService.updatePassword(headers, passwordReq);
//        if (!isOldPasswordRight) {
//            return new ResponseEntity<>(CommonResult.validateFailed("旧密码错误"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>("", HttpStatus.OK);
//    }
//
//    @ApiOperation("重置密码")
//    @RequestMapping(value = "/{id}/passwords/reset", method = RequestMethod.PUT)
//    public ResponseEntity<?> resetPassword(@PathVariable("id") Long id) {
//        String password = userService.resetPassword(id);
//        return new ResponseEntity<>(CommonResult.success(password), HttpStatus.OK);
//    }
}
