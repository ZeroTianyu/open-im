package com.openim.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openim.user.entity.Friend;
import com.openim.user.mapper.FriendMapper;
import com.openim.user.service.IFriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class IFriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements IFriendService {
}
