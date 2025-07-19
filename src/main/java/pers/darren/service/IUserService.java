package pers.darren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.darren.entity.User;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Darren Luo
 * @since 2025-07-10 12:21:18
 */
public interface IUserService extends IService<User> {

    List<User> listByAge(int age);

    List<User> listByName(String name);
}
