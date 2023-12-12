package com.openim.user.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.openim.common.model.OpenIMResponse;
import com.openim.user.controller.base.OpenImBaseController;
import com.openim.user.entity.UserInfo;
import com.openim.user.model.response.UserInfoResponse;
import com.openim.user.service.IUserInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserInfoController extends OpenImBaseController {
    @Resource
    private IUserInfoService iUserInfoService;

    @GetMapping("/info")
    public OpenIMResponse info() {
        // 获取当前认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 组装条件
        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery();
        UserInfo oauth2BasicUser = iUserInfoService.getOne(wrapper.eq(UserInfo::getId, authentication.getName()));
        // 构建返回信息
        UserInfoResponse userResponse = new UserInfoResponse();
        BeanUtil.copyProperties(oauth2BasicUser, userResponse, true);
        return OpenIMResponse.SUCCESS(userResponse);
    }


    @GetMapping("/info/{userId}")
    public OpenIMResponse info(@PathVariable String userId) {
        // 组装条件
        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery();
        UserInfo oauth2BasicUser = iUserInfoService.getOne(wrapper.eq(UserInfo::getId, userId));
        // 构建返回信息
        UserInfoResponse userResponse = new UserInfoResponse();
        BeanUtil.copyProperties(oauth2BasicUser, userResponse, true);
        return OpenIMResponse.SUCCESS(userResponse);
    }


}
