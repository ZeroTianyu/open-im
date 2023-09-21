package com.openim.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openim.auth.entity.Authority;
import com.openim.auth.entity.RoleAuthority;
import com.openim.auth.entity.UserRole;
import com.openim.auth.service.IAuthorityService;
import com.openim.auth.mapper.AuthorityMapper;
import com.openim.auth.mapper.RoleAuthorityMapper;
import com.openim.auth.mapper.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author vains
 * @since 2023-07-04
 */
@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements IAuthorityService {

    private final UserRoleMapper userRoleMapper;

    private final RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public List<Authority> getByUserId(Long userId) {
        // 通过用户角色关联表查询对应的角色
        List<UserRole> userRoles = userRoleMapper.selectList(Wrappers.lambdaQuery(UserRole.class).eq(UserRole::getUserId, userId));
        List<Long> rolesId = Optional.ofNullable(userRoles).orElse(Collections.emptyList()).stream().map(UserRole::getRoleId).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(rolesId)) {
            return Collections.emptyList();
        }
        // 通过角色菜单关联表查出对应的菜单
        List<RoleAuthority> roleMenus = roleAuthorityMapper.selectList(Wrappers.lambdaQuery(RoleAuthority.class).in(RoleAuthority::getRoleId, rolesId));
        List<Integer> menusId = Optional.ofNullable(roleMenus).orElse(Collections.emptyList()).stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(menusId)) {
            return Collections.emptyList();
        }

        // 根据菜单ID查出菜单
        return baseMapper.selectBatchIds(menusId);
    }
}
