package com.lt.sell.util;

import java.util.Collection;
import java.util.Map;

public class ListMapUtil {

    public static boolean isNullAndEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNull(Collection<?> collection) {
        return collection == null;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection.isEmpty();
    }

    public static boolean isNullAndEmpty(Map<?, ?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNull(Map<?, ?> collection) {
        return collection == null;
    }

    public static boolean isEmpty(Map<?, ?> collection) {
        return collection.isEmpty();
    }
}
