package com.openim.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openim.auth.entity.*;
import com.openim.auth.mapper.Oauth2BasicUserMapper;
import com.openim.auth.mapper.AuthorityMapper;
import com.openim.auth.mapper.RoleAuthorityMapper;
import com.openim.auth.mapper.UserRoleMapper;
import com.openim.auth.model.security.CustomGrantedAuthority;
import com.openim.auth.service.IOauth2BasicUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 基础用户信息表 服务实现类
 * </p>
 *
 * @author vains
 */
@Service
@RequiredArgsConstructor
public class Oauth2BasicUserServiceImpl extends ServiceImpl<Oauth2BasicUserMapper, Oauth2BasicUser> implements IOauth2BasicUserService, UserDetailsService {

    private final UserRoleMapper userRoleMapper;

    private final AuthorityMapper authorityMapper;

    private final RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 在Security中“username”就代表了用户登录时输入的账号，在重写该方法时它可以代表以下内容：账号、手机号、邮箱、姓名等
        // “username”在数据库中不一定非要是一样的列，它可以是手机号、邮箱，也可以都是，最主要的目的就是根据输入的内容获取到对应的用户信息，如下方所示
        // 通过传入的账号信息查询对应的用户信息
        LambdaQueryWrapper<Oauth2BasicUser> wrapper = Wrappers.lambdaQuery(Oauth2BasicUser.class)
                .or(o -> o.eq(Oauth2BasicUser::getEmail, username))
                .or(o -> o.eq(Oauth2BasicUser::getMobile, username))
                .or(o -> o.eq(Oauth2BasicUser::getAccount, username));
        Oauth2BasicUser basicUser = baseMapper.selectOne(wrapper);
        if (basicUser == null) {
            throw new UsernameNotFoundException("账号不存在");
        }

        // 通过用户角色关联表查询对应的角色
        List<UserRole> userRoles = userRoleMapper.selectList(Wrappers.lambdaQuery(UserRole.class).eq(UserRole::getUserId, basicUser.getId()));
        List<Long> rolesId = Optional.ofNullable(userRoles).orElse(Collections.emptyList()).stream().map(UserRole::getRoleId).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(rolesId)) {
            return basicUser;
        }
        // 通过角色菜单关联表查出对应的菜单
        List<RoleAuthority> roleMenus = roleAuthorityMapper.selectList(Wrappers.lambdaQuery(RoleAuthority.class).in(RoleAuthority::getRoleId, rolesId));
        List<Integer> menusId = Optional.ofNullable(roleMenus).orElse(Collections.emptyList()).stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(menusId)) {
            return basicUser;
        }

        // 根据菜单ID查出菜单
        List<Authority> menus = authorityMapper.selectBatchIds(menusId);
        Set<CustomGrantedAuthority> authorities = Optional.ofNullable(menus).orElse(Collections.emptyList()).stream().map(Authority::getAuthority).map(CustomGrantedAuthority::new).collect(Collectors.toSet());
        basicUser.setAuthorities(authorities);
        return basicUser;
    }

    @Override
    public Long saveByThirdAccount(Oauth2ThirdAccount thirdAccount) {
        Oauth2BasicUser basicUser = new Oauth2BasicUser();
        basicUser.setNikeName(thirdAccount.getName());
        basicUser.setAvatarUrl(thirdAccount.getAvatarUrl());
        basicUser.setDeleted(Boolean.FALSE);
        basicUser.setSourceFrom(thirdAccount.getType());
        this.save(basicUser);
        return basicUser.getId();
    }
}
