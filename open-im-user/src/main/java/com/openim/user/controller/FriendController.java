package com.openim.user.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openim.common.exception.OpenIMException;
import com.openim.common.model.OpenIMResponse;
import com.openim.common.model.jwt.JwtPayload;
import com.openim.common.utils.JwtPayLoadUtils;
import com.openim.user.controller.base.OpenImBaseController;
import com.openim.user.entity.Friend;
import com.openim.user.model.request.FriendAddRequest;
import com.openim.user.service.IFriendService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
public class FriendController extends OpenImBaseController {

    @Resource
    private IFriendService iFriendService;


    /**
     * 添加好友
     *
     * @param addRequest
     * @return
     */
    @PostMapping("/add")
    public OpenIMResponse add(@Valid @RequestBody FriendAddRequest addRequest) {
        // 当前用户信息
        JwtPayload jwtPayLoad = JwtPayLoadUtils.getJwtPayLoad();
        LambdaQueryWrapper<Friend> wrapper = Wrappers.lambdaQuery(Friend.class);
        wrapper.eq(Friend::getUserId, jwtPayLoad.getUserId()).eq(Friend::getFriendId, addRequest.getFriendId());
        Friend friend = iFriendService.getOne(wrapper);
        Assert.isNull(friend, () -> new OpenIMException("您已添加该用户为好友,请勿重复添加"));
        friend = new Friend();
        friend.setUserId(jwtPayLoad.getUserId());
        friend.setFriendId(addRequest.getFriendId());
        iFriendService.save(friend);
        return OpenIMResponse.SUCCESS(friend);
    }


    @GetMapping("/list")
    public OpenIMResponse list(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        // 当前用户信息
        JwtPayload jwtPayLoad = JwtPayLoadUtils.getJwtPayLoad();

        LambdaQueryWrapper<Friend> wrapper = Wrappers.lambdaQuery(Friend.class);
        wrapper.eq(Friend::getUserId, jwtPayLoad.getUserId());

        Page<Friend> page = iFriendService.page(Page.of(pageNo, pageSize), wrapper);
        return OpenIMResponse.SUCCESS(page);
    }

}
