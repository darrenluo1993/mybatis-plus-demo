package pers.darren.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DeleteStatus {
    DELETED(1, "已删除"),
    NOT_DELETED(0, "未删除");

    @EnumValue
    @JsonValue
    private final Integer code;
    private final String name;

    DeleteStatus(Integer code, String name) {
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
    public String toString() {
        return this.code.toString();
    }
}
