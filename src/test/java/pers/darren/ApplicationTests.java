package pers.darren;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import pers.darren.entity.User;
import pers.darren.mapper.UserMapper;
import pers.darren.service.IUserService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;

    @Test
    public void testSelect() {
        System.out.println("----- selectAll method test ------");
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(!userList.isEmpty(), "");
        userList.forEach(System.out::println);
    }

    @Test
    public void testList() {
        System.out.println("----- list method test ------");
        List<User> list = userService.list();
        Assert.isTrue(!list.isEmpty(), "");
        list.forEach(System.out::println);
    }

    @Test
    public void testSave() {
        System.out.println("----- save method test ------");
        User user = new User();
        user.setName("Darren");
        user.setAge(18);
        user.setEmail("darren@gmail.com");
        user.setCreatedBy(1L);
        boolean result = userService.save(user);
        Assert.isTrue(result, "");
        System.out.println("Success！");
    }

    @Test
    public void testSaveBatch() {
        System.out.println("----- saveBatch method test ------");
        // 假设有一组 User 实体对象
        List<User> users = Arrays.asList(
                new User("Alice", 19, "alice@example.com", 1L),
                new User("Bob", 18, "bob@example.com", 1L),
                new User("Charlie", 20, "charlie@example.com", 1L)
        );
        // 使用默认批次大小进行批量插入
        boolean result = userService.saveBatch(users); // 调用 saveBatch 方法，默认批次大小
        if (result) {
            System.out.println("Users saved successfully.");
        } else {
            System.out.println("Failed to save users.");
        }
    }

    @Test
    public void testSaveBatchSize() {
        System.out.println("----- saveBatchSize method test ------");
        // 假设有一组 User 实体对象
        List<User> users = Arrays.asList(
                new User("David", 24, "david@example.com", 1L),
                new User("Eve", 21, "eve@example.com", 1L),
                new User("Frank", 26, "frank@example.com", 1L),
                new User("Grace", 22, "grace@example.com", 1L)
        );
        // 指定批次大小为 2进行批量插入
        boolean result = userService.saveBatch(users, 2); // 调用 saveBatch 方法，指定批次大小
        if (result) {
            System.out.println("Users saved successfully.");
        } else {
            System.out.println("Failed to save users.");
        }
    }

    @Test
    public void testUpdate() {
        System.out.println("----- update method test ------");
        User user = new User();
        user.setId(7L);
        user.setName("Darren Luo");
        user.setAge(17);
        user.setEmail("darrenluo1993@gmail.com");
        user.setModifiedBy(1L);
        boolean result = userService.updateById(user);
        Assert.isTrue(result, "");
        System.out.println("Success！");
    }

    @Test
    public void testRemove() {
        System.out.println("----- remove method test ------");
        boolean result = userService.removeById(19L);
        Assert.isTrue(result, "");
        System.out.println("removeById Success！");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Eve1");
        result = userService.remove(queryWrapper);
        Assert.isTrue(result, "");
        System.out.println("removeByQueryWrapper Success！");
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getName, "Frank1").eq(User::getAge, 26).eq(User::getEmail, "frank@example.com");
        result = userService.remove(lambdaQueryWrapper);
        Assert.isTrue(result, "");
        System.out.println("removeByLambdaQueryWrapper Success！");
    }

    @Test
    public void testUpdateByUpdateWrapper() {
        System.out.println("----- updateByUpdateWrapper method test ------");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 22L).set("name", "Grace Ai").set("email", "graceai@example.com").set("modified_by", 1L);
        boolean result = userService.update(updateWrapper);
        Assert.isTrue(result, "");
        System.out.println("updateByUpdateWrapper Success！");
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(User::getId, 17L).set(User::getName, "Frank Liu").set(User::getEmail, "frankliu@example.com").set(User::getModifiedBy, 1L);
        result = userService.update(lambdaUpdateWrapper);
        Assert.isTrue(result, "");
        System.out.println("updateByLambdaUpdateWrapper Success！");
    }

    @Test
    public void testPage() {
        System.out.println("----- page method test ------");
        IPage<User> page = new Page<>(1, 5);
        IPage<User> result = userService.page(page);
        Assert.isTrue(result.getTotal() > 0, "");
        result.getRecords().forEach(System.out::println);
        System.out.println("page Success！");
        result = userService.page(page, new QueryWrapper<User>().eq("age", 18));
        Assert.isTrue(result.getTotal() > 0, "");
        result.getRecords().forEach(System.out::println);
        System.out.println("page Success！");
        result = userService.page(page, new LambdaQueryWrapper<User>().eq(User::getAge, 22));
        Assert.isTrue(result.getTotal() > 0, "");
        result.getRecords().forEach(System.out::println);
        System.out.println("page Success！");
    }
}
