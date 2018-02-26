package com.lt.sell.util;

import java.util.Random;

public class KeyGenerateUtil {
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis());
        return stringBuilder.append(random.nextInt(900000) + 100000).toString();
    }
}
