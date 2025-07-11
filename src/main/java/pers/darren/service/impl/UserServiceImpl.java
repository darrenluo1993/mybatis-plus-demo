package pers.darren.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.darren.entity.User;
import pers.darren.mapper.UserMapper;
import pers.darren.service.IUserService;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Darren Luo
 * @since 2025-07-10 12:21:18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
