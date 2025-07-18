package pers.darren.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE(1, "男"),
    FEMALE(0, "女");

    @EnumValue
    @JsonValue
    private final int code;
    private final String name;

    Gender(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return Integer.toString(this.code);
    }
}
