package pers.darren.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import pers.darren.enums.DeleteStatus;
import pers.darren.enums.Gender;
import pers.darren.enums.PasswordType;
import pers.darren.enums.UserStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.alibaba.fastjson.serializer.SerializerFeature.WriteEnumUsingToString;
import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.FieldFill.UPDATE;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author Darren Luo
 * @since 2025-07-10 12:21:18
 */
@TableName("user")
public class User extends Model<User> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 性别
     */
    @TableField("gender")
    @JSONField(serialzeFeatures = WriteEnumUsingToString)
    private Gender gender;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户状态
     */
    @TableField("user_status")
    @JSONField(serialzeFeatures = WriteEnumUsingToString)
    private UserStatus userStatus;

    /**
     * 密码类型
     */
    @TableField("password_type")
    @JSONField(serialzeFeatures = WriteEnumUsingToString)
    private PasswordType passwordType;

    /**
     * 创建人
     */
    @TableField(value = "created_by", fill = INSERT)
    private Long createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "created_time", fill = INSERT)
    private LocalDateTime createdTime;

    /**
     * 修改人
     */
    @TableField(value = "modified_by", fill = UPDATE)
    private Long modifiedBy;

    /**
     * 修改时间
     */
    @TableField(value = "modified_time", fill = UPDATE)
    private LocalDateTime modifiedTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField("deleted")
    @JSONField(serialzeFeatures = WriteEnumUsingToString)
    private DeleteStatus deleted;

    public User() {
    }

    public User(String name, Integer age, String email, Long createdBy) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.createdBy = createdBy;
    }

    public User(String name, Integer age, Gender gender, String email, UserStatus userStatus, PasswordType passwordType) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.userStatus = userStatus;
        this.passwordType = passwordType;
    }

    public User(String name, Integer age, Gender gender, String email, UserStatus userStatus, PasswordType passwordType, Long createdBy) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.userStatus = userStatus;
        this.passwordType = passwordType;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public PasswordType getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(PasswordType passwordType) {
        this.passwordType = passwordType;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public DeleteStatus getDeleted() {
        return deleted;
    }

    public void setDeleted(DeleteStatus deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", userStatus=" + userStatus +
                ", passwordType=" + passwordType +
                ", createdBy=" + createdBy +
                ", createdTime=" + createdTime +
                ", modifiedBy=" + modifiedBy +
                ", modifiedTime=" + modifiedTime +
                ", deleted=" + deleted +
                '}';
    }
}
