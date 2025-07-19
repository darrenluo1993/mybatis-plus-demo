package pers.darren.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.darren.entity.User;
import pers.darren.mapper.UserMapper;
import pers.darren.service.IUserService;

import java.util.List;

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

    @Override
    @DS("slave_1")
    public List<User> listByAge(int age) {
        return super.list(Wrappers.<User>lambdaQuery().eq(User::getAge, age));
    }

    @Override
    @DS("slave_2")
    public List<User> listByName(String name) {
        return super.list(Wrappers.lambdaQuery(User.class).eq(User::getName, name));
    }
}
