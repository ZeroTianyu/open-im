package com.openim.auth.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openim.auth.entity.UserRole;
import com.openim.auth.service.IUserRoleService;
import com.openim.auth.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vains
 * @since 2023-07-04
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
