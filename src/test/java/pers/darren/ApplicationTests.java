package pers.darren;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.baomidou.mybatisplus.extension.toolkit.SimpleQuery;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import pers.darren.entity.User;
import pers.darren.mapper.UserMapper;
import pers.darren.service.IUserService;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.alibaba.fastjson.JSON.toJSONString;
import static com.baomidou.mybatisplus.core.toolkit.Wrappers.lambdaQuery;
import static com.baomidou.mybatisplus.core.toolkit.Wrappers.query;
import static pers.darren.enums.Gender.FEMALE;
import static pers.darren.enums.Gender.MALE;
import static pers.darren.enums.PasswordType.PASSWORD_TYPE_3;
import static pers.darren.enums.UserStatus.USER_STATUS_1;
import static pers.darren.enums.UserStatus.USER_STATUS_2;

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
        user.setGender(MALE);
        user.setEmail("darren@gmail.com");
        user.setUserStatus(USER_STATUS_1);
        user.setPasswordType(PASSWORD_TYPE_3);
        user.setPassword("123456");
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
        QueryWrapper<User> queryWrapper = query();
        queryWrapper.eq("name", "Eve1");
        result = userService.remove(queryWrapper);
        Assert.isTrue(result, "");
        System.out.println("removeByQueryWrapper Success！");
        LambdaQueryWrapper<User> lambdaQueryWrapper = lambdaQuery();
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
        LambdaQueryWrapper<User> lambdaQueryWrapper = lambdaQuery();
        lambdaQueryWrapper.orderByDesc(User::getModifiedTime);
        IPage<User> result = userService.page(page, lambdaQueryWrapper);
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

    @Test
    public void testQueryChainWrapper() {
        System.out.println("----- queryChainWrapper method test ------");
        QueryChainWrapper<User> queryChainWrapper = userService.query();
        User user = queryChainWrapper.eq("id", 1L).select("id", "name", "age", "email").one();
        System.out.println(user);
        System.out.println("queryChainWrapper Success！");
        LambdaQueryChainWrapper<User> lambdaQueryChainWrapper = userService.lambdaQuery();
        user = lambdaQueryChainWrapper.eq(User::getId, 2L).select(User::getId, User::getName, User::getAge, User::getEmail).one();
        System.out.println(user);
        System.out.println("lambdaQueryChainWrapper Success！");
    }

    @Test
    public void testUpdateChainWrapper() {
        System.out.println("----- updateChainWrapper method test ------");
        UpdateChainWrapper<User> updateChainWrapper = userService.update();
        boolean result = updateChainWrapper.eq("id", 3L).set("name", "Charlie Liu").set("email", "charlieliu@example.com").set("modified_by", 1L).update();
        Assert.isTrue(result, "");
        System.out.println("updateChainWrapper Success！");
        LambdaUpdateChainWrapper<User> lambdaUpdateChainWrapper = userService.lambdaUpdate();
        result = lambdaUpdateChainWrapper.eq(User::getId, 4L).set(User::getName, "David Ai").set(User::getEmail, "davidai@example.com").set(User::getModifiedBy, 1L).update();
        Assert.isTrue(result, "");
        System.out.println("lambdaUpdateChainWrapper Success！");
    }

    @Test
    public void testActiveRecord() {
        System.out.println("----- activeRecord method test ------");
        User user = new User();
        List<User> list = user.selectAll();
        Assert.isTrue(!list.isEmpty(), "");
        list.forEach(System.out::println);
        System.out.println("activeRecord Success！");
        user.setId(5L);
        user.setName("Eve Liu");
        user.setAge(18);
        user.setEmail("eve.liu@example.com");
        user.setModifiedBy(1L);
        boolean result = user.updateById();
        Assert.isTrue(result, "");
        System.out.println("activeRecord Success！");
        user = user.selectById();
        System.out.println(user);
        System.out.println("activeRecord Success！");
        user = new User();
        user.setName("zhangsan");
        user.setAge(27);
        user.setEmail("zhangsan@example.com");
        user.setCreatedBy(1L);
        result = user.insertOrUpdate();
        Assert.isTrue(result, "");
        System.out.println("activeRecord Success！");
        user.setName("ZhangSan");
        user.setAge(25);
        user.setEmail("zhangsan@163.com");
        result = user.insertOrUpdate();
        Assert.isTrue(result, "");
        System.out.println("activeRecord Success！");
    }

    @Test
    public void testSimpleQuery() {
        System.out.println("----- simpleQuery method test ------");
        List<User> list = new ArrayList<>();
        SimpleQuery.list(lambdaQuery(User.class).eq(User::getAge, 18), User::getName, user -> System.out.println(user.getId() + " " + user.getName()), list::add).forEach(System.out::println);
        list.forEach(System.out::println);
        System.out.println("simpleQuery Success！");
        Map<Long, User> map = SimpleQuery.keyMap(lambdaQuery(User.class).eq(User::getAge, 18), User::getId, user -> System.out.println(user.getId() + " " + user.getName()));
        map.forEach((id, user) -> System.out.println("key:" + id + ", value:" + user));
        System.out.println("simpleQuery Success！");
        Map<Long, String> map1 = SimpleQuery.map(lambdaQuery(User.class).eq(User::getAge, 18), User::getId, User::getName, user -> System.out.println(user.getId() + " " + user.getName()));
        map1.forEach((id, name) -> System.out.println("key:" + id + ", value:" + name));
        System.out.println("simpleQuery Success！");
        Map<Integer, List<User>> map2 = SimpleQuery.group(lambdaQuery(User.class).ne(User::getName, "zhangsan").orderByAsc(User::getAge, User::getId), User::getAge, user -> System.out.println(user.getId() + " " + user.getName() + " " + user.getAge()));
        map2.forEach((age, list1) -> System.out.println("key:" + age + ", valueSize:" + list1.size()));
        System.out.println("simpleQuery Success！");
        Map<Integer, Long> map3 = SimpleQuery.group(lambdaQuery(User.class).between(User::getAge, 18, 20).orderByAsc(User::getAge, User::getId), User::getAge, Collectors.counting(), user -> System.out.println(user.getId() + " " + user.getName() + " " + user.getAge()));
        map3.forEach((age, count) -> System.out.println("key:" + age + ", value:" + count));
        System.out.println("simpleQuery Success！");
        Map<Integer, Optional<User>> map4 = SimpleQuery.group(lambdaQuery(User.class).between(User::getAge, 18, 20).orderByAsc(User::getAge, User::getId), User::getAge, Collectors.minBy(Comparator.comparingLong(User::getId)), user -> System.out.println(user.getId() + " " + user.getName() + " " + user.getAge()));
        map4.forEach((age, optional) -> System.out.println("key:" + age + ", value:" + (optional.isPresent() ? optional.get() : "null")));
        System.out.println("simpleQuery Success！");
    }

    @Test
    public void testDbKit() {
        System.out.println("----- dbKit method test ------");
        Db.list(lambdaQuery(User.class).eq(User::getAge, 20)).forEach(System.out::println);
        System.out.println("dbKit Success！");
        Db.listMaps(lambdaQuery(User.class).eq(User::getAge, 20)).forEach(map -> System.out.println(map.get("id") + " " + map.get("name")));
        System.out.println("dbKit Success！");
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testWrapperFunc(boolean isAdult) {
        System.out.println("----- func method test ------");
        LambdaQueryWrapper<User> lambdaQueryWrapper = lambdaQuery();
        lambdaQueryWrapper.func(wrapper -> {
            if (isAdult) {
                wrapper.ge(User::getAge, 18);
            } else {
                wrapper.lt(User::getAge, 18);
            }
        });
        this.userService.list(lambdaQueryWrapper).forEach(System.out::println);
        System.out.println("func Success！");
    }

    @Test
    public void testWrapperNested() {
        System.out.println("----- nested method test ------");
        LambdaQueryWrapper<User> lambdaQueryWrapper = lambdaQuery();
        lambdaQueryWrapper.nested(wrapper -> wrapper.eq(User::getAge, 18).eq(User::getName, "root"));
        lambdaQueryWrapper.or();
        lambdaQueryWrapper.nested(wrapper -> wrapper.eq(User::getAge, 24).eq(User::getName, "Billie"));
        this.userService.list(lambdaQueryWrapper).forEach(System.out::println);
        System.out.println("nested Success！");
        lambdaQueryWrapper = lambdaQuery();
        lambdaQueryWrapper.eq(User::getAge, 18).eq(User::getName, "root").or().eq(User::getAge, 24).eq(User::getName, "Billie");
        this.userService.list(lambdaQueryWrapper).forEach(System.out::println);
    }

    @Test
    public void testWrapperSelect() {
        System.out.println("----- select method test ------");
        LambdaQueryWrapper<User> lambdaQueryWrapper = lambdaQuery();
        lambdaQueryWrapper.select(User::getId, User::getName);
        lambdaQueryWrapper.last("limit 1");
        this.userService.list(lambdaQueryWrapper).forEach(System.out::println);
        System.out.println("select Success！");
        lambdaQueryWrapper = lambdaQuery(User.class);
        lambdaQueryWrapper.select(tableFieldInfo -> {
            String property = tableFieldInfo.getProperty();
            return property.equals("id") || property.equals("name") || property.equals("age");
        });
        lambdaQueryWrapper.last("limit 1");
        this.userService.list(lambdaQueryWrapper).forEach(System.out::println);
        System.out.println("select Success！");
    }

    @Test
    public void testResultHandler() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = lambdaQuery();
        lambdaQueryWrapper.eq(User::getAge, 18);
        this.userMapper.selectList(lambdaQueryWrapper, resultContext -> {
            System.out.print("count: " + resultContext.getResultCount() + ", ");
            User user = resultContext.getResultObject();
            System.out.println(user);
        });
    }

    @Test
    public void testAutoEnumMapping() throws JsonProcessingException {
        System.out.println("----- autoEnumMapping method test ------");
        User user = this.userService.getById(1L);
        user.setGender(MALE);
        user.setUserStatus(USER_STATUS_1);
        user.setPasswordType(PASSWORD_TYPE_3);
        user.setModifiedBy(1L);
        user.insertOrUpdate();

        String userJSON = toJSONString(user, true);
        System.out.println(userJSON);

        JsonMapper jsonMapper = new JsonMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        ObjectWriter objectWriter = jsonMapper.writerWithDefaultPrettyPrinter();
        userJSON = objectWriter.writeValueAsString(user);
        System.out.println(userJSON);
    }

    @Test
    public void testMetaObjectHandler() {
        User user = new User("test", 18, MALE, "test@163.com", USER_STATUS_1, PASSWORD_TYPE_3);
        user.insert();
        System.out.println(user);
        user.setGender(FEMALE);
        user.setEmail("test@gmail.com");
        user.setUserStatus(USER_STATUS_2);
        user.updateById();
        System.out.println(user);
    }

    @Test
    public void testLogicDelete() {
        System.out.println("----- logicDelete method test ------");
        this.userService.removeById(5L);
        System.out.println("logicDelete Success！");
    }
}
