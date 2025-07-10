package pers.darren;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import pers.darren.entity.User;
import pers.darren.mapper.UserMapper;
import pers.darren.service.IUserService;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }

    @Test
    public void testList() {
        System.out.println(("----- list method test ------"));
        List<User> list = userService.list();
        Assert.isTrue(5 == list.size(), "");
        list.forEach(System.out::println);
    }
}
