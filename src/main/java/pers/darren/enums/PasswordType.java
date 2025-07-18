package pers.darren.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PasswordType implements IEnum<Integer> {
    PASSWORD_TYPE_1(1, "交易密码"),
    PASSWORD_TYPE_2(2, "查询密码"),
    PASSWORD_TYPE_3(3, "登录密码");

    @JsonValue
    private final Integer code;
    private final String name;

    PasswordType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.code.toString();
    }
}
