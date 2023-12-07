package com.openim.auth.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.openim.auth.entity.Oauth2BasicUser;
import com.openim.auth.model.OpenIMResponse;
import com.openim.auth.model.response.Oauth2BasicUserResponse;
import com.openim.auth.service.IOauth2BasicUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IOauth2BasicUserService basicUserService;


    @GetMapping("/info")
    public OpenIMResponse info() {
        // 获取当前认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 组装条件
        LambdaQueryWrapper<Oauth2BasicUser> wrapper = Wrappers.lambdaQuery();
        Oauth2BasicUser oauth2BasicUser = basicUserService.getOne(wrapper.eq(Oauth2BasicUser::getAccount, authentication.getName()));
        // 构建返回信息
        Oauth2BasicUserResponse userResponse = new Oauth2BasicUserResponse();
        BeanUtil.copyProperties(oauth2BasicUser, userResponse, true);
        return OpenIMResponse.SUCCESS(userResponse);
    }

}
