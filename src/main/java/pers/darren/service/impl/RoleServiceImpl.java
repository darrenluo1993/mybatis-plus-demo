package pers.darren.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.darren.entity.Role;
import pers.darren.mapper.RoleMapper;
import pers.darren.service.IRoleService;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author Darren Luo
 * @since 2025-07-10 12:21:18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
