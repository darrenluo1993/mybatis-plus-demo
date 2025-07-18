package pers.darren.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus implements IEnum<Integer> {
    USER_STATUS_1(1, "正常"),
    USER_STATUS_2(2, "禁用"),
    USER_STATUS_3(3, "删除");

    @JsonValue
    private final Integer code;
    private final String name;

    UserStatus(Integer code, String name) {
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
