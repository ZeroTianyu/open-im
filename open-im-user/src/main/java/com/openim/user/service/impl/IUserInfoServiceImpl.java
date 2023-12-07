package com.openim.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openim.user.entity.UserInfo;
import com.openim.user.mapper.UserInfoMapper;
import com.openim.user.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class IUserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
}
