package pers.darren.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.darren.domain.User;
import pers.darren.mapper.UserMapper;
import pers.darren.service.UserService;

/**
* @author darren
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2025-07-09 17:54:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




