package com.openim.auth.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openim.auth.entity.RoleAuthority;
import com.openim.auth.mapper.RoleAuthorityMapper;
import com.openim.auth.service.IRoleAuthorityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单多对多关联表 服务实现类
 * </p>
 *
 * @author vains
 * @since 2023-07-04
 */
@Service
public class RoleAuthorityServiceImpl extends ServiceImpl<RoleAuthorityMapper, RoleAuthority> implements IRoleAuthorityService {

}
