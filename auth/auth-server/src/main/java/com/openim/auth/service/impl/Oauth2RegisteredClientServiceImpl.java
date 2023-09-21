package com.openim.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openim.auth.entity.Oauth2RegisteredClient;
import com.openim.auth.mapper.Oauth2RegisteredClientMapper;
import com.openim.auth.service.IOauth2RegisteredClientService;
import org.springframework.stereotype.Service;

@Service
public class Oauth2RegisteredClientServiceImpl extends ServiceImpl<Oauth2RegisteredClientMapper, Oauth2RegisteredClient> implements IOauth2RegisteredClientService {


}
