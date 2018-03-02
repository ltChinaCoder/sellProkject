package com.lt.sell.util;

import com.lt.sell.enums.BaseEnum;

public class EnumUtil {
    public static <T extends BaseEnum> T findEnumByCode(Integer code, Class<T> tClass) {
        for (T t : tClass.getEnumConstants()) {
            if (t.getCode().equals(code))
                return t;
        }
        return null;
    }
}
