package com.openim.auth.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openim.auth.entity.Role;
import com.openim.auth.service.IRoleService;
import com.openim.auth.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author vains
 * @since 2023-07-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
